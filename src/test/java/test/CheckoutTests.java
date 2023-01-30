package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CheckoutPage;
import page.HomePage;
import page.ItemPage;
import page.UserSelectionAlert;

public class CheckoutTests extends Base {

    @Test
    public void checkoutAsGuestWithoutSetRequiredFields() {
        int itemIndex = 0;

        HomePage homePage = new HomePage(driver);
        ItemPage itemPage = homePage
                .openPage()
                .search(searchKey)
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
