package glueSteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import settings.Settings;
import utils.pageResolver.Page;
import utils.pageResolver.PageResolver;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsWeb {

    private Page currentPage;

    @And("^выполнен скролл вниз")
    public void saveElementValueAsVariableDown() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
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
