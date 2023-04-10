package pageObjects;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class WithdrawalPageObject extends SideBarMyAccountPageObject{
    WebDriver driver;
    public WithdrawalPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void inputToAccountNoTextbox(String accountNo) {
        waitForElementVisible(driver, BankGuruPageUI.WithdrawalPageUI.ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.WithdrawalPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
    }

    public void inputToAmountTextbox(String amountWithdrawal) {
        waitForElementVisible(driver, BankGuruPageUI.WithdrawalPageUI.AMOUNT_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.WithdrawalPageUI.AMOUNT_TEXTBOX, amountWithdrawal);
    }

    public void inputToDescriptionTextbox(String descWithdrawal) {
        waitForElementVisible(driver, BankGuruPageUI.WithdrawalPageUI.DESCRIPTION_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.WithdrawalPageUI.DESCRIPTION_TEXTBOX, descWithdrawal);
    }

    public HomePageObject clickToSubmitButton() {
        waitForElementVisible(driver, BankGuruPageUI.WithdrawalPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.WithdrawalPageUI.SUBMIT_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
}
