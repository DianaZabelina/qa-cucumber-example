package unitTests.handlers;

import handlers.Handler;
import unitTests.utils.FakeHttpExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-тесты HandlerTest")
class HandlerTest {

    private Handler handler;
    private FakeHttpExchange exchange;

    @BeforeEach
    void setUp() {
        handler = new Handler("localhost", 8080);
        exchange = new FakeHttpExchange();
    }

    @Test
    @DisplayName("200 - Тест без параметра")
    void testHandle_withoutParam_shouldReturnJson() throws Exception {

        exchange.setRequestURI("http://localhost:8080/");

        handler.handle(exchange);

        String response = exchange.getResponseBodyAsString();

        assertEquals(200, exchange.getResponseCode());
        assertTrue(response.contains("8080"));
    }

    @Test
    @DisplayName("200 - Тест с параметром 200")
    void testHandle_withParam200_shouldReturnJson() throws Exception {

        exchange.setRequestURI("http://localhost:8080/?param=200");

        handler.handle(exchange);

        String response = exchange.getResponseBodyAsString();

        assertEquals(200, exchange.getResponseCode());
        assertTrue(response.contains("8080"));
    }

    @Test
    @DisplayName("500 - Тест с параметром 500")
    void testHandle_withParam500_shouldReturnErrorResponse() throws Exception {

        exchange.setRequestURI("http://localhost:8080/?param=500");

        handler.handle(exchange);

        String response = exchange.getResponseBodyAsString();

        assertEquals(500, exchange.getResponseCode());
        assertTrue(response.contains("3000"));
    }

    @Test
    @DisplayName("500 - Тест с неизвестным параметром")
    void testHandle_withUnknownQueryParams_shouldReturnJson() throws Exception {

        exchange.setRequestURI("http://localhost:8080/?unexpected=value");

        handler.handle(exchange);

        String response = exchange.getResponseBodyAsString();

        assertEquals(500, exchange.getResponseCode());
        assertTrue(response.contains("3000"));
    }
}
