package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}