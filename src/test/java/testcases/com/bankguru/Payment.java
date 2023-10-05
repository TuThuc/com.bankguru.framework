package testcases.com.bankguru;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import pageObjects.account.DeleteAccountPageObject;
import pageObjects.account.EditAccountPageObject;
import pageObjects.account.NewAccountPageObject;
import pageObjects.customer.DeleteCustomerPageObject;
import pageObjects.customer.EditCustomerPageObject;
import pageObjects.customer.NewCustomerPageObject;
import testcases.com.bankguru.common.Register;
import utilities.DataHelper;
public class Payment extends BaseTest {
    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {

        String env = System.getProperty("env");

        ConfigFactory.setProperty("server", env);

        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);

        dataFaker = DataHelper.getDataHelper();

        customerName = dataFaker.getFirstName();

        dateOfBirth = "2022-02-23";

        address = dataFaker.getAddresss();

        city = dataFaker.getCity();

        state = "active";

        pin = "235522";

        phoneNumber = "0984613532";

        emailAddress = dataFaker.getEmailAddress();

        addressEdit= dataFaker.getAddresss();

        cityEdit = dataFaker.getCity();

        stateEdit = "Texas";

        pinEdit = "152451";

        phoneNumberEdit = "0123456789";

        initialDeposit = "50000";

        amountDeposit = "5000";

        amountWithdrawal = "15000";

        amountTransfer = "1000";

        descDeposit = "deposit with value is 5000";

        descWithdrawal = "Withdrawal with value is 15000";

        descTransfer = "Transfer another account with 1000";

