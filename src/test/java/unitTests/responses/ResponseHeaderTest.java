package unitTests.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import responses.ResponseHeader;
import responses.errors.ErrorBody;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-тесты ResponseHeader")
class ResponseHeaderTest {

    @Test
    @DisplayName("Верификация Setters и Getters")
    void testSetAndGetResponseDate() {
        ResponseHeader header = new ResponseHeader();
        header.setResponseDate("2025-06-18T12:00:00Z");

        assertEquals("2025-06-18T12:00:00Z", header.getResponseDate());
    }

    @Test
    @DisplayName("Верификация Setters и Getters ERRORS")
    void testSetAndGetErrors() {
        ErrorBody error1 = new ErrorBody();
        error1.setLevel("ERROR");
        error1.setCode(400);
        error1.setMessage("Bad Request");

        ErrorBody error2 = new ErrorBody();
        error2.setLevel("WARNING");
        error2.setCode(404);
        error2.setMessage("Not Found");

        List<ErrorBody> errorList = List.of(error1, error2);

        ResponseHeader header = new ResponseHeader();
        header.setErrors(errorList);

        assertEquals(2, header.getErrors().size());

        ErrorBody first = header.getErrors().get(0);
        assertEquals("ERROR", first.getLevel());
        assertEquals(400, first.getCode());
        assertEquals("Bad Request", first.getMessage());

        ErrorBody second = header.getErrors().get(1);
        assertEquals("WARNING", second.getLevel());
        assertEquals(404, second.getCode());
        assertEquals("Not Found", second.getMessage());
    }

    @Test
    @DisplayName("Верификация значений по умолчанию")
    void testDefaultValues() {
        ResponseHeader header = new ResponseHeader();

        assertNull(header.getResponseDate());
        assertNull(header.getErrors());
    }

    @Test
    @DisplayName("Верификация сериализации в Json")
    void testSerializationToJson() throws JsonProcessingException {
        ErrorBody error = new ErrorBody();
        error.setLevel("INFO");
        error.setCode(100);
        error.setMessage("Informational message");

        ResponseHeader header = new ResponseHeader();
        header.setResponseDate("2025-06-18T15:00:00Z");
        header.setErrors(List.of(error));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(header);

        assertTrue(json.contains("\"responseDate\":\"2025-06-18T15:00:00Z\""));
        assertTrue(json.contains("\"level\":\"INFO\""));
        assertTrue(json.contains("\"code\":100"));
        assertTrue(json.contains("\"message\":\"Informational message\""));
    }

    @Test
    @DisplayName("Верификация десериализации в Json")
    void testDeserializationFromJson() throws JsonProcessingException {
        String json = """
                {
                  "responseDate": "2025-06-18T15:00:00Z",
                  "errors": [
                    {
                      "level": "ERROR",
                      "code": 500,
                      "message": "Internal Server Error"
                    }
                  ]
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        ResponseHeader header = mapper.readValue(json, ResponseHeader.class);

        assertEquals("2025-06-18T15:00:00Z", header.getResponseDate());
        assertNotNull(header.getErrors());
        assertEquals(1, header.getErrors().size());

        ErrorBody error = header.getErrors().get(0);
        assertEquals("ERROR", error.getLevel());
        assertEquals(500, error.getCode());
        assertEquals("Internal Server Error", error.getMessage());
    }
}