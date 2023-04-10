package testcases.com.bankguru.customer;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.customer.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.customer.NewCustomerPageObject;
import testcases.com.bankguru.common.Register;
import utilities.DataHelper;

public class Customer_02_Edit_Customer extends BaseTest {
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

        phoneNumber= "09874032132";

        pinNo = "132452";

        cusIDHaveSpecialCharacter = "@!#123";

        cityHaveNumber = "123Hanoi";

        stateHaveSpecialCharacter = "hanoi!@#";

        pinHaveCharacter = "123Pin";

        pinMoreSixNumber = "2367";

        mobileHaveCharacter ="0786464tesst";

        emailInvalid = "test.mgial.mail";

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());

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

        verifyEquals(homePage.getTitlePageSuccessfully(), "Customer Registered Successfully!!!");


    }
@Description("Edit customer with CustomerID is Special Character")
   @Test
    public void TC_01_Edit_Customer_With_CustomerId_Is_Special_Character() {

        homePage.openSideBarPageByTitle(driver,"Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver,"cusid", cusIDHaveSpecialCharacter);

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message14"),"Special characters are not allowed");
    }
    @Description("Edit customer with CustomerID is Valid")
    @Test
    public void TC_02_Edit_Customer_With_CustomerId_Is_Valid() {

        homePage.openSideBarPageByTitle(driver,"Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver,"cusid", customerID );

        editCustomer.clickToButtonByName(driver,"AccSubmit");

        verifyEquals(editCustomer.getEditCustomerPageTitle(),"Edit Customer");
    }
    @Description("Edit customer with EmailAddress is blank")
    @Test
    public void TC_03_Edit_Customer_With_EmailAddress_Is_Blank() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid",customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToAddressTextbox("");

        editCustomer.pressTabAtAddressTextbox();

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message3"),"Address Field must not be blank");
    }
    @Description("Edit customer with City is blank")
  @Test
    public void TC_04_Edit_Customer_With_City_Is_Blank() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"city", "");

        editCustomer.pressTabAtTextboxByName(driver, "city");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message4"),"City Field must not be blank");
    }
    @Description("Edit customer with City have number")
   @Test
    public void TC_05_Edit_Customer_With_City_Have_Number() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"city", cityHaveNumber);

        editCustomer.pressTabAtTextboxByName(driver, "city");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message4"),"Numbers are not allowed");
    }
    @Description("Edit customer with State is blank")
   @Test
    public void TC_06_Edit_Customer_With_State_Is_Blank() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"state", "");

        editCustomer.pressTabAtTextboxByName(driver, "state");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message5"),"State must not be blank");
    }
    @Description("Edit customer with State have special character")
   @Test
    public void TC_07_Edit_Customer_With_State_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"state", stateHaveSpecialCharacter);

        editCustomer.pressTabAtTextboxByName(driver, "state");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message5"),"Special characters are not allowed");
    }
    @Description("Edit customer with PIN have character")
  @Test
    public void TC_08_Edit_Customer_With_PIN_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"pinno", pinHaveCharacter);

        editCustomer.pressTabAtTextboxByName(driver, "pinno");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message6"),"Characters are not allowed");
    }
    @Description("Edit customer with PIN more six number")
    @Test
    public void TC_09_Edit_Customer_With_PIN_More_Six_Number() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"pinno", pinMoreSixNumber);

        editCustomer.pressTabAtTextboxByName(driver, "pinno");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message6"),"PIN Code must have 6 Digits");
    }
    @Description("Edit customer with mobile number have character")
   @Test
    public void TC_10_Edit_Customer_With_Mobile_Number_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"telephoneno", mobileHaveCharacter);

        editCustomer.pressTabAtTextboxByName(driver, "telephoneno");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message7"),"Characters are not allowed");
    }
    @Description("Edit customer with email address is invalid")
    @Test
    public void TC_11_Edit_Customer_With_Email_Address_Is_Invalid() {

        homePage.openSideBarPageByTitle(driver, "Edit Customer");

        editCustomer = PageGeneratorManager.getEditCustomerPage(driver);

        editCustomer.inputToTextboxByName(driver, "cusid", customerID);

        editCustomer.clickToButtonByName(driver, "AccSubmit");

        editCustomer.inputToTextboxByName(driver,"emailid", emailInvalid);

        editCustomer.pressTabAtTextboxByName(driver, "emailid");

        verifyEquals(editCustomer.getFieldErrorMessageById(driver,"message9"),"Email-ID is not valid");
    }
    @Parameters("envName")
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver("envName");
    }
    private WebDriver driver;
    private DataHelper dataFaker;
    private LoginPageObject loginPage;
    private NewCustomerPageObject newCustomer;
    private HomePageObject homePage;
    private EditCustomerPageObject editCustomer;
    private String dateOfBirth, state, phoneNumber, pinNo,customerID , cusIDHaveSpecialCharacter, cityHaveNumber, stateHaveSpecialCharacter, pinHaveCharacter, pinMoreSixNumber, mobileHaveCharacter, emailInvalid;
Environment environment;

}
