package factoryBrower;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class FirefoxDriverManager implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(new File(GlobalConstants.getGlobalConstants().getExtensionPath()+ "adblocker_ultimate-3.7.21.xpi"));
        options.setProfile(profile);
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
       System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstants().getBrowserLogs());
        options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");
        options.setAcceptInsecureCerts(true);
        return WebDriverManager.firefoxdriver().capabilities(options).create();

    }

}
