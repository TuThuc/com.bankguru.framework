package factoryBrower;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.chrome.ChromeOptions;
import commons.GlobalConstants;

public class ChromeDriverManager implements BrowserFactory {

    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File(GlobalConstants.getGlobalConstants().getExtensionPath()+ "adblocker_ultimate.crx"));

        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-geolocation");
        System.setProperty("webdriver.chrome.args", "--disable-logging");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Map<String, Object> chromaePrefs = new HashMap<String, Object>();
        chromaePrefs.put("profile.default_content_setting.popups", 0);
        chromaePrefs.put("dowload.default_directory", GlobalConstants.getGlobalConstants().getDownloadPath());
        options.setExperimentalOption("prefs", chromaePrefs);
        chromaePrefs.put("credentials_enable_service", false);
        chromaePrefs.put("profile.password_manager_enabled", false);
        options.setAcceptInsecureCerts(true);
        options.addArguments("--lang=vi");
        return WebDriverManager.chromedriver().capabilities(options).create();
    }

}