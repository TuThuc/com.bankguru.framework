package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class LoginPageObject extends BasePage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Open Register Page with URL ")
    public RegisterPageObject clickToHereLink() {
        waitForElementVisible(driver, BankGuruPageUI.LoginPageUI.HERE_LINK);
        clickToElement(driver, BankGuruPageUI.LoginPageUI.HERE_LINK);
        return PageGeneratorManager.getRegisterPage(driver);
    }
    @Step("Input To UserID Textbox with value is {0} ")
    public void inputToUserIDTextbox(String userID) {
        waitForElementVisible(driver, BankGuruPageUI.LoginPageUI.USERID_TEXTBOX);
        sendkeyToElement(driver, BankGuruPageUI.LoginPageUI.USERID_TEXTBOX,userID );
    }
    @Step("Input To Password Textbox with value is {0} ")
    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, BankGuruPageUI.LoginPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, BankGuruPageUI.LoginPageUI.PASSWORD_TEXTBOX,password );
    }
    @Step("Click To Login Button ")
    public HomePageObject clickToLoginButton() {
        waitForElementClickable(driver,BankGuruPageUI.LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, BankGuruPageUI.LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
}
