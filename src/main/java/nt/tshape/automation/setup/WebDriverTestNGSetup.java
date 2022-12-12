package nt.tshape.automation.setup;

import nt.tshape.automation.selenium.Constant;
import nt.tshape.automation.selenium.Customer_Information;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.selenium.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class WebDriverTestNGSetup {
    public WebDriverWait wait;
    public TestContext testContext;
    public Customer_Information customerInformation;

    public WebDriver getDriver(){
        return WebDriverManager.getDriver();
    }

    @AfterClass
    public static void afterClass() {
        WebDriverManager.getDriver().quit();
    }

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClassSetUp(@Optional("chrome") String browser) {
        wait = new WebDriverWait(getDriver(), Constant.SHORT_TIME);
        testContext = new TestContext();
        customerInformation = new Customer_Information();
        customerInformation = testContext.getCustomerInformation();
        WebDriverManager.iniDriver(browser);
        WebDriverManager.getDriver();
    }
}
