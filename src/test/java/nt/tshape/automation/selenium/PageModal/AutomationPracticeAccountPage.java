package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.Customer_Information;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutomationPracticeAccountPage extends ActionManager {
    private final WebDriverWait wait;
    private final TestContext testContext;
    private final Customer_Information customerInformation;
    //locator
    private final String linkButtonXPathLocatorByName = "//span[normalize-space() = '%s']//parent::a";
    private final String titleCheckBoxLocatorById = "//input[@id='%s']";
    private final String personalInfoTextFieldLocatorByName = "//label[normalize-space(text())='%s']//parent::div//child::input";
    private final String dobDropDownFieldLocatorByXPathIdName = "//div[@id='%s']";
    private final String addressAliasLocatorByValue = "//h3[contains (@class,'page-subheading') and normalize-space(text())='%s']";
    private final String addressInfoLocatorByFieldName = "//span[normalize-space() = '%s']";
    public WebDriver driver;

    public AutomationPracticeAccountPage(WebDriver driver, WebDriverWait wait, TestContext testContext) {
        super(driver, wait, testContext);
        this.driver = driver;
        this.wait = wait;
        this.testContext = testContext;
        this.customerInformation = testContext.getCustomerInformation();
    }


    public AutomationPracticeAccountPage clickLinkButtonByName(String buttonName) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(linkButtonXPathLocatorByName, buttonName))));
        driver.findElement(By.xpath(String.format(linkButtonXPathLocatorByName, buttonName)))
                .click();
        return this;
    }

    public AutomationPracticeAccountPage verifyPersonalInfoDataByFieldName(String fieldName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(personalInfoTextFieldLocatorByName, fieldName))));
        WebElement fieldToBeVerify = driver.findElement(By.xpath(String.format(personalInfoTextFieldLocatorByName, fieldName)));
        String actualValue = fieldToBeVerify.getAttribute("value");
        String expectedValue = customerInformation.getDataByFieldName(fieldName);
        Assert.assertEquals(actualValue, expectedValue, "Field: " + fieldName + " does not display correct value");
        return this;
    }

    public AutomationPracticeAccountPage verifyAccountLinkButtonWithAccountNameAvailable() {
        String accountName = customerInformation.getFirstName() + " " + customerInformation.getLastName();
        WebElement headerLinkWithAccountName = driver.findElement(By.xpath(String.format(linkButtonXPathLocatorByName, accountName)));
        Assert.assertTrue(headerLinkWithAccountName.isDisplayed(), "Link button with account name on menu does not displace as expected");
        return this;
    }

    public AutomationPracticeAccountPage verifyTitleCheckBoxDisplayCorrectlyById(String titleFieldId, Boolean expectedResult) {
        WebElement titleCheckBox = driver.findElement(By.xpath(String.format(titleCheckBoxLocatorById, titleFieldId)));
        Boolean actualValue = titleCheckBox.isSelected();
        Assert.assertEquals(actualValue, expectedResult, "Field: " + titleFieldId + " does not have same value with saved data");
        return this;
    }

    public AutomationPracticeAccountPage verifyDropDownFieldByIdDisplayCorrectValue(String fieldName) {
        WebElement dropDownFieldToBeVerify = driver.findElement(By.xpath(String.format(dobDropDownFieldLocatorByXPathIdName, fieldName)));
        String actualValue = dropDownFieldToBeVerify.getText().substring(0, dropDownFieldToBeVerify.getText().indexOf("-")).trim();
        String expectedValue = customerInformation.getDataByFieldName(fieldName);
        Assert.assertEquals(actualValue, expectedValue, "Field: " + fieldName + " does not have date same with saved one");
        return this;
    }

    public AutomationPracticeAccountPage verifyAddressAliasHeaderDisplayCorrectValue(String expectedValue) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(addressAliasLocatorByValue, expectedValue))));
        WebElement addressAliasHeader = driver.findElement(By.xpath(String.format(addressAliasLocatorByValue, expectedValue)));
        String actualValue = addressAliasHeader.getText();
        Assert.assertEquals(actualValue.compareToIgnoreCase(expectedValue), 0, "Address " + expectedValue + " does not exist in address panel");
        return this;
    }

    public AutomationPracticeAccountPage verifyExistingOfAddressInfoByFieldName(String fieldName) {
        String expectedValue = customerInformation.getDataByFieldName(fieldName);
        expectedValue = (fieldName.equals("City")) ? expectedValue + "," : expectedValue;
        WebElement addressInfoField = driver.findElement(By.xpath(String.format(addressInfoLocatorByFieldName, expectedValue)));
        Assert.assertTrue(addressInfoField.isDisplayed(), "Field :" + fieldName + " not displayed on the address panel");
        return this;
    }
}
