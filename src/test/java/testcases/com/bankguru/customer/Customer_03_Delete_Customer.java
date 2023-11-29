package testcases.com.bankguru.customer;

import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import commons.BaseTest;
import pageObjects.customer.DeleteCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.customer.NewCustomerPageObject;
import retryConfig.RetryFailedManager;
import testcases.com.bankguru.common.Register;
import utilities.DataHelper;

public class Customer_03_Delete_Customer extends BaseTest {

    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String env = System.getProperty("env");

        ConfigFactory.setProperty("server", env);

        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);

        dataFaker = DataHelper.getDataHelper();

        loginPage = PageGeneratorManager.getLoginPage(driver);

        dateOfBirth = "10/04/2021";

        state = "active";

        phoneNumber = "09874032132";

        pinNo = "132452";

        customerIdHaveCharacter = "Tesst123";

        customerIdHaveSpecialCharacter = "123!@#";

        customerIdStartWithSpace = " 123456";

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        //verifyTrue(homePage.getLoginSuccessMessage());

        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", dataFaker.getFirstName());

        newCustomer.inputToDateOfBirthTextbox(driver, "type", "dob", dateOfBirth);

        newCustomer.inputToAddressTextbox(driver, dataFaker.getAddresss());

        newCustomer.inputToTextboxByName(driver, "city", dataFaker.getCity());

        newCustomer.inputToTextboxByName(driver, "state", state);

        newCustomer.inputToTextboxByName(driver, "pinno", pinNo);

        newCustomer.inputToTextboxByName(driver, "telephoneno", phoneNumber);

        newCustomer.inputToTextboxByName(driver, "emailid", dataFaker.getEmailAddress());

        newCustomer.inputToTextboxByName(driver, "password", Register.password);

        newCustomer.clickToButtonByName(driver, "sub");

        homePage = PageGeneratorManager.getHomePage(driver);

        customerID = homePage.getValueTextByTitle("Customer ID");

        //verifyEquals(homePage.getTitlePageSuccessfully(), "Customer Registered Successfully!!!");

    }

    @Description("Delete Customer with CustomerID is empty")
    @Test()
    public void TC_01_Delete_Customer_With_CustomerID_Is_Empty() {
        homePage.openSideBarPageByTitle(driver, "Delete Customer");

        deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);

        deleteCustomer.inputToTextboxByName(driver, "cusid", "");

        deleteCustomer.pressTabAtTextboxByName(driver, "cusid");

        verifyEquals(deleteCustomer.getFieldErrorMessageById(driver, "message14"), "Customer ID is required");
    }

    @Description("Delete Customer with CustomerID have character")
    @Test
    public void TC_02_Delete_Customer_With_CustomerID_Have_Character() {
        homePage.openSideBarPageByTitle(driver, "Delete Customer");

        deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);

        deleteCustomer.inputToTextboxByName(driver, "cusid", customerIdHaveCharacter);

        deleteCustomer.pressTabAtTextboxByName(driver, "cusid");

        verifyEquals(deleteCustomer.getFieldErrorMessageById(driver, "message14"), "Characters are not allowed");
    }

    @Description("Delete Customer with CustomerID have character")
    @Test
    public void TC_03_Delete_Customer_With_CustomerID_Have_Special_Character() {
        homePage.openSideBarPageByTitle(driver, "Delete Customer");

        deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);

        deleteCustomer.inputToTextboxByName(driver, "cusid", customerIdHaveSpecialCharacter);

        deleteCustomer.pressTabAtTextboxByName(driver, "cusid");

        verifyEquals(deleteCustomer.getFieldErrorMessageById(driver, "message14"), "Special characters are not allowed");
    }

    @Description("Delete Customer with CustomerID have first character space")
    @Test
    public void TC_04_Delete_Customer_With_CustomerID_Have_First_Character_Space() {
        homePage.openSideBarPageByTitle(driver, "Delete Customer");

        deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);

        deleteCustomer.inputToTextboxByName(driver, "cusid", customerIdStartWithSpace);

        deleteCustomer.pressTabAtTextboxByName(driver, "cusid");

        verifyEquals(deleteCustomer.getFieldErrorMessageById(driver, "message14"), "First character can not have space");
    }

//    @Description("Delete Customer successfuly")
//    @Test
//    public void TC_05_Delete_Customer_Successful() throws InterruptedException {
//        homePage.openSideBarPageByTitle(driver, "Delete Customer");
//
//        deleteCustomer = PageGeneratorManager.getDeleteCustomerPage(driver);
//System.out.println(customerID);
//        deleteCustomer.inputToTextboxByName(driver, "cusid", customerID);
//Thread.sleep(5000);
//        deleteCustomer.clickToButtonByName(driver, "AccSubmit");
//        Thread.sleep(5000);
//        deleteCustomer.acceptAlert(driver);
//        Thread.sleep(5000);
//        verifyEquals(deleteCustomer.getAlertText(driver), "Customer does not Exist!!!");
//        Thread.sleep(5000);
//    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }
    private NewCustomerPageObject newCustomer;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private DeleteCustomerPageObject deleteCustomer;
    private WebDriver driver;
    private DataHelper dataFaker;
    private String customerID, dateOfBirth, phoneNumber, pinNo, state, customerIdHaveCharacter, customerIdHaveSpecialCharacter, customerIdStartWithSpace;

    private Environment environment;


}
