package unitTests.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import responses.HostPortJson;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-tests HostPortJson")
class HostPortJsonTest {

    @Test
    @DisplayName("Verifying Getters")
    void testGetters() {
        HostPortJson hostPort = new HostPortJson("localhost", 8080);

        assertEquals("localhost", hostPort.getHost());
        assertEquals(8080, hostPort.getPort());
    }

    @Test
    @DisplayName("Verifying serialization order")
    void testJsonSerializationOrder() throws JsonProcessingException {
        HostPortJson hostPort = new HostPortJson("127.0.0.1", 1234);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(hostPort);

        assertTrue(json.contains("\"host\":\"127.0.0.1\""));
        assertTrue(json.contains("\"port\":1234"));
        assertTrue(json.indexOf("host") < json.indexOf("port"),
                "The 'host' field should come before 'port'");
    }
}

