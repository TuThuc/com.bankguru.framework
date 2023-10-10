package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryEnviroment.BrowserstackFactory;
import factoryEnviroment.EnvironmentList;
import factoryEnviroment.GridFactory;
import factoryEnviroment.LambdaFactory;
import factoryEnviroment.LocalFactory;
import factoryEnviroment.SauceLabFactory;

public abstract class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    protected final Log log;

    @BeforeSuite
    public void initBeforeSuite() {
        deleteAllureReport();
    }

    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getBrowserDriver(String envName, String browserName, String serverName, String ipAddress, String portNumber, String osName, String osVersion) {
        switch (envName) {
            case "local":
                driver.set(new LocalFactory(browserName).creatDriver());
                break;
            case "grid":
                driver.set(new GridFactory(browserName, ipAddress, portNumber).creatDriver());
                break;
            case "browserStack":
                driver.set(new BrowserstackFactory(browserName, osName, osVersion).creatDriver());

                break;
            case "sauceLab":
                driver.set(new SauceLabFactory(browserName, osName).creatDriver());
                break;
            case "Lambda":
                driver.set(new LambdaFactory(browserName, osName).creatDriver());
                break;
            default:
                driver.set(new LocalFactory(browserName).creatDriver());

                break;
        }
        driver.get().manage().timeouts().implicitlyWait(GlobalConstants.getGlobalConstants().getLongTimeout(), TimeUnit.SECONDS);
        driver.get().manage().window().maximize();
        driver.get().get(serverName);
        return driver.get();
    }


    private String getAppUrlByServerName(String serverName) {
        System.out.println("Server name: " + serverName);
        EnvironmentList serverList = EnvironmentList.valueOf(serverName.toUpperCase());
        switch (serverList) {
            case DEV:
            return GlobalConstants.getGlobalConstants().getDevAppUrl();
            case TESTING:
            return GlobalConstants.getGlobalConstants().getTestAppUrl();
            case STAGING:
            return GlobalConstants.getGlobalConstants().getStagingAppUrl();
            case PRODUCTION:
            return GlobalConstants.getGlobalConstants().getProductAppUrl();
            default:
            throw new RuntimeException("Server name is invalid");
        }
    }

    public WebDriver getDriverInstance() {
        return this.driver.get();
    }

    protected int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;

            // Add lỗi vào ReportNG
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    private void deleteAllureReport() {
        try {

            String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + "/target/allure-results";
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println(listOfFiles[i].getName());
                    new File(listOfFiles[i].toString()).delete();
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    protected void closeBrowserAndDriver() {
        driver.get().quit();
    }
    protected void closeBrowserAndDriver(String envName) {
        if (envName.equals("local") || envName.equals("grid")) {
            String cmd = "";
            try {
                String osName = System.getProperty("os.name").toLowerCase();
                log.info("OS name = " + osName);

                String driverInstanceName = driver.get().toString().toLowerCase();
                log.info("Driver instance name = " + driverInstanceName);

                if (driverInstanceName.contains("chrome")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                    } else {
                        cmd = "pkill chromedriver";
                    }
                } else if (driverInstanceName.contains("internetexplorer")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                    }
                } else if (driverInstanceName.contains("firefox")) {
                    if (osName.contains("windows")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                    } else {
                        cmd = "pkill geckodriver";
                    }
                } else if (driverInstanceName.contains("edge")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                    } else {
                        cmd = "pkill msedgedriver";
                    }
                } else if (driverInstanceName.contains("opera")) {
                    if (osName.contains("window")) {
                        cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                    } else {
                        cmd = "pkill operadriver";
                    }
                } else if (driverInstanceName.contains("safari")) {
                    if (osName.contains("mac")) {
                        cmd = "pkill safaridriver";
                    }
                }

                if (driver != null) {
                    driver.get().manage().deleteAllCookies();
                    driver.get().quit();
                    driver.remove();
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            } finally {
                try {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected String getCurrentDate() {
        DateTime nowUTC = new DateTime();
        int day = nowUTC.getDayOfMonth();
        if (day < 10) {
            String dayValue = "0" + day;
            return dayValue;
        }
        return String.valueOf(day);
    }

    protected String getCurrentMonth() {
        DateTime now = new DateTime();
        int month = now.getMonthOfYear();
        if (month < 10) {
            String monthValue = "0" + month;
            return monthValue;
        }
        return String.valueOf(month);
    }

    protected String getCurrentYear() {
        DateTime now = new DateTime();
        return now.getYear() + "";
    }

    protected String getCurrentDay() {
        return getCurrentDate() + "/" + getCurrentMonth() + "/" + getCurrentYear();
    }
}
