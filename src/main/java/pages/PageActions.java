package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static java.lang.Thread.sleep;

public class PageActions {

    //Storing the Web elements/objects
    By hamburgerMenu = By.id("nav-hamburger-menu");
    By electronics = By.xpath("//a[@class='hmenu-item'][@data-menu-id='5']");
    By television = By.xpath("//a[contains(text(),'Television & Video')]");
    By amazonStoreCheckbox = By.xpath("//li[@id ='p_n_is-global-store-asin/16354393011']");
    By microsoftCheckbox = By.xpath("//span[@class='a-size-base a-color-base'][contains(text(),'Microsoft')]");
    By featureMenu = By.xpath("//span[@class ='a-dropdown-prompt'][contains(text(),'Featured')]");
    By selectByPrice = By.xpath("//a[@id='s-result-sort-select_1']");
    By firstResult = By.xpath("//img[@class='s-image'][@src='https://m.media-amazon.com/images/I/5159B8W-xzL._AC_UL320_.jpg']");
    By productDetails = By.xpath("//div[@id='productOverview_feature_div']");

    //Declaring the drivers
    WebDriver driver;
    WebDriverWait wait;
    Actions cursor;

    //Initializing the drivers in constructor
    public PageActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        cursor = new Actions(driver);

    }

    //Method to click on the hamburger menu
    public void clickonHamburgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(hamburgerMenu));
        driver.findElement(hamburgerMenu).click();
    }

    //Method to click on Electronics
    public void clickonElectronics() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(electronics));
        driver.findElement(electronics).click();
        if (driver.findElement(television).isEnabled()) {
            sleep(3000);
            clickOnTelevision();
        } else {
            clickonHamburgerMenu();
            clickonElectronics();
            clickOnTelevision();
        }
    }

    //Method to click on the television
    public void clickOnTelevision() throws InterruptedException {
        cursor.moveToElement(driver.findElement(television)).click().build().perform();

    }

    //Method to click Amazon global store
    public void clickAmazonStore() throws InterruptedException {
        sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        driver.findElement(amazonStoreCheckbox).click();
    }

    //Method to click the Microsoft checkbox
    public void selectMicrosoft() throws InterruptedException {
        sleep(3000);
        cursor.moveToElement(driver.findElement(microsoftCheckbox)).click().build().perform();
    }

    //Method to click the feature dropdown
    public void clickFeatureDropdown() throws InterruptedException {
        sleep(3000);
        driver.findElement(featureMenu).click();
    }

    //Method to sort by price
    public void selectLowtoHighprice() {
        wait.until(ExpectedConditions.elementToBeClickable(selectByPrice));
        driver.findElement(selectByPrice).click();
    }

    //Method to click on the first result
    public void clickOnFirstResult() {
        wait.until(ExpectedConditions.elementToBeClickable(firstResult));
        driver.findElement(firstResult).click();
    }

    //Method to get the product details
    public String getProductDetailsText() {
        return driver.findElement(productDetails).getText();
    }

    //Method to get the title
    public String getTitle() {
        return driver.getTitle();
    }


}
