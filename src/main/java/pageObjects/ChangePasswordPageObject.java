package pageObjects;

import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObject extends SideBarMyAccountPageObject{
    WebDriver driver;
    public ChangePasswordPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
