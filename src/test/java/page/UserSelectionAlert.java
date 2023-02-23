package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserSelectionAlert extends AbstractPage {

    public UserSelectionAlert(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='ux-bin-nudge__guestCheckOut']//a")
    private WebElement checkOutAsGuestButton;

    public CheckoutPage clickCheckOutAsGuest() {
        checkOutAsGuestButton.click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return new CheckoutPage(driver);
    }
}
