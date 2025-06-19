package glueSteps;

import io.cucumber.java.en.*;
import net.javacrumbs.jsonunit.JsonAssert;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import utils.JsonCompareConfigurations;
import net.javacrumbs.jsonunit.core.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class StepsApi {

    private ClassicHttpResponse response;
    private String responseBody;


    @And("тело ответа JSON соответствует ожидаемому результату \"{}\" по правилу проверки \"{}\"")
    public void verifyJsonBody(String expectedJsonPath, String compareModeStr) throws Exception {
        assertNotNull(responseBody, "API response is null! Make a request first!");

        String expectedJson = Files.readString(Paths.get("src/test/resources/" + expectedJsonPath));
        String actualJson = responseBody;

        Configuration configuration = JsonCompareConfigurations.CONFIGURATIONS.get(compareModeStr.toUpperCase());
        if (configuration == null) {
            throw new IllegalArgumentException("Unknown comparison rule: " + compareModeStr);
        }

        JsonAssert.assertJsonEquals(expectedJson, actualJson, configuration);
    }


    @Given("^Тест делает GET вызов по адресу \"(.*)\"$")
    public void sendGetRequest(String url) throws Exception {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            var request = new HttpGet(url);
            response = client.executeOpen(null, request, null);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            responseBody = reader.lines().reduce("", (acc, line) -> acc + line);
        }
    }

    @Then("^Тест получает ответ с кодом \"(.*)\"$")
    public void verifyStatusCode(int expectedCode) {
        assertEquals(expectedCode, response.getCode());
    }

    @And("^тело ответа JSON содержит строку \"(.*)\"$")
    public void verifyResponseBody(String expectedContent) {
        assertTrue(responseBody.contains(expectedContent), "Response does not contain: " + expectedContent);
    }
}
