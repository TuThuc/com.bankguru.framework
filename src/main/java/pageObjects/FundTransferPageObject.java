package pageObjects;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class FundTransferPageObject extends SideBarMyAccountPageObject{
    WebDriver driver;
    public FundTransferPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void inputToAccountPayersAccountNoTextbox(String payerAccountNo) {
        waitForElementVisible(driver, BankGuruPageUI.FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.FundTransferPageUI.PAYER_ACCOUNT_NO_TEXTBOX, payerAccountNo);
    }
    public void inputToAccountPayeersAccountNoTextbox(String payeerAccountNo) {
        waitForElementVisible(driver, BankGuruPageUI.FundTransferPageUI.PAYEER_ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.FundTransferPageUI.PAYEER_ACCOUNT_NO_TEXTBOX, payeerAccountNo);
    }


    public void inputToAmountTextbox(String amountTransfer) {
        waitForElementVisible(driver, BankGuruPageUI.FundTransferPageUI.AMOUNT_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.FundTransferPageUI.AMOUNT_TEXTBOX, amountTransfer);
    }

    public void inputToDescriptionTextbox(String descTransfer) {
        waitForElementVisible(driver, BankGuruPageUI.FundTransferPageUI.DESCRIPTION_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.FundTransferPageUI.DESCRIPTION_TEXTBOX, descTransfer);
    }

    public HomePageObject clickToSubmitButton() {
        waitForElementVisible(driver, BankGuruPageUI.FundTransferPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.FundTransferPageUI.SUBMIT_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }
}
