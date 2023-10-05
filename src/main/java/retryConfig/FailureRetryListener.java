package retryConfig;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FailureRetryListener implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = testAnnotation.getRetryAnalyzer();
        if(retry==null)
            testAnnotation.setRetryAnalyzer(RetryFailedManager.class);
    }
}
