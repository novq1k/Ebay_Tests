package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected static final int DEFAULT_TIMEOUT = 30;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitWebElementIsVisible(WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions
                        .visibilityOf(webElement));
    }
}
