package services;

import com.sun.net.httpserver.HttpServer;
import handlers.Handler;

import java.io.IOException;
import java.net.InetSocketAddress;

// Этот класс для unit-тестов
public class RealHttpServerWrapper implements ServerWrapper {

    private final HttpServer server;

    public RealHttpServerWrapper(String host, int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(host, port), 0);
    }

    @Override
    public void createContext(String path, Handler handler) {
        server.createContext(path, handler);
    }

    @Override
    public void start() {
        server.start();
    }
}