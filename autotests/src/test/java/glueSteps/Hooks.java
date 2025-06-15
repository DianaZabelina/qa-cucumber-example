package glueSteps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import org.junit.jupiter.api.Assumptions;
import utils.AllureUtils;

import java.util.TimeZone;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT-3"));

        AllureUtils.сleanDirectory();
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

        AllureUtils.startAllureReport();
    }
}
