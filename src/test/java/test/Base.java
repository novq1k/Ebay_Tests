package test;

import builder.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners({TestListener.class})
public class Base {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void browserSetup() {
        driver = DriverBuilder.getDriver();

        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void browserClose() {
        DriverBuilder.closeDriver();
    }
}
