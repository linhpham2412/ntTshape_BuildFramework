package nt.tshape.automation.selenium.PageModal;

import nt.tshape.automation.selenium.ActionManager;
import nt.tshape.automation.selenium.Constant;
import nt.tshape.automation.selenium.Customer_Information;
import nt.tshape.automation.selenium.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class AutomationPracticeIndexPage extends ActionManager {
    private final WebDriverWait wait;
    private final Customer_Information customerInformation;
    private final TestContext testContext;
    //locator
    private final String linkButtonXPathLocatorByName = "xpath=//a[normalize-space(text()) = '%s']";
    private final String postFixSelect = "//select";
    private final String buttonXPathLocatorByName = "xpath=//span[normalize-space() = '%s']//parent::button";
    private final String dobDropDownFieldLocatorByXPathIdName = "xpath=//div[@id='%s']";
    private final String linkMenuListXPathLocatorByName = "xpath=//a[contains (@class,'sf-with-ul') and normalize-space(text()) = '%s']";
    private final String personalInfoCheckBoxLocatorByText = "xpath=//label[normalize-space()='%s']//preceding-sibling::div";
    private final String emailCreateTextBox = "id=email_create";
    private final String titleCheckBoxLocatorById = "xpath=//input[@id='%s']";
    private final String personalInfoTextFieldLocatorByName = "xpath=//label[normalize-space(text())='%s']//parent::div//child::input";
    private final String yourAddressTextFieldLocatorByName = "xpath=//label[normalize-space(text())='%s']//parent::p//child::input";
    private final String additionalInformationTextareaXPathLocator = "xpath=//textarea[@id='other']";
    private final String linkSubMenuToMergeWithMenuXPathLocatorByName = "//following::ul[contains(@class,'submenu-container')]//a[normalize-space(text()) = '%s']";
    private final String productXPathLocatorByIndex = "xpath=//ul[contains (@class,'product_list')]//li[contains (@class,'ajax_block_product')][%s]";
    private final String productDetailXPathLocatorByClassName = "//div[@class='right-block']//*[@class='%s']";
    private final String searchTextField = "id=search_query_top";
    public WebDriver driver;


    //constructor
    public AutomationPracticeIndexPage(WebDriver driver, WebDriverWait wait, TestContext testContext) {
        super(driver, wait, testContext);
        this.driver = driver;
        this.wait = wait;
        this.testContext = testContext;
        this.customerInformation = testContext.getCustomerInformation();
    }

    public AutomationPracticeIndexPage goToPageByURL(String locationURL) {
        driver.get(locationURL);
        return this;
    }

    public AutomationPracticeIndexPage clickLinkButtonByName(String linkName) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(String.format(linkButtonXPathLocatorByName, linkName))));
        WebElement linkToBeClick = findElement(String.format(linkButtonXPathLocatorByName, linkName));
        linkToBeClick.click();
        return this;
    }

    public AutomationPracticeIndexPage clickButtonByName(String buttonName) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(String.format(buttonXPathLocatorByName, buttonName))));
        WebElement buttonToBeClick = findElement(String.format(buttonXPathLocatorByName, buttonName));
        buttonToBeClick.click();
        return this;
    }

    public AutomationPracticeIndexPage inputToEmailAddressWithEmail(String emailAddress) {
        wait.until(ExpectedConditions.visibilityOf(findElement(emailCreateTextBox)));
        findElement(emailCreateTextBox).sendKeys(emailAddress);
        customerInformation.saveDataByFieldName("Email", emailAddress);
        return this;
    }

    public AutomationPracticeIndexPage selectDropDownFieldByIdWithValue(String fieldId, String fieldValue) {
        wait.until(ExpectedConditions.elementToBeClickable(findElement(String.format(dobDropDownFieldLocatorByXPathIdName, fieldId))));
        Select dropDownField = new Select(findElement(String.format(dobDropDownFieldLocatorByXPathIdName, fieldId) + postFixSelect));
        dropDownField.selectByValue(fieldValue);
        customerInformation.saveDataByFieldName(fieldId, fieldValue);
        if (fieldId.equals(Constant.ADDRESS_STATE_ID)) {
            List<WebElement> options = dropDownField.getOptions();
            options.forEach(option -> customerInformation.setListOfState(option.getAttribute("value"), option.getText()));
        } else if (fieldId.equals(Constant.ADDRESS_COUNTRY_ID)) {
            List<WebElement> options = dropDownField.getOptions();
            options.forEach(option -> customerInformation.setListOfCountry(option.getAttribute("value"), option.getText()));
        }
        return this;
    }


    public AutomationPracticeIndexPage selectPersonalTitleAs(Boolean isMrTitle) {
        String titleId = (isMrTitle) ? Constant.TITLE_MR_FIELD_ID : Constant.TITLE_MRS_FIELD_ID;
        wait.until(ExpectedConditions.visibilityOf(findElement(String.format(titleCheckBoxLocatorById, titleId))));
        WebElement titleCheckBox = findElement(String.format(titleCheckBoxLocatorById, titleId));
        titleCheckBox.click();
        customerInformation.saveCustomerTitleByBoolean(isMrTitle);
        return this;
    }

    public AutomationPracticeIndexPage inputPersonalInformationFieldNameWithValue(String fieldName, String fieldValue) {
        findElement(String.format(personalInfoTextFieldLocatorByName, fieldName))
                .sendKeys(fieldValue);
        customerInformation.saveDataByFieldName(fieldName, fieldValue);
        return this;
    }

    public AutomationPracticeIndexPage checkOnPersonalInfoCheckBoxByText(String fieldText, Boolean isCheck) {
        if (isCheck) findElement(String.format(personalInfoCheckBoxLocatorByText, fieldText))
                .click();
        customerInformation.saveCheckBoxDataByFieldNameWithBooleanValue(fieldText, isCheck);
        return this;
    }

    public AutomationPracticeIndexPage inputYourAddressTextFieldNameWithValue(String fieldName, String fieldValue) {
        WebElement textField = findElement(String.format(yourAddressTextFieldLocatorByName, fieldName));
        textField.clear();
        textField.sendKeys(fieldValue);
        customerInformation.saveDataByFieldName(fieldName, fieldValue);
        return this;
    }

    public AutomationPracticeIndexPage inputAdditionalTextareaWithText(String fieldText) {
        findElement(additionalInformationTextareaXPathLocator)
                .sendKeys(fieldText);
        customerInformation.saveAdditionalInformationTextAreaByValue(fieldText);
        return this;
    }

    public AutomationPracticeIndexPage moveToMenuWithNameAndClickOnButtonByFieldName(String menuName, String subMenuFieldName) {
        wait.until(ExpectedConditions.visibilityOf(findElement(String.format(linkMenuListXPathLocatorByName, menuName))));
        WebElement mainMenuElement = findElement(String.format(linkMenuListXPathLocatorByName, menuName));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenuElement);
        WebElement subMenuElement = findElement(String.format(linkMenuListXPathLocatorByName, menuName) + String.format(linkSubMenuToMergeWithMenuXPathLocatorByName, subMenuFieldName));
        actions.moveToElement(subMenuElement);
        actions.click().build().perform();
        return this;
    }

    public AutomationPracticeIndexPage focusOnProductInListByIndex(String index) {
        testContext.setAttribute("focusedProductXPath", String.format(productXPathLocatorByIndex, index));
        return this;
    }

    public AutomationPracticeIndexPage getDetailDataOfFocusedProductByName(String className) {
        wait.until(ExpectedConditions.visibilityOf(findElement(testContext.getAttributeByName("focusedProductXPath") + String.format(productDetailXPathLocatorByClassName, className))));
        WebElement productDetailElement = findElement(testContext.getAttributeByName("focusedProductXPath") + String.format(productDetailXPathLocatorByClassName, className));
        String valueToBeExtracted = productDetailElement.getText();
        testContext.setAttribute(className, valueToBeExtracted);
        return this;
    }

    public AutomationPracticeIndexPage inputSearchTextIntoSearchField(String searchText) {
        findElement(searchTextField).sendKeys(searchText);
        return this;
    }

    public AutomationPracticeIndexPage verifyDetailDataOfSavedProductDisplayOnSearchByName(String className) {
        WebElement productDetailElement = driver.findElement(By.xpath(testContext.getAttributeByName("focusedProductXPath") + String.format(productDetailXPathLocatorByClassName, className)));
        String actualValue = productDetailElement.getText();
        String expectedValue = testContext.getAttributeByName(className);
        Assert.assertEquals(actualValue, expectedValue, "Search Product Field " + className + " does not match with the focused Product field");
        return this;
    }
}
