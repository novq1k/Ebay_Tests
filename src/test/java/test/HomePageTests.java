package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class HomePageTests extends Base {

    @Test
    public void searchContainsSearchKey() {
        String searchKey = "Samsung Galaxy S10e";

        HomePage homePage = new HomePage(driver);
        Boolean result = homePage
                .openPage()
                .search(searchKey)
                .compareFoundValuesWithSearchKey(searchKey);
        Assert.assertTrue(result, "No search results found");
    }
}
