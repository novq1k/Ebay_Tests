package test;

import builder.DriverBuilderFromProperty;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.TestListener;

@Listeners({TestListener.class})
public class Base {
    protected WebDriver driver;
    protected final String searchKey = "pixel 6 pro";

    @BeforeClass(alwaysRun = true)
    public void browserSetup() {
        driver = DriverBuilderFromProperty.getDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void browserClose() {
        DriverBuilderFromProperty.closeDriver();
    }
}
