package pageObjects.account;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;
import pageUI.BankGuruPageUI;

public class EditAccountPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public EditAccountPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void selectAccountTypeDropdown( String textItem){
        waitForAllElementVisible(driver, BankGuruPageUI.EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN);
        selectItemInDefaultDropdown(driver, BankGuruPageUI.EditAccountPageUI.ACCOUNT_TYPE_DROPDOWN, textItem );
    }
}
