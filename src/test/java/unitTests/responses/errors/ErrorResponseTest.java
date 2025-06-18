package unitTests.responses.errors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import responses.ResponseHeader;
import responses.errors.ErrorBody;
import responses.errors.ErrorResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-tests ErrorResponse")
class ErrorResponseTest {

    @Test
    @DisplayName("Verifying Setters and Getters")
    void testSetAndGetResponseHeader() {
        ResponseHeader header = new ResponseHeader();
        header.setResponseDate("2025-06-18T12:00:00Z");

        ErrorBody error = new ErrorBody();
        error.setLevel("ERROR");
        error.setCode(500);
        error.setMessage("Internal Server Error");

        header.setErrors(List.of(error));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponseHeader(header);

        ResponseHeader result = errorResponse.getResponseHeader();
        assertNotNull(result);
        assertEquals("2025-06-18T12:00:00Z", result.getResponseDate());

        ErrorBody resultError = result.getErrors().get(0);
        assertEquals("ERROR", resultError.getLevel());
        assertEquals(500, resultError.getCode());
        assertEquals("Internal Server Error", resultError.getMessage());
    }

    @Test
    @DisplayName("Verifying default values")
    void testDefaultValue() {
        ErrorResponse errorResponse = new ErrorResponse();
        assertNull(errorResponse.getResponseHeader(), "responseHeader должен быть null по умолчанию");
    }

    @Test
    @DisplayName("Verifying serialization to Json")
    void testSerializationToJson() throws JsonProcessingException {
        ErrorBody error = new ErrorBody();
        error.setLevel("WARNING");
        error.setCode(400);
        error.setMessage("Bad request");

        ResponseHeader header = new ResponseHeader();
        header.setResponseDate("2025-06-18T14:30:00Z");
        header.setErrors(List.of(error));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResponseHeader(header);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(errorResponse);

        assertTrue(json.contains("\"responseDate\":\"2025-06-18T14:30:00Z\""));
        assertTrue(json.contains("\"code\":400"));
        assertTrue(json.contains("\"message\":\"Bad request\""));
    }

    @Test
    @DisplayName("Verifying deserialization to Json")
    void testDeserializationFromJson() throws JsonProcessingException {
        String json = """
                {
                  "responseHeader": {
                    "responseDate": "2025-06-18T15:00:00Z",
                    "errors": [
                      {
                        "level": "ERROR",
                        "code": 503,
                        "message": "Service Unavailable"
                      }
                    ]
                  }
                }
                """;

        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = mapper.readValue(json, ErrorResponse.class);

        assertNotNull(errorResponse.getResponseHeader());
        assertEquals("2025-06-18T15:00:00Z", errorResponse.getResponseHeader().getResponseDate());
        assertEquals(1, errorResponse.getResponseHeader().getErrors().size());

        ErrorBody error = errorResponse.getResponseHeader().getErrors().get(0);
        assertEquals("ERROR", error.getLevel());
        assertEquals(503, error.getCode());
        assertEquals("Service Unavailable", error.getMessage());
    }
}