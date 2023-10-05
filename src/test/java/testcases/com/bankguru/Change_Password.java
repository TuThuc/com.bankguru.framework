package testcases.com.bankguru;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.ChangePasswordPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import testcases.com.bankguru.common.Register;

public class Change_Password extends BaseTest {
    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {

        String env= System.getProperty("env");

        ConfigFactory.setProperty("server", env);

        environment = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);

        loginPage = PageGeneratorManager.getLoginPage(driver);

        newPasswordHaveNotNumber = "tester";

        newPasswordHaveNotSpecialCharater = "123abc";

        newPasswordHavePasswordTerm = "password123!@#";

        newPasswordValid = "test01!@";

        confirmPasswpordInvalid = "12345";

        loginPage.inputToUserIDTextbox(Register.userID);

        loginPage.inputToPasswordTextbox(Register.password);

        homePage = loginPage.clickToLoginButton();

        verifyTrue(homePage.getLoginSuccessMessage());
    }
    @Description("Change Password with old password is empty ")
    @Test
    public void TC_01_Change_Password_With_Old_Password_Is_Empty() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "oldpassword","");

        changePassword.pressTabAtTextboxByName(driver,"oldpassword");

        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message20"),"Old Password must not be blank");
    }
    @Description("Change Password with new password is empty ")
    @Test
    public void TC_02_Change_Password_With_New_Password_Is_Empty() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "newpassword","");

        changePassword.pressTabAtTextboxByName(driver,"newpassword");

        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message21"),"New Password must not be blank");
    }
    @Description("Change Password with new password have not number ")
    @Test
    public void TC_03_Change_Password_With_New_Password_Have_Not_Number() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "newpassword",newPasswordHaveNotNumber);

        changePassword.pressTabAtTextboxByName(driver,"newpassword");

        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message21"),"Enter at-least one numeric value");
    }
    @Description("Change Password with new password have not special character ")
    @Test
    public void TC_04_Change_Password_With_New_Password_Have_Not_Special_Character() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "newpassword",newPasswordHaveNotSpecialCharater);

        changePassword.pressTabAtTextboxByName(driver,"newpassword");

        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message21"),"Enter at-least one special character");
    }
    @Description("Change Password with new password have password terms ")
    @Test
    public void TC_05_Change_Password_With_New_Password_Have_Password_Terms() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "newpassword",newPasswordHavePasswordTerm);

        changePassword.pressTabAtTextboxByName(driver,"newpassword");

        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message21"),"Choose a difficult Password");
    }
    @Description("Change Password with Confirm password not match password ")
    @Test
    public void TC_06_Change_Password_With_Confirm_Password_Not_Match_Password() {

        homePage.openSideBarPageByTitle(driver, "Change Password");

        changePassword = PageGeneratorManager.getChangePasswordPage(driver);

        changePassword.inputToTextboxByName(driver, "newpassword",newPasswordValid);

        changePassword.inputToTextboxByName(driver, "confirmpassword",confirmPasswpordInvalid);


        verifyEquals(changePassword.getFieldErrorMessageById(driver,"message22"),"Passwords do not Match");
    }
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //     closeBrowserAndDriver("envName");
        closeBrowserAndDriver();
    }
    private WebDriver driver;
    private LoginPageObject loginPage;
    private HomePageObject homePage;
    private ChangePasswordPageObject changePassword;
    private  String newPasswordHaveNotNumber, newPasswordHaveNotSpecialCharater, newPasswordHavePasswordTerm, confirmPasswpordInvalid, newPasswordValid;

    private Environment environment;
}