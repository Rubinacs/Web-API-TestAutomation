package webautomation;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BaseSetup;
import pages.PageActions;


public class ProductDetailsTest extends BaseSetup {

    @Test(priority = 1)
    public void verifyTitle() {
        PageActions homePage = new PageActions(driver);
        Assert.assertEquals(homePage.getTitle(), "Amazon.com. Spend less. Smile more.");
    }

    @Test(priority = 2)
    public void getProductDetailsScenario() throws InterruptedException {
        PageActions homePage = new PageActions(driver);
        homePage.clickonHamburgerMenu();
        homePage.clickonElectronics();
        homePage.clickAmazonStore();
        homePage.selectMicrosoft();
        homePage.clickFeatureDropdown();
        homePage.selectLowtoHighprice();
        homePage.clickOnFirstResult();
        homePage.getProductDetailsText();
        System.out.println(homePage.getProductDetailsText());

    }
}
