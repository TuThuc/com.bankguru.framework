package pageObjects.customer;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;
import pageUI.BasePageUI;

public class NewCustomerPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public NewCustomerPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void inputToDateOfBirthTextbox(WebDriver driver,String type, String dob, String dateOfBirth) {
        waitForElementVisible(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, dob);
        removeAttributeInDOM(driver , BasePageUI.DYNAMIC_TEXTBOX_BY_NAME,type, dob);
        sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_NAME, dateOfBirth, dob );
    }

    public void inputToAddressTextbox(WebDriver driver, String valueInput) {
        waitForElementVisible(driver, BankGuruPageUI.NewCustomerPageUI.ADDRESS_TEXTAREA);
        sendkeyToElement(driver, BankGuruPageUI.NewCustomerPageUI.ADDRESS_TEXTAREA, valueInput);

    }
}
