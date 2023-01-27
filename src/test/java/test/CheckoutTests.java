package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;

public class CheckoutTests extends Base {
    private final String searchKey = "Samsung Galaxy S10e SM-G970U - 128GB";

    @Test
    public void checkoutAsGuestWithoutSetRequiredFields() {
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
