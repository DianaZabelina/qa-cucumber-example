package glueSteps;

import com.codeborne.selenide.*;
import com.codeborne.selenide.Condition;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Settings;
import utils.pageResolver.Page;
import utils.pageResolver.PageResolver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsWeb {

    private Page currentPage;

    @And("^значение (?:элемента|кнопки|поля) '([^']*)' равно '(.*)'$")
    public void checkElementTextEqualsString(String elementName, String expectedText) {
        String normalizedExpected = expectedText.replaceAll("\\s+", " ").trim();
        currentPage.getElement(elementName)
                .shouldHave(Condition.exactText(normalizedExpected));
    }

    @And("^(?:элемент|кнопка|поле) '([^']*)' отобразил(?:ся|ось|ась) на экране")
    public void andElementVisibleOnScreen(String element) {
        Assert.assertTrue(isElementVisibleOnScreen(currentPage.getElement(element)));
    }

    public static boolean isElementVisibleOnScreen(SelenideElement element) {
        String script =
                "var rect = arguments[0].getBoundingClientRect();" +
                        "return (rect.top >= 0 && rect.left >= 0 && " +
                        "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
                        "rect.right <= (window.innerWidth || document.documentElement.clientWidth)" +
                        ");";
        return (Boolean) ((JavascriptExecutor) Selenide.webdriver().driver().getWebDriver())
                .executeScript(script, element);
    }

    @And("^загрузилась страница '([^']*)'$")
    public void checkCurrentPage(String pageTitle) {
        Page page = PageResolver.getPage(pageTitle);
        Assert.assertTrue(page.isLoaded());
        currentPage = page;
    }

    @And("^блок '([^']*)' содержит значения как в таблице:$")
    public void checkBlockContainsValues(String elementName, List<List<String>> values) {
        SelenideElement block = currentPage.getElement(elementName);

        List<List<String>> filteredValues = values.stream()
                .map(row -> row.stream()
                        .filter(cell -> cell != null && !cell.isBlank())
                        .toList())
                .toList();

        for (List<String> row : filteredValues) {
            String expectedText = String.join(" ", row);
            block.shouldHave(myValues(expectedText));
        }
    }

    public static Condition myValues(final String expectedText) {
        return new Condition("values " + expectedText) {
            public CheckResult check(Driver driver, WebElement element) {
                if (element == null) {
                    return CheckResult.rejected("element is null", null);
                }
                String text = element.getText();
                if (text == null) {
                    return CheckResult.rejected("text is null", null);
                }
                String normalizedText = text.replaceAll("\\s+", " ").trim();
                boolean matches = normalizedText.contains(expectedText)
                        || normalizedText.matches(".*" + Pattern.quote(expectedText) + ".*");

                return new CheckResult(matches, normalizedText);
            }
        };
    }

    @And("^выполнен скролл вниз")
    public void saveElementValueAsVariableDown() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
    }

    @And("^выполнен скролл вверх")
    public void saveElementValueAsVariableUp() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.sendKeys(Keys.PAGE_UP).perform();
    }

    @And("^(?:элемент|кнопка|поле) с текстом '([^']*)' отобразил(?:ся|ось|ась) на странице$")
    public void checkElementWithTextDisplayed(String textValue) {
        currentPage.getElementWithText(textValue).shouldBe(Condition.visible);
    }

    @And("^совершен переход на страницу '([^']*)'$")
    public void openPage(String pageTitle) {
        String baseUrl = Settings.getSwagLabsUrl();
        String path = PageResolver.getPath(pageTitle);

        openUrl(baseUrl + path);

        currentPage = PageResolver.getPage(pageTitle);
    }

    @And("^пользователь авторизовался с помощью логина и пароля$")
    public void signIn() {
        this.setFieldValue("User-name", Settings.getSwagLabsUserName());
        this.setFieldValue("Password", Settings.getSwagLabsPassword());
        this.clickOnElement("Login");
    }

    @And("^в поле '([^']*)' введено значение '(.*)'$")
    public void setFieldValue(String elementName, String value) {
        this.clearField(elementName);
        SelenideElement field = currentPage.getElement(elementName);
        field.setValue(value);
    }

    @And("^очищено поле '([^']*)'$")
    public void clearField(String elementName) {
        SelenideElement field = currentPage.getElement(elementName);
        field.click();
        field.clear();
    }

    @And("^выполнено нажатие на (?:кнопку|элемент|поле) '([^']*)'$")
    public void clickOnElement(String elementName) {
        SelenideElement element = currentPage.getElement(elementName);
        this.clickIfInteractable(element);
    }

    private void clickIfInteractable(SelenideElement element) {
        element.shouldBe(Condition.interactable, Duration.ofSeconds(15L));
        element.click();
    }

    @Given("^Тест открывает страницу \"(.*)\"$")
    public void openUrl(String url) {
        Selenide.open(url);
    }

    @Then("^заголовок содержит строку \"(.*)\"$")
    public void titleShouldContain(String expected) {
        assertTrue(Objects.requireNonNull(title()).contains(expected), "Title does not contain: " + expected);
    }

    @And("^окно браузера развернуто на весь экран$")
    public void maximizeWindow() {
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
