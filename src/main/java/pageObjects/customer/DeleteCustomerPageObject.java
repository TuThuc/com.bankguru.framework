package pageObjects.customer;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;

public class DeleteCustomerPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public DeleteCustomerPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void inputToCustomerIDTextbox(String customerID) {
        waitForElementVisible(driver, BankGuruPageUI.DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX);
        sendkeyToElement(driver,BankGuruPageUI.DeleteCustomerPageUI.CUSTOMER_ID_TEXTBOX, customerID);
    }
    public void clickToSubmitButton() {
        waitForElementVisible(driver, BankGuruPageUI.DeleteCustomerPageUI.SUBMIT_BUTTON);
        clickToElement(driver,BankGuruPageUI.DeleteCustomerPageUI.SUBMIT_BUTTON);

    }


}
