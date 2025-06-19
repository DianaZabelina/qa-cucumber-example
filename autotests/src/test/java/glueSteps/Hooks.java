package glueSteps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.AllureUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT-3"));

        if (!"true".equalsIgnoreCase(System.getenv("CI"))) {
            AllureUtils.сleanDirectory();
            setChromeOptions();
        }
    }

    private static void setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // Отключение менеджера паролей и предупреждений
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-features=PasswordLeakDetection,AutofillKeyedPasswords");
        // Важно: создать новый ChromeDriver с этими опциями
        WebDriver driver = new ChromeDriver(options);
        // Установить этот драйвер для Selenide
        WebDriverRunner.setWebDriver(driver);
    }

    @Before("@local-only")
    public void skipIfCI() {
        // GitHub Actions устанавливает переменную CI=true
        if ("true".equalsIgnoreCase(System.getenv("CI"))) {
            Assumptions.assumeTrue(false, "Этот тест выполняется только локально!");
        }
    }

    @AfterAll
    public static void afterAll() {

        if (!"true".equalsIgnoreCase(System.getenv("CI"))) {
            if (WebDriverRunner.hasWebDriverStarted()) {
                WebDriverRunner.getWebDriver().quit();
                WebDriverRunner.closeWebDriver(); // Для очистки ссылки внутри Selenide
            }
        }

        //AllureUtils.startAllureReport();
    }
}
