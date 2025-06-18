package unitTests.services;

import handlers.Handler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.HttpServiceRunner;
import services.ServerWrapper;

import static org.mockito.Mockito.*;

@DisplayName("Unit-тесты HttpServiceRunner")
class HttpServiceRunnerTest {

    @Test
    @DisplayName("Верификация запуска HTTP-сервера")
    void testStartServer_shouldRegisterHandlerAndStart() {

        ServerWrapper mockServer = mock(ServerWrapper.class);
        HttpServiceRunner runner = new HttpServiceRunner("localhost", 8080, mockServer);

        runner.startServer();

        verify(mockServer).createContext(eq("/"), any(Handler.class));
        verify(mockServer).start();
    }
}