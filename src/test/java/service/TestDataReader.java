package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));
    //replace System.getProperty("environment") to name of properties file for run not from console

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}