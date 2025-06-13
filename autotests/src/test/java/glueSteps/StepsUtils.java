package glueSteps;

import io.cucumber.java.en.*;

public class StepsUtils {

    @And("^подождать (\\d+) секунд[ыу]?$")
    public void waitFor(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000L);
    }

    @And("^Сценарий теста: \"(.*)\"$")
    public void log(String text) {
        System.out.println(text);
    }

}
