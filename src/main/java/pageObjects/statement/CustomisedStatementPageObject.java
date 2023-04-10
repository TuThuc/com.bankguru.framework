package pageObjects.statement;

import org.openqa.selenium.WebDriver;
import pageObjects.SideBarMyAccountPageObject;

public class CustomisedStatementPageObject extends SideBarMyAccountPageObject {
    private WebDriver driver;

    public CustomisedStatementPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}


