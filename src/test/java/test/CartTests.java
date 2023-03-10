package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CartPage;
import page.HomePage;
import page.ItemPage;
import service.ItemCreator;

public class CartTests extends Base {

    @Test
    public void addItemToCart() {
        int itemIndex = 0;
        Item item = ItemCreator.withoutRequiredFields();

        HomePage homePage = new HomePage(driver);
        ItemPage itemPage = homePage
                .openPage()
                .search(item.getSearchKey())
                .selectItemFromSearchList(itemIndex);

        String itemName = itemPage
                .getItemName();

        Boolean hasItem = itemPage
                .clickAddToCart()
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

    @Test
    public void changeCartItemQuantity() {
        addItemToCart();

        CartPage cartPage = new CartPage(driver);

        Integer testQuantity = 2;

        Boolean quantityIsUpdated = cartPage
                .changeQuantity(testQuantity);

        Assert.assertTrue(quantityIsUpdated, "Quantity is not updated");
    }
}
