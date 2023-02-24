package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("qa");
    //TODO change qa value to System.getProperty("environment") while run from console

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}