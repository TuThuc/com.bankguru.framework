package factoryBrower;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
       System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstants().getBrowserLogs());
     //  options.addArguments("browser.download.dir", GlobalConstants.DOWNLOAD_PATH);
        options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");
        options.setAcceptInsecureCerts(true);
        return WebDriverManager.firefoxdriver().capabilities(options).create();

    }

}
