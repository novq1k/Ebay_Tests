package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import service.ItemCreator;

public class HomePageTests extends Base {

    @Test
    public void searchContainsSearchKey() {
        Item item = ItemCreator.withoutRequiredFields();

        HomePage homePage = new HomePage(driver);
        Boolean searchResultsContainSearchKey = homePage
                .openPage()
                .search(item.getSearchKey())
                .compareSearchResultsWithSearchKey(item.getSearchKey());

        Assert.assertTrue(searchResultsContainSearchKey, "No search results are found or mismatch to search key");
    }
}
