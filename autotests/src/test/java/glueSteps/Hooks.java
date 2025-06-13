package glueSteps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import utils.AllureUtils;

import java.util.TimeZone;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {

        TimeZone.setDefault(TimeZone.getTimeZone("Etc/GMT-3"));

        AllureUtils.сleanDirectory();
    }

    @AfterAll
    public static void afterAll() {

        AllureUtils.startAllureReport();
    }
}
