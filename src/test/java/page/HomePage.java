package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.regex.Pattern;

public class HomePage extends AbstractPage {
    private static final String PAGE_URL = "https://www.ebay.com/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    @FindBy(xpath = "//*[@class = 'gh-tb ui-autocomplete-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='srp-results srp-list clearfix']//*[@class='s-item__info clearfix']//div[@class='s-item__title']/span")
    private List<WebElement> searchResult;

    @FindBy(xpath = "//*[@id = 'binBtn_btn_1']")
    private WebElement buyItNowButton;

    @FindBy(xpath = "//div[@class='vim x-atc-action overlay-placeholder']//a")
    private WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='ux-bin-nudge__guestCheckOut']//a")
    private WebElement checkOutAsGuestButton;

    @FindBy(xpath = "//*[@data-test-id='CONFIRM_AND_PAY_BUTTON']")
    private WebElement confirmAndPayButton;

    @FindBy(xpath = "//*[@data-test-id='cart-item-link']/span/span/label")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//*[@data-testid='x-item-title']//span")
    private WebElement itemName;

    @FindBy(xpath = "//*[@data-test-id='cart-remove-item']")
    private WebElement removeItemFromCartButton;

    @FindBy(xpath = "//*[@data-test-id='page-alerts']//a")
    private WebElement confirmationOfDeleteAlert;

    public HomePage search(String text) {
        new Actions(driver)
                .sendKeys(searchInput, text)
                .click(searchButton)
                .build()
                .perform();

        return this;
    }

    public Boolean compareSearchResultsWithSearchKey(String value) {
        boolean isEqual = false;
        String[] testStringWords = value.split(" ");

        for (WebElement webElement : searchResult) {
            String source = webElement.getText();

            for (String testWord : testStringWords) {
                isEqual = Pattern.compile(Pattern.quote(testWord), Pattern.CASE_INSENSITIVE).matcher(source).find();

                if (isEqual) {
                    break;
                }
            }
        }

        return isEqual;
    }

    public HomePage selectItemFromSearchList(int index) {
        //select item
        searchResult.get(index).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return this;
    }

    public HomePage clickBuyItNow() {
        waitWebElementIsVisible(buyItNowButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return this;
    }

    public HomePage clickCheckOutAsGuest() {
        waitWebElementIsVisible(checkOutAsGuestButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return this;
    }

    public Boolean checkConfirmAndPayButtonDisabled() {
        return confirmAndPayButton.isDisplayed();
    }

    public HomePage clickAddToCart() {
        waitWebElementIsVisible(addToCartButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return this;
    }

    public Boolean findItemInCart(String value) {
        boolean isEqual = false;
        String[] testStringWords = value.split(" ");

        for (WebElement cartItem : cartItems) {
            String source = cartItem.getText();

            for (String testWord : testStringWords) {
                isEqual = Pattern.compile(Pattern.quote(testWord), Pattern.CASE_INSENSITIVE).matcher(source).find();

                if (isEqual) {
                    break;
                }
            }
        }

        return isEqual;
    }

    public String getItemNameOnItemPreviewPage() {
        return waitWebElementIsVisible(itemName).getText();
    }

    public HomePage deleteItemFromCart() {
        waitWebElementIsVisible(removeItemFromCartButton).click();

        return this;
    }

    public Boolean checkIfItemDeletedFromCart() {
        return confirmationOfDeleteAlert.isDisplayed();
    }
}
