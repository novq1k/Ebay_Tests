package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.regex.Pattern;

public class CartPage extends AbstractPage {
    private static final String PAGE_URL = "https://cart.payments.ebay.com";

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@data-test-id='cart-item-link']/span/span/label")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//*[@data-test-id='cart-remove-item']")
    private WebElement removeItemFromCartButton;

    @FindBy(xpath = "//*[@data-test-id='page-alerts']//a")
    private WebElement confirmationOfDeleteAlert;

    public CartPage openPage() {
        driver.get(PAGE_URL);
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

    public CartPage deleteItemFromCart() {
        waitWebElementIsVisible(removeItemFromCartButton).click();

        return this;
    }

    public Boolean checkIfItemDeletedFromCart() {
        return confirmationOfDeleteAlert.isDisplayed();
    }

    public Boolean changeQuantity(Integer value) {

        Select quantitiesDropdown = new Select(driver.findElement(By.xpath
                ("//div[@class='grid__cell--one-half quantity-col']//*[@class='quantity']//*[@data-test-id = 'qty-dropdown']")));

        quantitiesDropdown.selectByValue(value.toString());
        //refresh page to update dom
        driver.navigate().refresh();

        List<WebElement> selectedOpts = driver.findElements(
                By.cssSelector("select[data-test-id='qty-dropdown'] option:checked"));

        String text = selectedOpts.get(0).getText();

        return text.equals(value.toString());
    }
}
