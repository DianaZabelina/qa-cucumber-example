
import services.HttpServiceRunner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // Создаем и запускаем сервер
        HttpServiceRunner serverRunner = new HttpServiceRunner();
        serverRunner.startServer();
    }
}
