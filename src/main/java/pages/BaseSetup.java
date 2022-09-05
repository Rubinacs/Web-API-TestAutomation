package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseSetup {

    public static WebDriver driver;
    String url = "https://www.amazon.com/";
    public static ExtentTest logger;
    public static ExtentReports report;

    @BeforeClass(alwaysRun = true)
    public void setup(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        context.setAttribute("WebDriver", driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
