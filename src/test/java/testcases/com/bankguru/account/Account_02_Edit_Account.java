package testcases.com.bankguru.account;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.account.EditAccountPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import testcases.com.bankguru.common.Register;

public class Account_02_Edit_Account extends BaseTest {
    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String env = System.getProperty("env");

        ConfigFactory.setProperty("server", env);

        environment = ConfigFactory.create(Environment.class);
        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);

        loginPage = PageGeneratorManager.getLoginPage(driver);

        accountNumberHaveSpecialCharacter = "123!@#";

        accountNumberHaveCharacter = "123abc";

        accountNumberHaveBlank = "123 45";

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

//        verifyTrue(homePage.getLoginSuccessMessage());
    }

    @Description("Edit account with Account number is empty ")
    @Test(groups = "smoke")
    public void TC_01_Edit_Account_With_Account_Number_Is_Empty() {

        homePage.openSideBarPageByTitle(driver, "Edit Account");

        editAccount = PageGeneratorManager.getEditAccountPage(driver);

        editAccount.inputToTextboxByName(driver, "accountno", "");

        editAccount.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(editAccount.getFieldErrorMessageById(driver, "message2"), "Account Number must not be blank");
    }

    @Description("Edit account with Account number have character ")
    @Test
    public void TC_02_Edit_Account_With_Account_Number_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Edit Account");

        editAccount = PageGeneratorManager.getEditAccountPage(driver);

        editAccount.inputToTextboxByName(driver, "accountno", accountNumberHaveCharacter);

        editAccount.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(editAccount.getFieldErrorMessageById(driver, "message2"), "Characters are not allowed");
    }

    @Description("Edit account with Account number have special character ")
    @Test
    public void TC_03_Edit_Account_With_Account_Number_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Edit Account");

        editAccount = PageGeneratorManager.getEditAccountPage(driver);

        editAccount.inputToTextboxByName(driver, "accountno", accountNumberHaveSpecialCharacter);

        editAccount.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(editAccount.getFieldErrorMessageById(driver, "message2"), "Special characters are not allowed");
    }

    @Description("Edit account with Account number have blank space ")
    @Test
    public void TC_03_Edit_Account_With_Account_Number_Have_Special_Space() {

        homePage.openSideBarPageByTitle(driver, "Edit Account");

        editAccount = PageGeneratorManager.getEditAccountPage(driver);

        editAccount.inputToTextboxByName(driver, "accountno", accountNumberHaveBlank);

        editAccount.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(editAccount.getFieldErrorMessageById(driver, "message2"), "Characters are not allowed");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private EditAccountPageObject editAccount;
    private String accountNumberHaveCharacter, accountNumberHaveSpecialCharacter, accountNumberHaveBlank;

    private Environment environment;
}
