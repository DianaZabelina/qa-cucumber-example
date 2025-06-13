package glueSteps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.*;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.title;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepsWeb {

    @Given("^Тест открывает страницу \"(.*)\"$")
    public void openPage(String url) {
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
