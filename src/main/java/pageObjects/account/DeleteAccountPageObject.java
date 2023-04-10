package pageObjects.account;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;
import pageUI.BasePageUI;

public class DeleteAccountPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public DeleteAccountPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void inputToAccountNoTextbox(String accountNo) {
        waitForElementVisible(driver, BankGuruPageUI.DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.DeleteAccountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
    }

    public void clickToSubmitButton() {
        waitForElementVisible(driver, BankGuruPageUI.DeleteAccountPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.DeleteAccountPageUI.SUBMIT_BUTTON);

    }
}
