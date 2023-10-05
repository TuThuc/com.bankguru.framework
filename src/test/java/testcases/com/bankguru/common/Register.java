package testcases.com.bankguru.common;

import commons.BaseTest;
import commons.Environment;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import utilities.DataHelper;

public class Register extends BaseTest {

    @Parameters({"envName", "serverName", "browserName", "ipAddress", "port", "osName", "osVersion"})
    @BeforeTest( alwaysRun = true)
    public void beforeTest(@Optional("local") String envName, @Optional("dev") String serverName, @Optional("chrome") String browserName, @Optional("Windows") String osName, @Optional("10") String osVersion,
                            @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String env = System.getProperty("env");
        ConfigFactory.setProperty("server", env);
        environment = ConfigFactory.create(Environment.class);
        driver = getBrowserDriver(envName, browserName, environment.getUserUrl(), ipAddress, portNumber, osName, osVersion);
        dataFaker = DataHelper.getDataHelper();
        loginPage = PageGeneratorManager.getLoginPage(driver);

        registerPage = loginPage.clickToHereLink();

        registerPage.inputToEmailAdressTextbox(dataFaker.getEmailAddress());

        registerPage.clickToSubmitButton();

        userID = registerPage.getUserInfor();

        password = registerPage.getPasswordInfor();
closeBrowserAndDriver();
      //  closeBrowserAndDriver("envName");
    }
    private WebDriver driver;
    private DataHelper dataFaker;
    public static String userID, password;
    private LoginPageObject loginPage;
    private RegisterPageObject registerPage;
    private Environment environment;

}
