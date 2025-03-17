package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    // Static block to load properties file once
    static {
        try {
            properties = new Properties();
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file!", e);
        }
    }

    // Method to get property values
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
