package settings;

import java.io.IOException;
import java.util.Properties;

public abstract class Settings {

    private static final Properties prop = new Properties();

    static {
        try {
            prop.load(Settings.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String loadProperty(String key) {
        return prop.getProperty(key).trim();
    }

    public static String getSwagLabsUrl() {
        return loadProperty("swagLabsUrl");
    }

    public static String getSwagLabsUserName() {
        return loadProperty("swagLabsUserName");
    }

    public static String getSwagLabsPassword() {
        return loadProperty("swagLabsPassword");
    }

}