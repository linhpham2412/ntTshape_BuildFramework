package nt.tshape;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
    public WebDriver driver;
    public WebDriverWait wait;
    public TestContext testContext;
    public Customer_Information customerInformation;

    @Parameters({"browser"})
    @BeforeClass
    public void loadWebDriver(@Optional("chrome") String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\OneDrive\\Documents\\LinhPham\\TShapeTraining\\Webdriver\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            driver = new ChromeDriver(options);
        }
        if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\Admin\\OneDrive\\Documents\\LinhPham\\TShapeTraining\\Webdriver\\geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions()
                    .setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe")
                    .setAcceptInsecureCerts(true);
            driver = new FirefoxDriver(options);
        }
        driver
                .manage()
                .window()
                .maximize();
    }

    @BeforeClass
    public void setUp() {
        wait = new WebDriverWait(driver, Constant.SHORT_TIME);
        testContext = new TestContext();
        customerInformation = new Customer_Information();
        testContext.setCustomerInformation(customerInformation);
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
