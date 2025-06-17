package unitTests.settings;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import settings.Settings;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Юнит-тесты Settings")
class SettingsTest {

    @Test
    @DisplayName("Проверка наличия localhost")
    void testGetHost_shouldReturnCorrectHost() {
        String host = Settings.getHost();
        assertEquals("unit-localhost-test", host);
    }

    @Test
    @DisplayName("Проверка наличия порта 8080")
    void testGetPort_shouldReturnCorrectPort() {
        int port = Settings.getPort();
        assertEquals(80808080, port);
    }
}