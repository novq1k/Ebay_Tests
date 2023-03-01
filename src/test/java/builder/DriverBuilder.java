package builder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DriverBuilder {

    private static WebDriver driver;

    private DriverBuilder() {
    }

    public static WebDriver getDriver() {

        if (driver == null) {
            WebDriverManager webDriverManager = null;
            try {
                webDriverManager = getWebDriverManager(System.getProperty("browser"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            driver = webDriverManager.create();
        }

        return driver;
    }

    private static WebDriverManager getWebDriverManager(String browser) throws Exception {
        WebDriverManager webDriverManager;
        switch (browser) {
            case "chrome":
                webDriverManager = WebDriverManager.chromedriver();
                break;
            case "edge":
                webDriverManager = WebDriverManager.edgedriver();
                break;
            case "firefox":
                webDriverManager = WebDriverManager.firefoxdriver();
                break;
            default:
                throw new Exception("incorrect value for driver");
        }

        return webDriverManager;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
