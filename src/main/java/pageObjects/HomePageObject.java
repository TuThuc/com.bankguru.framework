package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUI.BankGuruPageUI;

public class HomePageObject extends SideBarMyAccountPageObject {
    WebDriver driver;
    public HomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Login Success Message ")

    public boolean getLoginSuccessMessage() {
        waitForElementVisible(driver, BankGuruPageUI.HomePageUI.LOGIN_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, BankGuruPageUI.HomePageUI.LOGIN_SUCCESS_MESSAGE);
    }

    public Object getTitlePageSuccessfully() {
        waitForElementVisible(driver, BankGuruPageUI.HomePageUI.TITLE_PAGE_SUCCESS_MESSAGE);
        return  getElementText(driver, BankGuruPageUI.HomePageUI.TITLE_PAGE_SUCCESS_MESSAGE);
    }

    public String getValueTextByTitle(String titleText) {
        waitForElementVisible(driver, BankGuruPageUI.HomePageUI.DYNAMIC_VALUE_TEXT_BY_TITLE, titleText);
        return  getElementText(driver, BankGuruPageUI.HomePageUI.DYNAMIC_VALUE_TEXT_BY_TITLE,titleText);
    }
}
