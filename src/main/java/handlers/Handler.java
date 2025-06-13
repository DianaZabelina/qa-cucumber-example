package handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import responses.HostPortJson;
import responses.ResponseHeader;
import responses.errors.ErrorResponse;
import responses.errors.ErrorBody;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Handler implements HttpHandler {

    private final String host;
    private final int port;

    public Handler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {

            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            Map<String, String> params = queryToMap(query);
            String paramValue = params.get("param");

            // Чтобы искусственно вызвать ошибку 500
            // Нужно значение param=500
            // http://localhost:8080/?param=500
            // При param=200
            // http://localhost:8080/?param=200
            // Будет стандартный ответ
            if (!params.isEmpty() && !"200".equals(paramValue)) {
                triggerInternalServerError();
            }

            // Пробуем получить ответ в формате JSON
            String response = getHostPortJSon();
            // Статус 200 (OK)
            exchange.sendResponseHeaders(200, response.getBytes().length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }

        } catch (Exception e) {
            // В случае ошибки отправляем код 500 (Internal Server Error)
            sendErrorResponse(exchange, 500, getError(e.getMessage()));
        }
    }

    private Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;

        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(URLDecoder.decode(entry[0], StandardCharsets.UTF_8),
                        URLDecoder.decode(entry[1], StandardCharsets.UTF_8));
            } else {
                result.put(URLDecoder.decode(entry[0], StandardCharsets.UTF_8), "");
            }
        }
        return result;
    }


    private String getError(String message) {
        String jsonResponse = "";

        // Создаем объект ошибки
        ErrorBody errorBody = new ErrorBody();
        errorBody.setLevel("ERROR");
        errorBody.setCode(3000);
        errorBody.setMessage(message);

        // Создаем объект заголовка ответа
        ResponseHeader responseHeader = new ResponseHeader();
        // Получаем текущую дату и время
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Форматируем дату в строку
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = currentDateTime.format(formatter);
        responseHeader.setResponseDate(formattedDate);
        responseHeader.setErrors(List.of(errorBody));

        // Создаем объект ошибки с заголовком
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponseHeader(responseHeader);

        // Сериализуем объект в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonResponse = objectMapper.writeValueAsString(errorResponse);
            System.out.println(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

    // Метод для вызова ошибки 500
    private void triggerInternalServerError() throws Exception {
        // Здесь мы явно вызываем ошибку 500, например, выбрасывая исключение
        throw new Exception("This is a manually triggered internal server error!");
    }

    private void sendErrorResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        exchange.sendResponseHeaders(statusCode, message.getBytes().length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(message.getBytes());
        }
    }

    private String getHostPortJSon() {
        String result = "";
        // Создаем объект HostPortResponse с заданным порядком полей
        HostPortJson responseObject = new HostPortJson(this.host, this.port);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.writeValueAsString(responseObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}


