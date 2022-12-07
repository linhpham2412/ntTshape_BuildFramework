package nt.tshape.TestCase;

import nt.tshape.BaseClass;
import nt.tshape.Constant;
import nt.tshape.PageModal.AutomationPracticeIndexPage;
import org.testng.annotations.Test;

public class Exercise2SearchProductTestCases extends BaseClass {
    @Test
    public void CollectProductNameAndSearch() {
        AutomationPracticeIndexPage automationPracticeIndexPage = new AutomationPracticeIndexPage(driver, wait, testContext);

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