        loginPage = PageGeneratorManager.getLoginPage(driver);


        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());
    }
    @Description("Creat New Customer account")
    @Test
    public void TC_01_Creat_New_Customer_Successfully() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomerPage.inputToTextboxByName(driver, "name", customerName );

        newCustomerPage.inputToDateOfBirthTextbox(driver, "type", "dob", dateOfBirth );

        newCustomerPage.inputToAddressTextbox(driver, address);

        newCustomerPage.inputToTextboxByName(driver,"city", city);

        newCustomerPage.inputToTextboxByName(driver,"state", state);

        newCustomerPage.inputToTextboxByName(driver,"pinno", pin);

        newCustomerPage.inputToTextboxByName(driver,"telephoneno", phoneNumber);

        newCustomerPage.inputToTextboxByName(driver,"emailid", emailAddress);

        newCustomerPage.inputToTextboxByName(driver,"password", Register.password);

        newCustomerPage.clickToButtonByName(driver,"sub");

        homePage = PageGeneratorManager.getHomePage(driver);

        customerID = homePage.getValueTextByTitle("Customer ID");

        verifyEquals(homePage.getTitlePageSuccessfully(),"Customer Registered Successfully!!!");

        verifyEquals(homePage.getValueTextByTitle("Customer Name"),customerName);

        verifyEquals(homePage.getValueTextByTitle("Birthdate"),dateOfBirth);

        verifyEquals(homePage.getValueTextByTitle("Address"),address);

        verifyEquals(homePage.getValueTextByTitle("City"),city);

        verifyEquals(homePage.getValueTextByTitle("State"),state);

        verifyEquals(homePage.getValueTextByTitle("Pin"),pin);

        verifyEquals(homePage.getValueTextByTitle("Mobile No."),phoneNumber);

        verifyEquals(homePage.getValueTextByTitle("Email"),emailAddress);
    }
    @Description("Edit Customer account successfully")
    @Test
    public void TC_02_Edit_Customer_Successfully() {
        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomerPage.inputToTextboxByName(driver, "cusid", customerID);

        editCustomerPage.clickToButtonByName(driver,"AccSubmit");

        editCustomerPage.inputToAddressTextbox(addressEdit);

        editCustomerPage.inputToTextboxByName(driver, "city", cityEdit);

        editCustomerPage.inputToTextboxByName(driver, "state", stateEdit);

        editCustomerPage.inputToTextboxByName(driver,"pinno", pinEdit);

        editCustomerPage.inputToTextboxByName(driver, "telephoneno", phoneNumberEdit);

        editCustomerPage.clickToButtonByName(driver,"sub");

        verifyEquals(editCustomerPage.getAlertText(driver),"No Changes made to Customer records");

        editCustomerPage.acceptAlert(driver);
    }
    @Description("Creat New Account successfully")
    @Test
    public void TC_03_Creat_New_Account_Successfully() {
        editCustomerPage.openSideBarPageByTitle(driver, "New Account");

        newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

        newAccountPage.inputToTextboxByName(driver, "cusid", customerID);

        newAccountPage.selectAccountTypeDropdown("Current");

        newAccountPage.inputToTextboxByName(driver,"inideposit", initialDeposit);

        newAccountPage.clickToButtonByName(driver,"button2");

        homePage = PageGeneratorManager.getHomePage(driver);

        accountID = homePage.getValueTextByTitle("Account ID");

        verifyEquals(homePage.getTitlePageSuccessfully(),"Account Generated Successfully!!!");

        verifyEquals(homePage.getValueTextByTitle("Current Amount"), initialDeposit);
    }
    @Description("Edit Account successfully")
    //@Test
    public void TC_04_Edit_Account_Successfully() {
        homePage.openSideBarPageByTitle(driver, "Edit Account");

        editAccountPage = PageGeneratorManager.getEditAccountPage(driver);

        editAccountPage.inputToTextboxByName(driver, "accountno", accountID);

        editAccountPage.clickToButtonByName(driver,"AccSubmit");

        editAccountPage.selectAccountTypeDropdown("Savings");

        editAccountPage.clickToButtonByName(driver,"button2");

        homePage = PageGeneratorManager.getHomePage(driver);

        verifyEquals(homePage.getTitlePageSuccessfully(),"Account details updated Successfully!!!");

        verifyEquals(homePage.getValueTextByTitle("Account Type"), "Savings");
    }
    @Description("Creat Amount Deposit successfully")
   // @Test
    public void TC_05_Creat_Amount_Deposit_Successfully() {
        homePage.openSideBarPageByTitle(driver, "Deposit");

        depositPage = PageGeneratorManager.getDepositPage(driver);

        depositPage.inputToAccountNoTextbox(accountID);

        depositPage.inputToAmountTextbox(amountDeposit);

        depositPage.inputToDescriptionTextbox(descDeposit);

        depositPage.clickToSubmitButton();

        homePage = PageGeneratorManager.getHomePage(driver);

        verifyEquals(homePage.getTitlePageSuccessfully(),"Transaction details of Deposit for Account Number " + accountID);

        verifyEquals(homePage.getValueTextByTitle("Current Amount"), "55000");
    }
    @Description("Creat new Amount Deposit successfully")
    @Test
    public void TC_06_Withdraw_Money_From_Account_Successfully() {
        homePage.openSideBarPageByTitle(driver, "Withdrawal");

        withdrawalPage = PageGeneratorManager.getWithdrawalPage(driver);

        withdrawalPage.inputToAccountNoTextbox(accountID);

        withdrawalPage.inputToAmountTextbox(amountWithdrawal);

        withdrawalPage.inputToDescriptionTextbox(descWithdrawal);

        withdrawalPage.clickToSubmitButton();

        homePage = PageGeneratorManager.getHomePage(driver);

        verifyEquals(homePage.getTitlePageSuccessfully(),"Transaction details of Withdrawal for Account " + accountID);

        //verifyEquals(homePage.getValueTextByTitle("Current Amount"), "40000");
    }
    @Description("Transfer money into another account")
    @Test
    public void TC_07_Transfer_Money_Into_Another_Account_Successfully() {
        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccountPage = PageGeneratorManager.getNewAccountPage(driver);

        newAccountPage.inputToTextboxByName(driver, "cusid", customerID);

        newAccountPage.selectAccountTypeDropdown("Current");

        newAccountPage.inputToTextboxByName(driver,"inideposit", initialDeposit);

        newAccountPage.clickToButtonByName(driver,"button2");

        homePage = PageGeneratorManager.getHomePage(driver);

        accountIDTransfer = homePage.getValueTextByTitle("Account ID");

        verifyEquals(homePage.getTitlePageSuccessfully(),"Account Generated Successfully!!!");

        homePage.openSideBarPageByTitle(driver,"Fund Transfer");

        fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);

        fundTransferPage.inputToAccountPayersAccountNoTextbox(accountID);

        fundTransferPage.inputToAccountPayeersAccountNoTextbox(accountIDTransfer);

        fundTransferPage.inputToAmountTextbox(amountTransfer);

        fundTransferPage.inputToDescriptionTextbox(descTransfer);

        homePage = fundTransferPage.clickToSubmitButton();

        verifyEquals(homePage.getTitlePageSuccessfully(),"Fund Transfer Details");

        verifyEquals(homePage.getValueTextByTitle("Amount"), amountTransfer);
    }
    @Description("Check current account")
    //@Test
    public void TC_08_Check_Current_Balance_Enquiry() {
        homePage.openSideBarPageByTitle(driver, "Balance Enquiry");

        balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);

        balanceEnquiryPage.inputToAccountNoTextbox(accountID);

        balanceEnquiryPage.clickToSubmitButton();
    }
    @Description("Delete all account of this customer")
    @Test
    public void TC_08_Delete_Account_Successfully() {
        homePage.openSideBarPageByTitle(driver,"Delete Account");

        deleteAccountPage = PageGeneratorManager.getDeleteAccountPage(driver);

        deleteAccountPage.inputToAccountNoTextbox(accountID);

        deleteAccountPage.clickToSubmitButton();

        deleteAccountPage.acceptAlert(driver);

        verifyEquals(deleteAccountPage.getAlertText(driver),"Account does not exist");

        deleteAccountPage.acceptAlert(driver);
    }
    @Description("Delete exist customer")
    @Test
    public void TC_09_Delete_Customer_Successfully() {
        deleteAccountPage.openSideBarPageByTitle(driver,"Delete Customer");

        deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);

        deleteCustomerPage.inputToCustomerIDTextbox(customerID);

        deleteCustomerPage.clickToSubmitButton();

        deleteCustomerPage.acceptAlert(driver);

        verifyEquals(deleteCustomerPage.getAlertText(driver),"Customer deleted successfully");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private NewCustomerPageObject newCustomerPage;
    private EditCustomerPageObject editCustomerPage;
    private NewAccountPageObject newAccountPage;

    private EditAccountPageObject editAccountPage;

    private DepositPageObject depositPage;

    private WithdrawalPageObject withdrawalPage;
    private FundTransferPageObject fundTransferPage;
    private BalanceEnquiryPageObject balanceEnquiryPage;
    private DeleteAccountPageObject deleteAccountPage;

    private DeleteCustomerPageObject deleteCustomerPage;
    private DataHelper dataFaker;
    private  String customerName, customerID,accountIDTransfer, address, dateOfBirth, city, state, pin, phoneNumber, emailAddress, addressEdit, cityEdit, stateEdit, pinEdit, phoneNumberEdit, initialDeposit, accountID, amountDeposit,amountTransfer, descDeposit,descTransfer, amountWithdrawal, descWithdrawal;

    private Environment environment;
}

