package pageObjects.statement;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;

public class MiniStatementPageObject extends SideBarMyAccountPageObject {
    private WebDriver driver;

    public MiniStatementPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}


