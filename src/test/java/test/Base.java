package test;

import builder.DriverBuilder;
import builder.DriverBuilderFromProperty;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Base {
    protected WebDriver driver;
    protected final String searchKey = "pixel 6 pro";

    @BeforeClass(alwaysRun = true)
    public void browserSetup() throws Exception {
        driver = DriverBuilderFromProperty.getDriver();
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void browserClose() {
        DriverBuilderFromProperty.closeDriver();
    }
}
