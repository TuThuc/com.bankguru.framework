package testcases.com.bankguru.customer;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.customer.NewCustomerPageObject;
import testcases.com.bankguru.common.Register;
import utilities.DataHelper;

public class Customer_01_New_Customer extends BaseTest {
   // Environment environment;
    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
       // String env = System.getProperty("env");
       // ConfigFactory.setProperty("server", env);
        //environment = ConfigFactory.create(Environment.class);
        driver = getBrowserDriver(envName, browserName,serverName, ipAddress, portNumber, osName, osVersion);
        dataFaker = DataHelper.getDataHelper();

        nameHaveNumber = "123name";

        nameValid = dataFaker.getFirstName();

        nameHaveSpecialCharacter = "name!@#";

        nameHaveBlankSpace = " 123bc";

        dateOfBirth = "2021-04-14";

        city = dataFaker.getCity();

        loginPage = PageGeneratorManager.getLoginPage(driver);

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());
    }
    @Description("Creat New Customer with filed name is empty")
    @Test
    public void TC_01_New_Customer_With_Name_Empty() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", "");

        newCustomer.pressTabAtTextboxByName(driver, "name");

        verifyEquals(newCustomer.getFieldErrorMessageById(driver, "message"), "Customer name must not be blank");
    }

    @Description("Creat New Customer with filed name is number")
    @Test
    public void TC_02_New_Customer_With_Name_Number() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", nameHaveNumber);

        newCustomer.pressTabAtTextboxByName(driver, "name");

        verifyEquals(newCustomer.getFieldErrorMessageById(driver, "message"), "Numbers are not allowed");

    }

    @Description("Creat New Customer with filed name have special characters")
    @Test
    public void TC_03_New_Customer_With_Name_Have_Special_Character() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", nameHaveSpecialCharacter);

        newCustomer.pressTabAtTextboxByName(driver, "name");

        verifyEquals(newCustomer.getFieldErrorMessageById(driver, "message"), "Special characters are not allowed");

    }

    @Description("Creat New Customer with filed name have blank space")
    @Test
    public void TC_04_New_Customer_With_Name_Start_As_Blank_Character() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", nameHaveBlankSpace);

        newCustomer.pressTabAtTextboxByName(driver, "name");

        verifyEquals(newCustomer.getFieldErrorMessageById(driver, "message"), "First character can not have space");

    }

    @Description("Creat New Customer with filed Address is empty")
    @Test
    public void TC_05_New_Customer_With_Address_Empty() {
        homePage.openSideBarPageByTitle(driver, "New Customer");

        newCustomer = PageGeneratorManager.getNewCustomerPage(driver);

        newCustomer.inputToTextboxByName(driver, "name", nameValid);

        newCustomer.inputToDateOfBirthTextbox(driver, "type", "dob", dateOfBirth);

        newCustomer.inputToAddressTextbox(driver, "");

        newCustomer.inputToTextboxByName(driver, "city", city);

        verifyEquals(newCustomer.getFieldErrorMessageById(driver, "message3"), "Address Field must not be blank");
    }

    @Parameters("envName")
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver("envName");
    }

    private WebDriver driver;
    private DataHelper dataFaker;
    private String nameHaveNumber, nameHaveSpecialCharacter, nameHaveBlankSpace, nameValid, dateOfBirth, city;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private NewCustomerPageObject newCustomer;
}