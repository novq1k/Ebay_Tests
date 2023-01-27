package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import page.ItemPage;

public class CartTests extends Base {
    private final String searchKey = "Samsung Galaxy S10e SM-G970U - 128GB";

    @Test
    public void addItemToCart() {
        int itemIndex = 0;

        HomePage homePage = new HomePage(driver);
        ItemPage itemPage = homePage
                .openPage()
                .search(searchKey)
                .selectItemFromSearchList(itemIndex);

        String itemName = itemPage
                .getItemName();

        CartPage cartPage = itemPage.clickAddToCart();

        Boolean hasItem = cartPage
                .findItemInCart(itemName);

        Assert.assertTrue(hasItem, "Item is not found in cart");
    }

    @Test
    public void deleteItemFromCart() {
        addItemToCart();

        CartPage cartPage = new CartPage(driver);

        Boolean itemDeleted = cartPage
                .deleteItemFromCart()
                .checkIfItemDeletedFromCart();

        Assert.assertTrue(itemDeleted, "Item is not deleted from cart");
    }
}
