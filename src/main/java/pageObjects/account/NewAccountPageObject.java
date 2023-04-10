package pageObjects.account;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;

public class NewAccountPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public NewAccountPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void selectAccountTypeDropdown(String textItem) {
        waitForAllElementVisible(driver, BankGuruPageUI.NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
        selectItemInDefaultDropdown(driver, BankGuruPageUI.NewAccountPageUI.ACCOUNT_TYPE_DROPDOWN, textItem);
    }
}
