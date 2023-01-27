package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class CartTests extends Base {
    private final String searchKey = "Samsung Galaxy S10e SM-G970U - 128GB";

    @Test
    public void addItemToCart() {
        HomePage homePage = new HomePage(driver);
        String itemName = homePage
                .openPage()
                .search(searchKey)
                .selectItemFromSearchList(0)
                .getItemNameOnItemPreviewPage();

        Boolean hasItem = homePage
                .clickAddToCart()
                .findItemInCart(itemName);

        Assert.assertTrue(hasItem, "Item is not found in cart");
    }

    @Test
    public void deleteItemFromCart() {
        HomePage homePage = new HomePage(driver);
        addItemToCart();
        Boolean itemDeleted = homePage
                .deleteItemFromCart()
                .checkIfItemDeletedFromCart();

        Assert.assertTrue(itemDeleted, "Item is not deleted from cart");
    }
}
