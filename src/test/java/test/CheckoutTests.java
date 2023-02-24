package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CheckoutPage;
import page.HomePage;
import page.ItemPage;
import page.UserSelectionAlert;
import service.ItemCreator;

public class CheckoutTests extends Base {

    @Test
    public void checkoutAsGuestWithoutSetRequiredFields() {
        int itemIndex = 0;
        Item item = ItemCreator.withoutRequiredFields();

        HomePage homePage = new HomePage(driver);
        ItemPage itemPage = homePage
                .openPage()
                .search(item.getSearchKey())
                .selectItemFromSearchList(itemIndex);

        UserSelectionAlert userSelectionAlert = itemPage
                .clickBuyItNow();

        CheckoutPage checkoutPage = userSelectionAlert
                .clickCheckOutAsGuest();

        Boolean confirmAndPayButtonDisabled = checkoutPage
                .checkConfirmAndPayButtonDisabled();

        Assert.assertTrue(confirmAndPayButtonDisabled, "Confirm And Pay button is not disabled");
    }
}
