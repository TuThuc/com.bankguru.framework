package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.SideBarMyAccountPageUI;

public class SideBarMyAccountPageObject extends BasePage {
    WebDriver driver;

    public SideBarMyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void openSideBarPageByTitle(WebDriver driver, String sideBarTitle) {
        waitForElementVisible(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, sideBarTitle);
        clickToElement(driver, SideBarMyAccountPageUI.DYNAMIC_SIDE_BAR_LINK, sideBarTitle);
    }
}
