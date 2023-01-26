package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
    private WebElement addToCardButton;

    @FindBy(xpath = "//div[@class='ux-bin-nudge__guestCheckOut']//a")
    private WebElement checkOutAsGuestButton;

    @FindBy(xpath = "//*[@data-test-id='CONFIRM_AND_PAY_BUTTON']")
    private WebElement confirmAndPayButton;

    @FindBy(xpath = "//*[@data-test-id='cart-item-link']/span/span/label")
    private List<WebElement> cardItems;

    @FindBy(xpath = "//*[@data-testid='x-item-title']//span")
    private WebElement itemName;

    @FindBy(xpath = "//*[@data-test-id='cart-remove-item']")
    private WebElement removeItemFromCardButton;

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

    public HomePage clickAddToCard() {
        waitWebElementIsVisible(addToCardButton).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return this;
    }

    public Boolean findItemInCard(String value) {
        boolean isEqual = false;
        String[] testStringWords = value.split(" ");

        for (WebElement cardItem : cardItems) {
            String source = cardItem.getText();

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

    public HomePage deleteItemFromCard() {
        waitWebElementIsVisible(removeItemFromCardButton).click();

        return this;
    }

    public Boolean checkIfItemDeletedFromCard() {
        return confirmationOfDeleteAlert.isDisplayed();
    }

    public HomePage changeQuantity(Integer value){
       Select quantitiesDropdown =  new Select(driver.findElement(By.xpath
               ("//div[@class='grid__cell--one-half quantity-col']//*[@class='quantity']//*[@data-test-id = 'qty-dropdown']")));

       quantitiesDropdown.selectByValue(value.toString());

       return this;
    }

    public Boolean checkQuantityIsUpdated(Integer value){
        Select quantitiesDropdown =  new Select(driver.findElement(By.xpath
                ("//div[@class='grid__cell--one-half quantity-col']//*[@class='quantity']//*[@data-test-id = 'qty-dropdown']")));
        String selectedQuantityValue = quantitiesDropdown.getFirstSelectedOption().getText();

        return selectedQuantityValue.equals(value.toString());
        //TODO selectedQuantityValue for some reason equals 1 when should be 2
    }
}
