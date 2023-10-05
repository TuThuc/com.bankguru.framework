package testcases.com.bankguru.statement;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.statement.MiniStatementPageObject;
import testcases.com.bankguru.common.Register;

public class Mini_Statement extends BaseTest {
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

        verifyTrue(homePage.getLoginSuccessMessage());
    }
    @Description("Mini statement with Account number is empty ")
    @Test
    public void TC_01_Mini_Statement_With_Account_Number_Is_Empty() {

        homePage.openSideBarPageByTitle(driver, "Mini Statement");

        miniStatement = PageGeneratorManager.getMiniStatementPage(driver);

        miniStatement.inputToTextboxByName(driver, "accountno","");

        miniStatement.pressTabAtTextboxByName(driver,"accountno");

        verifyEquals(miniStatement.getFieldErrorMessageById(driver,"message2"),"Account Number must not be blank");
    }
    @Description("Mini Statement with Account number have character ")
    @Test
    public void TC_02_Mini_Statement_With_Account_Number_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Mini Statement");

        miniStatement = PageGeneratorManager.getMiniStatementPage(driver);

        miniStatement.inputToTextboxByName(driver, "accountno",accountNumberHaveCharacter);

        miniStatement.pressTabAtTextboxByName(driver,"accountno");

        verifyEquals(miniStatement.getFieldErrorMessageById(driver,"message2"),"Characters are not allowed");
    }
    @Description("Mini Statement with Account number have special character ")
    @Test
    public void TC_03_Mini_Statement_With_Account_Number_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Mini Statement");

        miniStatement = PageGeneratorManager.getMiniStatementPage(driver);

        miniStatement.inputToTextboxByName(driver, "accountno",accountNumberHaveSpecialCharacter);

        miniStatement.pressTabAtTextboxByName(driver,"accountno");

        verifyEquals(miniStatement.getFieldErrorMessageById(driver,"message2"),"Special characters are not allowed");
    }
    @Description("Mini Statement with Account number have blank space ")
    @Test
    public void TC_04_Mini_Statement_With_Account_Number_Have_Special_Space() {

        homePage.openSideBarPageByTitle(driver, "Mini Statement");

        miniStatement = PageGeneratorManager.getMiniStatementPage(driver);

        miniStatement.inputToTextboxByName(driver, "accountno",accountNumberHaveBlank);

        miniStatement.pressTabAtTextboxByName(driver,"accountno");

        verifyEquals(miniStatement.getFieldErrorMessageById(driver,"message2"),"Characters are not allowed");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private MiniStatementPageObject miniStatement;
    private  String accountNumberHaveCharacter, accountNumberHaveSpecialCharacter, accountNumberHaveBlank;
    private Environment environment;

}

