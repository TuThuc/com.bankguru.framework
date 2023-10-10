package testcases.com.bankguru.account;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import pageObjects.account.NewAccountPageObject;
import testcases.com.bankguru.common.Register;

public class Account_01_New_Account extends BaseTest {
    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass( alwaysRun=true )
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String env = System.getProperty("env");

        ConfigFactory.setProperty("server", env);

        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);

        loginPage = PageGeneratorManager.getLoginPage(driver);

        customerIdHaveSpecialCharacter = "123!@#";

        customerIdHaveCharacter = "123abc";

        initialDepositHaveCharacter = "5000abc";

        initialDepositHaveSpecialCharacter = "6000!@#";


        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());


    }

    @Description("New account with CustomerID is empty")
    @Test( groups = "regressionTest")
    public void TC_01_New_Account_With_CustomerId_Is_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccount = PageGeneratorManager.getNewAccountPage(driver);

        newAccount.pressTabAtTextboxByName(driver, "cusid");

        verifyEquals(newAccount.getFieldErrorMessageById(driver, "message14"), "Customer ID is required");
    }

    @Description("New account with CustomerID have special character ")
    @Test(groups = "smoke")
    public void TC_02_New_Account_With_CustomerId_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccount = PageGeneratorManager.getNewAccountPage(driver);

        newAccount.inputToTextboxByName(driver, "cusid", customerIdHaveSpecialCharacter);

        verifyEquals(newAccount.getFieldErrorMessageById(driver, "message14"), "Special characters are not allowed");
    }

    @Description("New account with CustomerID have character ")
    @Test(groups = "regressionTest")
    public void TC_03_New_Account_With_CustomerId_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccount = PageGeneratorManager.getNewAccountPage(driver);

        newAccount.inputToTextboxByName(driver, "cusid", customerIdHaveCharacter);

        verifyEquals(newAccount.getFieldErrorMessageById(driver, "message14"), "Characters are not allowed");
    }

    @Description("New account with Initial Deposit have character ")
    @Test(groups = "smoke")
    public void TC_04_New_Account_With_Initial_Deposit_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccount = PageGeneratorManager.getNewAccountPage(driver);

        newAccount.inputToTextboxByName(driver, "inideposit", initialDepositHaveCharacter);

        verifyEquals(newAccount.getFieldErrorMessageById(driver, "message19"), "Characters are not allowed");
    }

    @Description("New account with Initial Deposit have special character ")
    @Test
    public void TC_05_New_Account_With_Initial_Deposit_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "New Account");

        newAccount = PageGeneratorManager.getNewAccountPage(driver);

        newAccount.inputToTextboxByName(driver, "inideposit", initialDepositHaveSpecialCharacter);

        verifyEquals(newAccount.getFieldErrorMessageById(driver, "message19"), "Special characters are not allowed");
    }

   @AfterClass(alwaysRun = true)
   public void afterClass() {
  //     closeBrowserAndDriver("envName");
       closeBrowserAndDriver();
   }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private NewAccountPageObject newAccount;
    private String customerIdHaveSpecialCharacter, customerIdHaveCharacter, initialDepositHaveCharacter, initialDepositHaveSpecialCharacter;
    private Environment environment;
}
