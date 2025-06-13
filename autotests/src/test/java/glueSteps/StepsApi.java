package glueSteps;

import io.cucumber.java.en.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

public class StepsApi {

    private ClassicHttpResponse response;
    private String responseBody;

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
