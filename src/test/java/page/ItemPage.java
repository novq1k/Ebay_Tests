package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends AbstractPage {

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@data-testid='x-item-title']//span")
    private WebElement itemName;

    @FindBy(xpath = "//div[@class='vim x-atc-action overlay-placeholder']//a")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id = 'binBtn_btn_1']")
    private WebElement buyItNowButton;

    public ItemPage openPage(String url) {
        driver.get(url);
        return this;
    }

    public String getItemName() {
        return waitWebElementIsVisible(itemName).getText();
    }

    public CartPage clickAddToCart() {
        waitWebElementIsVisible(addToCartButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return new CartPage(driver);
    }

    //ItemPage
    public UserSelectionAlert clickBuyItNow() {
        waitWebElementIsVisible(buyItNowButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return new UserSelectionAlert(driver);
    }
}
