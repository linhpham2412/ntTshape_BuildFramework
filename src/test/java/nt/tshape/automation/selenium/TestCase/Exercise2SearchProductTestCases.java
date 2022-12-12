package nt.tshape.automation.selenium.TestCase;

import nt.tshape.automation.selenium.Constant;
import nt.tshape.automation.selenium.Customer_Information;
import nt.tshape.automation.selenium.PageModal.AutomationPracticeIndexPage;
import nt.tshape.automation.selenium.TestContext;
import nt.tshape.automation.setup.WebDriverTestNGSetup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Exercise2SearchProductTestCases extends WebDriverTestNGSetup {
    WebDriverWait wait;
    TestContext testContext;
    Customer_Information customerInformation;

    public Exercise2SearchProductTestCases(WebDriverWait wait, TestContext testContext) {
        this.wait = wait;
        this.testContext = testContext;
        this.customerInformation = testContext.getCustomerInformation();
    }

    @Test
    public void CollectProductNameAndSearch() {
        AutomationPracticeIndexPage automationPracticeIndexPage = new AutomationPracticeIndexPage(getDriver(), wait, testContext);

        automationPracticeIndexPage
                .goToPageByURL("http://automationpractice.com/index.php")
                .moveToMenuWithNameAndClickOnButtonByFieldName("Women", "T-shirts")
                .focusOnProductInListByIndex("1")
                .getDetailDataOfFocusedProductByName(Constant.PRODUCT_NAME)
                .getDetailDataOfFocusedProductByName(Constant.PRODUCT_CURRENT_PRICE)
                .inputSearchTextIntoSearchField(testContext.getAttributeByName(Constant.PRODUCT_NAME))
                .clickButtonByName("Search")
                .verifyDetailDataOfSavedProductDisplayOnSearchByName(Constant.PRODUCT_NAME)
                .verifyDetailDataOfSavedProductDisplayOnSearchByName(Constant.PRODUCT_CURRENT_PRICE);
    }
}
