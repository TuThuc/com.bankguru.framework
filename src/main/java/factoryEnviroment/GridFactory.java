        package factoryEnviroment;

        import java.io.File;
        import java.net.MalformedURLException;
        import java.net.URL;

        import commons.GlobalConstants;
        import org.openqa.selenium.Platform;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.chrome.ChromeOptions;
        import org.openqa.selenium.edge.EdgeDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.firefox.FirefoxOptions;
        import org.openqa.selenium.firefox.FirefoxProfile;
        import org.openqa.selenium.ie.InternetExplorerDriver;
       // import org.openqa.selenium.opera.OperaDriver;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.remote.RemoteWebDriver;

        import factoryBrower.BrowserList;
        import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
    private String browserName;
    private String ipAddress;
    private String portNumber;
    private WebDriver driver;

    public GridFactory(String browserName, String ipAddress, String portNumber) {
        this.browserName = browserName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public WebDriver creatDriver() {
        DesiredCapabilities capability = null;
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        if (browserList == BrowserList.FIREFOX) {

            WebDriverManager.firefoxdriver().setup();
           capability = DesiredCapabilities.firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.ANY);
            FirefoxOptions options = new FirefoxOptions();
            FirefoxProfile profile = new FirefoxProfile();
            profile.addExtension(new File(GlobalConstants.getGlobalConstants().getExtensionPath()+ "adblocker_ultimate-3.7.21.xpi"));
            options.setProfile(profile);
            options.merge(capability);
        } else if (browserList == BrowserList.H_FIREFOX) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            options.addArguments("window-size = 1920x1080");
            driver = new FirefoxDriver(options);
        } else if (browserList == BrowserList.CHROME) {
           WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addExtensions(new File(GlobalConstants.getGlobalConstants().getExtensionPath()+ "adblocker_ultimate.crx"));
            capability = DesiredCapabilities.chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.ANY);
            options.merge(capability);
        } else if (browserList == BrowserList.H_CHROME) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("window-size = 1920x1080");
            driver = new ChromeDriver(options);

        } else if (browserList == BrowserList.EDGE_CHROMIUM) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserList == BrowserList.EDGE_LEGACY) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        } else if (browserList == BrowserList.OPERA) {
            WebDriverManager.operadriver().setup();
           // driver = new OperaDriver();
        } else if (browserList == BrowserList.IE) {
            WebDriverManager.iedriver().arch32().setup();
            driver = new InternetExplorerDriver();

        } else if (browserList == BrowserList.COCCOC) {
            WebDriverManager.chromedriver().driverVersion("100.0.4896.60").setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Admin\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
            driver = new ChromeDriver(options);

        } else if (browserList == BrowserList.BRAVE) {
            WebDriverManager.chromedriver().driverVersion("101.0.4951.41").setup();
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
            driver = new ChromeDriver(options);
        } else {
            throw new RuntimeException("Brower name invalid");
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }
}

