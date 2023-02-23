package commons;

import java.io.File;

public class GlobalConstants {
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;
    public static final long RETRY_TEST_FAIL = 3;
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String FILE_SEPARATOR = File.separator;
    public static final String PORTAL_DEV_URL = "https://demo.nopcommerce.com";
    public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com";
    public static final String PORTAL_TESTING_URL = "https://demo.nopcommerce.com";
    public static final String ADMIN_TESTING_URL = "https://admin-demo.nopcommerce.com";
    public static final String UPLOAD_PATH = getFolderSeparator("uploadFiles");
    public static final String DOWNLOAD_PATH = getFolderSeparator("downloadFiles");
    public static final String BROWSER_LOG = getFolderSeparator("browserLogs");
    public static final String BROWSER_USERNAME = "tuthuc1";
    public static final String BROWSER_AUTOMATE_KEY = "b6H118BpMUuq4mqJAGPo";
    public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static final String SAUCE_USERNAME = "oauth-tuthuc176216-91dbe";
    public static final String SAUCE_AUTOMATE_KEY = "bc18b83f-ee05-4afd-b38e-1ab69390a95c";
    public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + ":" + SAUCE_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

    public static final String BITBAR_AUTOMATE_KEY = "ZS3i8gl3teNETEe9IxPP1BYxx4maizOf";
    public static final String BITBAR_URL = "https://appium.bitbar.com/wd/hub";

    public static final String LAMBDA_USERNAME = "oauth-tuthuc176216-91dbe";
    public static final String LAMBDA_AUTOMATE_KEY = "bc18b83f-ee05-4afd-b38e-1ab69390a95c";
    public static final String LAMBDA_URL = "https://" + LAMBDA_USERNAME + ":" + LAMBDA_AUTOMATE_KEY + "@hub.lambdatest.com/wd/hub";

    private static String getFolderSeparator(String folderName) {
        return PROJECT_PATH + FILE_SEPARATOR + "folderName" + FILE_SEPARATOR;
    }
}