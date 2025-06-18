package services;

import handlers.Handler;

// Этот интерфейс для unit-тестов
public interface ServerWrapper {
    void createContext(String path, Handler handler);
    void start();
}