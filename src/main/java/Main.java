
import services.HttpServiceRunner;

public class Main {

    public static void main(String[] args) {

        // Создаем и запускаем сервер
        HttpServiceRunner serverRunner = new HttpServiceRunner();
        serverRunner.startServer();
    }
}
