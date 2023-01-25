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

    public HomePage search(String text) {
        new Actions(driver)
                .sendKeys(searchInput, text)
                .click(searchButton)
                .build()
                .perform();

        return this;
    }

    public Boolean compareFoundValuesWithSearchKey(String value){
        boolean isEqual = false;

        for (WebElement webElement : searchResult) {
            String source = webElement.getText();
            isEqual = Pattern.compile(Pattern.quote(value), Pattern.CASE_INSENSITIVE).matcher(source).find();
        }

        return isEqual;
    }
}
