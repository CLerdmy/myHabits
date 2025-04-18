package dev.clerdmy.util;

import java.io.InputStream;
import java.util.Properties;

public class Configurator {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configurator.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}