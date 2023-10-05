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
import pageObjects.statement.CustomisedStatementPageObject;
import testcases.com.bankguru.common.Register;

public class Customised_Statement extends BaseTest {
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

        minimumTransactionValueHaveCharacter = "1500abc";

        minimumTransactionValueHaveSpecialCharacter = "1200!@#";

        minimumTransactionValueHaveBlank = "123 0000";

        numberOfTransactionHaveBlank = "123 45";

        numberOfTransactionHaveCharacter = "123acb";

        numberOfTransactionHaveSpecialCharacter = "123!!@#";

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());
    }

    @Description("Customised Statement with Account number is empty ")
    @Test
    public void TC_01_Customised_Statement_With_Account_Number_Is_Empty() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "accountno", "");

        customisedStatement.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message2"), "Account Number must not be blank");
    }

    @Description("Customised Statement with Account number have character ")
    @Test
    public void TC_02_Customised_Statement_With_Account_Number_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "accountno", accountNumberHaveCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message2"), "Characters are not allowed");
    }

    @Description("Customised Statement with Account number have special character ")
    @Test
    public void TC_03_Customised_Statement_With_Account_Number_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "accountno", accountNumberHaveSpecialCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message2"), "Special characters are not allowed");
    }

    @Description("Customised Statement with Account number have character space ")
    @Test
    public void TC_04_Customised_Statement_With_Account_Number_Have_Character_Space() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "accountno", accountNumberHaveBlank);

        customisedStatement.pressTabAtTextboxByName(driver, "accountno");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message2"), "Characters are not allowed");
    }

    @Description("Customised Statement with Minimum transaction value have character ")
    @Test
    public void TC_05_Customised_Statement_With_Minimum_Transaction_Value_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "amountlowerlimit", minimumTransactionValueHaveCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "amountlowerlimit");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message12"), "Characters are not allowed");
    }

    @Description("Customised Statement with Minimum transaction value have special character ")
    @Test
    public void TC_06_Customised_Statement_With_Minimum_Transaction_Value_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "amountlowerlimit", minimumTransactionValueHaveSpecialCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "amountlowerlimit");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message12"), "Special characters are not allowed");
    }

    @Description("Customised Statement with Minimum transaction value have character space ")
    @Test
    public void TC_07_Customised_Statement_With_Minimum_Transaction_Value_Have_Character_Space() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "amountlowerlimit", minimumTransactionValueHaveBlank);

        customisedStatement.pressTabAtTextboxByName(driver, "amountlowerlimit");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message12"), "Characters are not allowed");
    }

    @Description("Customised Statement with Number of transaction have character ")
    @Test
    public void TC_08_Customized_Statement_With_Number_Of_Tracsaction_Have_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "numtransaction", numberOfTransactionHaveCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "numtransaction");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message13"), "Characters are not allowed");
    }

    @Description("Customised Statement with Number of transaction have special character ")
    @Test
    public void TC_09_Customized_Statement_With_Number_Of_Tracsaction_Have_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "numtransaction", numberOfTransactionHaveSpecialCharacter);

        customisedStatement.pressTabAtTextboxByName(driver, "numtransaction");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message13"), "Special characters are not allowed");
    }

    @Description("Customised Statement with Number of transaction have character space ")
    @Test
    public void TC_10_Customized_Statement_With_Number_Of_Tracsaction_Have_Character_Space() {

        homePage.openSideBarPageByTitle(driver, "Customised Statement");

        customisedStatement = PageGeneratorManager.getCustomisedStatementPage(driver);

        customisedStatement.inputToTextboxByName(driver, "numtransaction", numberOfTransactionHaveBlank);

        customisedStatement.pressTabAtTextboxByName(driver, "numtransaction");

        verifyEquals(customisedStatement.getFieldErrorMessageById(driver, "message13"), "Characters are not allowed");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private CustomisedStatementPageObject customisedStatement;
    private String accountNumberHaveCharacter, accountNumberHaveSpecialCharacter, accountNumberHaveBlank, minimumTransactionValueHaveCharacter, minimumTransactionValueHaveSpecialCharacter, minimumTransactionValueHaveBlank, numberOfTransactionHaveCharacter, numberOfTransactionHaveSpecialCharacter, numberOfTransactionHaveBlank;
    private Environment environment;
}
