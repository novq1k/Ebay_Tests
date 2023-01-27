package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends AbstractPage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-test-id='CONFIRM_AND_PAY_BUTTON']")
    private WebElement confirmAndPayButton;

    public CheckoutPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public Boolean checkConfirmAndPayButtonDisabled() {
        return confirmAndPayButton.isDisplayed();
    }
}
