package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class HomePageTests extends Base {
    private final String searchKey = "Samsung Galaxy S10e SM-G970U - 128GB";

    @Test
    public void searchContainsSearchKey() {
        HomePage homePage = new HomePage(driver);
        Boolean searchResultsContainSearchKey = homePage
                .openPage()
                .search(searchKey)
                .compareSearchResultsWithSearchKey(searchKey);

        Assert.assertTrue(searchResultsContainSearchKey, "No search results are found or mismatch to search key");
    }
}
