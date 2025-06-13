package services;  // Пакет для сервисов

import com.sun.net.httpserver.HttpServer;
import handlers.Handler;

import java.io.IOException;
import java.net.InetSocketAddress;

import settings.Settings;

public class HttpServiceRunner {

    private final String host;
    private final int port;

    public HttpServiceRunner() {
        this.host = Settings.getHost();
        this.port = Settings.getPort();
    }

    public void startServer() {
        try {
            // Привязываем сервер к хосту из настроек
            InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);

            // Создаем сервер
            HttpServer server = HttpServer.create(inetSocketAddress, 0);

            // Регистрируем обработчик для корневой страницы
            server.createContext("/", new Handler(host, port));

            // Запускаем сервер
            server.start();
            System.out.println("Server started at " + getHostPortString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getHostPortString() {
        return "http://" + host + ":" + port;
    }
}
