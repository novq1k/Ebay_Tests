package test;

import builder.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.Drivers;

public class Base {
    protected WebDriver driver;

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
