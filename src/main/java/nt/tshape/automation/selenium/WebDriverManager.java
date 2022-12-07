package nt.tshape.automation.selenium;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driverThread;

    public static void iniDriver(String browser) {
        if (getDriver() != null) {
            WebDriver driver = WebDriverCreator.createLocalDriver(browser);
            driverThread.set(driver);
        }
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }
}
