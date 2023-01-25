package builder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import util.Drivers;

public class DriverBuilder {

    private static WebDriver driver;

    private DriverBuilder() {
    }

    public static WebDriver getDriver(Drivers value) throws Exception {
        if (driver == null) {
            WebDriverManager webDriverManager = getWebDriverManager(value);
            driver = webDriverManager.create();
        }
        return driver;
    }

    public static WebDriverManager getWebDriverManager(Drivers value) throws Exception {
        WebDriverManager webDriverManager;
        switch (value) {
            case CHROME:
                webDriverManager = WebDriverManager.chromedriver();
                break;
            case EDGE:
                webDriverManager = WebDriverManager.edgedriver();
                break;
            case FIREFOX:
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
