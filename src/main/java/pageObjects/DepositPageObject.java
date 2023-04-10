package pageObjects;

import commons.PageGeneratorManager;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class DepositPageObject extends SideBarMyAccountPageObject{
    WebDriver driver;
    public DepositPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void inputToAccountNoTextbox(String accountNo){
        waitForElementVisible(driver, BankGuruPageUI.AmountPageUI.ACCOUNT_NO_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.AmountPageUI.ACCOUNT_NO_TEXTBOX, accountNo);
    }
    public void inputToAmountTextbox(String amount){
        waitForElementVisible(driver, BankGuruPageUI.AmountPageUI.AMOUNT_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.AmountPageUI.AMOUNT_TEXTBOX, amount);
    }
    public void inputToDescriptionTextbox(String desc){
        waitForElementVisible(driver, BankGuruPageUI.AmountPageUI.DESCRIPTION_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.AmountPageUI.DESCRIPTION_TEXTBOX, desc);
    }

    public HomePageObject clickToSubmitButton(){
        waitForElementVisible(driver, BankGuruPageUI.AmountPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.AmountPageUI.SUBMIT_BUTTON);
        return PageGeneratorManager.getHomePage(driver);
    }

}
