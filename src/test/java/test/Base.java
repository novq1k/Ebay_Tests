package test;

import builder.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import builder.Drivers;

public class Base {
    protected WebDriver driver;
    protected final String searchKey = "pixel 6 pro";

    @BeforeClass(alwaysRun = true)
    public void browserSetup() throws Exception {
        driver = DriverBuilder.getDriver(Drivers.CHROME);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void browserClose() {
        DriverBuilder.closeDriver();
    }
}
