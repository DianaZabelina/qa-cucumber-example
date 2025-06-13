package utils;

import java.io.File;
import java.io.IOException;

public abstract class AllureUtils {


    public static void сleanDirectory() {
        File resultsDir = new File("target/allure-results");
        deleteDirectoryContents(resultsDir);
    }

    private static void deleteDirectoryContents(File dir) {
        if (dir.exists() && dir.isDirectory()) {
            File[] contents = dir.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    if (file.isDirectory()) {
                        deleteDirectoryContents(file);
                    }
                    file.delete();
                }
            }
        }
    }

    public static void startAllureReport() {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Указываем команду
        processBuilder.command("cmd.exe", "/c", "allure serve target/allure-results");

        try {
            processBuilder.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
