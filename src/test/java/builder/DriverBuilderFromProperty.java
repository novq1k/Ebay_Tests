package builder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DriverBuilderFromProperty {

    private static WebDriver driver;

    private DriverBuilderFromProperty() {
    }

    public static WebDriver getDriver() throws Exception {
        if (driver == null) {
            WebDriverManager webDriverManager = getWebDriverManager();
            driver = webDriverManager.create();
        }

        return driver;
    }

    private static WebDriverManager getWebDriverManager() throws Exception {
        WebDriverManager webDriverManager;
        switch (System.getProperty("browser")) {
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
