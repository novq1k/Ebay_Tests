package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage extends AbstractPage {
    private static final String PAGE_URL = "https://www.ebay.com/";
    private final Logger logger = LogManager.getRootLogger();

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class = 'gh-tb ui-autocomplete-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='srp-results srp-list clearfix']//*[@class='s-item__info clearfix']//div[@class='s-item__title']/span")
    private List<WebElement> searchResult;

    public HomePage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public HomePage search(String text) {
        new Actions(driver)
                .sendKeys(searchInput, text)
                .click(searchButton)
                .build()
                .perform();
        logger.info("Search item with name: [" + text + "]");
        return this;
    }

    public Boolean compareSearchResultsWithSearchKey(String value) {
        boolean isExist = false;
        String[] testWords = value.split(" ");

        for (WebElement webElement : searchResult) {
            String source = webElement.getText();

            for (String testWord : testWords) {
                isExist = Pattern.compile(Pattern.quote(testWord), Pattern.CASE_INSENSITIVE).matcher(source).find();

                if (isExist) {
                    break;
                }
            }
        }

        return isExist;
    }

    public ItemPage selectItemFromSearchList(int index) {
        //select item
        searchResult.get(index).click();

        // Switch to new window opened
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        return new ItemPage(driver);
    }
}
