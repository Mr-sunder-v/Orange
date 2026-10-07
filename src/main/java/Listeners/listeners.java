package Listeners;

import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import AI.AIResponse;
import AI.AIService;
import AI.failureDetails;
import Framework.PageDriver;
import Utilities.ScreenshotUtils;
import Utilities.extentManager;
import Utilities.extentTestManager;

public class listeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        extentTestManager.setTest(
            extentManager.getInstance()
                .createTest(result.getMethod().getMethodName())
        );

        extentTestManager.getTest().log(
            Status.INFO,
            "Test execution started"
        );
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTestManager.getTest().log(
            Status.PASS,
            "✅ Test Passed"
        );

        attachScreenshot(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        Throwable error = result.getThrowable();

        String testName = result.getMethod().getMethodName();

        String currentUrl = "URL not available";
        String pageName = "Unknown";
        String locator = "Unknown";

        if (PageDriver.getDriver() != null) {
            try {
                currentUrl = PageDriver.getDriver().getCurrentUrl();
            } catch (Exception e) {
                currentUrl = "Unable to fetch URL";
            }
        }

        String exceptionType = "Unknown";
        String exceptionMessage = "Unknown";
        String stackTrace = "Unknown";

        if (error != null) {

            exceptionType = error.getClass().getSimpleName();

            exceptionMessage = error.getMessage();

            stackTrace = Arrays.toString(
                error.getStackTrace()
            );
        }

        failureDetails details = new failureDetails(
            testName,
            pageName,
            currentUrl,
            locator,
            exceptionType,
            exceptionMessage,
            stackTrace
        );

        // Log failure
        extentTestManager.getTest().fail(
            "❌ Test Failed"
        );

        // Log exception
        if (error != null) {
            extentTestManager.getTest().fail(error);
        }

        // Capture failure screenshot
        attachScreenshot(result);

        // AI failure analysis
        try {

            AIService service = new AIService();

            AIResponse aiResponse =
                service.failureAnalyzer(details);

            extentTestManager.getTest().info(
                "🤖 AI Failure Analysis:<br>" +
                aiResponse.getResponse()
            );

        } catch (IOException | InterruptedException e) {

            extentTestManager.getTest().warning(
                "AI Failure Analysis unavailable: "
                + e.getMessage()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTestManager.getTest().log(
            Status.SKIP,
            "⚠️ Test Skipped"
        );

        attachScreenshot(result);
    }

    @Override
    public void onFinish(ITestContext context) {

        extentManager.getInstance().flush();

        extentTestManager.unload();
    }

    private void attachScreenshot(ITestResult result) {

        if (PageDriver.getDriver() == null) {

            extentTestManager.getTest().warning(
                "Screenshot skipped because WebDriver is null."
            );

            return;
        }

        try {

            String testName =
                result.getMethod().getMethodName()
                + "_"
                + System.currentTimeMillis();

            String path = ScreenshotUtils.captureScreenshot(
                PageDriver.getDriver(),
                testName
            );

            if (path != null) {

                extentTestManager.getTest()
                    .addScreenCaptureFromPath(
                        path,
                        "📸 Screenshot"
                    );
            }

        } catch (Exception e) {

            extentTestManager.getTest().warning(
                "Unable to capture screenshot: "
                + e.getMessage()
            );
        }
    }
}