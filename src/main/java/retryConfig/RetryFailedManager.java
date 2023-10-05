package retryConfig;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedManager implements IRetryAnalyzer {
    //private  int retryCount = 0;
//private static final int maxRetryCount = 2;
//    @Override
//    public boolean retry(ITestResult result) {
//        if(retryCount<maxRetryCount){
//            retryCount++;
//            return true;
//        }
//        return false;
//    }
//}
    private static final Logger LOG = LogManager.getLogger("Retry.class");
    private static final int maxTry = 2;
    private int count = 0;

    @Override
    public boolean retry(final ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (this.count < maxTry) {
                LOG.info("Retrying test " + iTestResult.getName() + " with status " + getResultStatusName(
                        iTestResult.getStatus()) + " for the " + (this.count + 1) + " time(s).");
                this.count++;
                return true;
            }
        }
        return false;
    }

    public String getResultStatusName(final int status) {
        String resultName = null;
        if (status == 1) {
            resultName = "PASSED";
        }
        if (status == 2) {
            resultName = "FAILED";
        }
        if (status == 3) {
            resultName = "SKIP";
        }
        return resultName;
    }
}

