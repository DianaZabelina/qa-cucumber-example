package unitTests.responses.errors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import responses.errors.ErrorBody;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-тесты ErrorBody")
class ErrorBodyTest {

    @Test
    @DisplayName("Верификация Setters и Getters")
    void testSettersAndGetters() {
        ErrorBody error = new ErrorBody();

        error.setLevel("ERROR");
        error.setCode(123);
        error.setMessage("Something went wrong");

        assertEquals("ERROR", error.getLevel());
        assertEquals(123, error.getCode());
        assertEquals("Something went wrong", error.getMessage());
    }

    @Test
    @DisplayName("Верификация значений по умолчанию")
    void testDefaultValues() {
        ErrorBody error = new ErrorBody();

        assertNull(error.getLevel());
        assertEquals(0, error.getCode());
        assertNull(error.getMessage());
    }
}