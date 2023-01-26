package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class HomePageTests extends Base {

    @Test
    public void searchContainsSearchKey() {
        String searchKey = "Samsung Galaxy S10e";

        HomePage homePage = new HomePage(driver);
        Boolean searchResultsContainSearchKey = homePage
                .openPage()
                .search(searchKey)
                .compareSearchResultsWithSearchKey(searchKey);

        Assert.assertTrue(searchResultsContainSearchKey, "No search results are found or mismatch to search key");
    }

    @Test
    public void addItemToCard() {
        String searchKey = "Samsung Galaxy S10e";

        HomePage homePage = new HomePage(driver);
        String itemName = homePage
                .openPage()
                .search(searchKey)
                .selectItemFromSearchList(0)
                .getItemNameOnItemPreviewPage();

        Boolean hasItem = homePage
                .clickAddToCard()
                .findItemInCard(itemName);

        Assert.assertTrue(hasItem, "Item is not found in card");
    }

    @Test
    public void deleteItemFromCard() {
        HomePage homePage = new HomePage(driver);
        addItemToCard();
        Boolean itemDeleted = homePage
                .deleteItemFromCard()
                .checkIfItemDeletedFromCard();

        Assert.assertTrue(itemDeleted, "Item is not deleted from card");
    }

    @Test
    public void changeCardItemQuantity(){
        HomePage homePage = new HomePage(driver);
        addItemToCard();

        Integer testQuantity = 2;

        Boolean quantityIsUpdated = homePage
                .changeQuantity(testQuantity)
                .checkQuantityIsUpdated(testQuantity);

        Assert.assertTrue(quantityIsUpdated, "Quantity is not updated");
    }

    @Test
    public void buyAsGuestWithoutSetRequiredFields() {
        String searchKey = "Samsung Galaxy S10e";

        HomePage homePage = new HomePage(driver);
        Boolean confirmAndPayButtonDisabled = homePage
                .openPage()
                .search(searchKey)
                .selectItemFromSearchList(0)
                .clickBuyItNow()
                .clickCheckOutAsGuest()
                .checkConfirmAndPayButtonDisabled();

        Assert.assertTrue(confirmAndPayButtonDisabled, "Confirm And Pay button is not disabled");
    }

}
