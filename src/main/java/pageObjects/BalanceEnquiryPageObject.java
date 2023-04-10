package pageObjects;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class BalanceEnquiryPageObject extends SideBarMyAccountPageObject{
    WebDriver driver;
    public BalanceEnquiryPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void inputToAccountNoTextbox(String accountNo) {
        waitForElementVisible(driver, BankGuruPageUI.BalanceEnquiryPageUI.ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.BalanceEnquiryPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
    }

    public void clickToSubmitButton() {
        waitForElementVisible(driver, BankGuruPageUI.BalanceEnquiryPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.BalanceEnquiryPageUI.SUBMIT_BUTTON);

    }
}
