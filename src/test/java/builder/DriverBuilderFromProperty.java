package builder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverBuilderFromProperty {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();

    private DriverBuilderFromProperty() {
    }

    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                WebDriverManager webDriverManager = getWebDriverManager();
                driver = webDriverManager.create();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
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
