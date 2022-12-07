package nt.tshape.TestCase;

import nt.tshape.BaseClass;
import nt.tshape.Constant;
import nt.tshape.PageModal.AutomationPracticeAccountPage;
import nt.tshape.PageModal.AutomationPracticeIndexPage;
import org.testng.annotations.Test;

import static nt.tshape.Utils.*;

public class Exercise1RegisterNewAccountTestCases extends BaseClass {
    @Test
    public void RegisterNewAccount() {
        AutomationPracticeIndexPage automationPracticeIndexPage = new AutomationPracticeIndexPage(driver, wait, testContext);
        AutomationPracticeAccountPage automationPracticeAccountPage = new AutomationPracticeAccountPage(driver, wait, testContext);

        automationPracticeIndexPage
                .goToPageByURL("http://automationpractice.com/index.php")
                .clickLinkButtonByName("Sign in")
                .inputToEmailAddressWithEmail(generateTestEmail())
                .clickButtonByName("Create an account")
                .selectPersonalTitleAs(generateRandomTrueOrFalse())
                .inputPersonalInformationFieldNameWithValue("First name", generateRandomTestCharacters(5))
                .inputPersonalInformationFieldNameWithValue("Last name", generateRandomTestCharacters(7))
                .inputPersonalInformationFieldNameWithValue("Password", generateRandomTestCharacters(5))
                .selectDropDownFieldByIdWithValue(Constant.DOB_DAY_ID, generateRandomNumberInRange(1, 31))
                .selectDropDownFieldByIdWithValue(Constant.DOB_MONTH_ID, generateRandomNumberInRange(1, 12))
                .selectDropDownFieldByIdWithValue(Constant.DOB_YEAR_ID, generateRandomNumberInRange(1900, 2022))
                .checkOnPersonalInfoCheckBoxByText("Sign up for our newsletter!", generateRandomTrueOrFalse())
                .checkOnPersonalInfoCheckBoxByText("Receive special offers from our partners!", generateRandomTrueOrFalse())
                .inputYourAddressTextFieldNameWithValue("Company", generateRandomTestCharacters(10))
                .inputYourAddressTextFieldNameWithValue("Address", generateRandomTestCharacters(50))
                .inputYourAddressTextFieldNameWithValue("Address (Line 2)", generateRandomTestCharacters(50))
                .inputYourAddressTextFieldNameWithValue("City", generateRandomTestCharacters(20))
                .selectDropDownFieldByIdWithValue(Constant.ADDRESS_STATE_ID, generateRandomNumberInRange(1, 50))
                .inputYourAddressTextFieldNameWithValue("Zip/Postal Code", generateRandomNumberInRange(10000, 99999))
                .selectDropDownFieldByIdWithValue(Constant.ADDRESS_COUNTRY_ID, Constant.COUNTRY_UNITED_STATE)
                .inputAdditionalTextareaWithText(generateRandomTestCharacters(100))
                .inputYourAddressTextFieldNameWithValue("Home phone", generateRandomNumberInRange(1000000000, 1111111111))
                .inputYourAddressTextFieldNameWithValue("Mobile phone", generateRandomNumberInRange(1000000000, 1111111111))
                .inputYourAddressTextFieldNameWithValue("Assign an address alias for future reference.", generateRandomTestCharacters(10))
                .clickButtonByName("Register");

        automationPracticeAccountPage
                .clickLinkButtonByName("My personal information")
                .verifyPersonalInfoDataByFieldName("First name")
                .verifyPersonalInfoDataByFieldName("Last name")
                .verifyAccountLinkButtonWithAccountNameAvailable()
                .verifyPersonalInfoDataByFieldName("E-mail address")
                .verifyTitleCheckBoxDisplayCorrectlyById(Constant.TITLE_MR_FIELD_ID, customerInformation.getMrTitle())
                .verifyTitleCheckBoxDisplayCorrectlyById(Constant.TITLE_MRS_FIELD_ID, !customerInformation.getMrTitle())
                .verifyDropDownFieldByIdDisplayCorrectValue(Constant.DOB_DAY_ID)
                .verifyDropDownFieldByIdDisplayCorrectValue(Constant.DOB_MONTH_ID)
                .verifyDropDownFieldByIdDisplayCorrectValue(Constant.DOB_YEAR_ID)
                .clickLinkButtonByName("Back to your account")
                .clickLinkButtonByName("My addresses")
                .verifyAddressAliasHeaderDisplayCorrectValue(customerInformation.getDataByFieldName("Assign an address alias for future reference."))
                .verifyExistingOfAddressInfoByFieldName("First name")
                .verifyExistingOfAddressInfoByFieldName("Last name")
                .verifyExistingOfAddressInfoByFieldName("Company")
                .verifyExistingOfAddressInfoByFieldName("Address")
                .verifyExistingOfAddressInfoByFieldName("Address (Line 2)")
                .verifyExistingOfAddressInfoByFieldName("City")
                .verifyExistingOfAddressInfoByFieldName(Constant.ADDRESS_STATE_ID)
                .verifyExistingOfAddressInfoByFieldName("Zip/Postal Code")
                .verifyExistingOfAddressInfoByFieldName(Constant.ADDRESS_COUNTRY_ID)
                .verifyExistingOfAddressInfoByFieldName("Home phone")
                .verifyExistingOfAddressInfoByFieldName("Mobile phone");
    }
}
