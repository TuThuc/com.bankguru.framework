package pageObjects.customer;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;

public class EditCustomerPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public EditCustomerPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void pressTabAtAddressTextbox() {
        waitForElementVisible(driver, BankGuruPageUI.EditCustomerPageUI.ADDRESS_TEXTAREA);
        pressKeyToElement(driver,BankGuruPageUI.EditCustomerPageUI.ADDRESS_TEXTAREA, Keys.TAB);
    }
    public void inputToAddressTextbox( String valueInput) {
        waitForElementVisible(driver, BankGuruPageUI.EditCustomerPageUI.ADDRESS_TEXTAREA);
        sendkeyToElement(driver, BankGuruPageUI.EditCustomerPageUI.ADDRESS_TEXTAREA, valueInput);

    }

    public Object getEditCustomerPageTitle() {
        waitForElementVisible(driver, BankGuruPageUI.EditCustomerPageUI.EDIT_CUSTOMER_PAGE_TITLE);
        return  getElementText(driver, BankGuruPageUI.EditCustomerPageUI.EDIT_CUSTOMER_PAGE_TITLE);
    }

}
