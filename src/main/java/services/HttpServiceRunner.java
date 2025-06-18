package services;

import handlers.Handler;
import settings.Settings;

import java.io.IOException;

public class HttpServiceRunner {

    private final String host;
    private final int port;
    private final ServerWrapper server;

    // Этот метод для прода
    public HttpServiceRunner() throws IOException {
        this(Settings.getHost(), Settings.getPort(),
                new RealHttpServerWrapper(Settings.getHost(), Settings.getPort()));
    }

    // Этот метод для unit-тестов
    public HttpServiceRunner(String host, int port, ServerWrapper server) {
        this.host = host;
        this.port = port;
        this.server = server;
    }

    public void startServer() {
        server.createContext("/", new Handler(host, port));
        server.start();
        System.out.println("Server started at http://" + host + ":" + port);
    }
}