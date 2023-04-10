package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class RegisterPageObject extends BasePage {
    WebDriver driver;
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Register - Input to Email Address with value is {0} ")
    public void inputToEmailAdressTextbox(String emailAdress) {
        waitForElementVisible(driver, BankGuruPageUI.RegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendkeyToElement(driver, BankGuruPageUI.RegisterPageUI.EMAIL_ADDRESS_TEXTBOX,emailAdress);
    }
    @Step("Register - Click To Submit Button ")
    public void clickToSubmitButton() {
        waitForElementClickable(driver,BankGuruPageUI.RegisterPageUI.SUBMIT_BUTTON);
        clickToElement(driver, BankGuruPageUI.RegisterPageUI.SUBMIT_BUTTON);
    }
    @Step(" Get userID info")
    public String getUserInfor() {
        waitForElementVisible(driver, BankGuruPageUI.RegisterPageUI.USERID_INFOR);
        return getElementText(driver,BankGuruPageUI.RegisterPageUI.USERID_INFOR);
    }
    @Step(" Get password info")
    public String getPasswordInfor() {
        waitForElementVisible(driver, BankGuruPageUI.RegisterPageUI.PASSWORD_INFOR);
        return getElementText(driver, BankGuruPageUI.RegisterPageUI.PASSWORD_INFOR);
    }
}
