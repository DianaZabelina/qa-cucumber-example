package responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"host", "port"}) // Задаем порядок ключей
public class HostPortJson {
    private final String host;
    private final int port;

    public HostPortJson(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
