package test;

import builder.DriverBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import util.Drivers;

public class Base {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() throws Exception {
            driver = DriverBuilder.getDriver(Drivers.CHROME);
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void browserClose() { DriverBuilder.closeDriver();}
}
