package Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import Framework.PageDriver;
import Utilities.*;

public class listeners implements ITestListener{
	
	public void onStart(ITestContext context)
	{
		
	}
	public void onTestStart(ITestResult result)
	{
		extentTestManager.setTest(extentManager.getInstance().createTest(result.getMethod().getMethodName()));
	}
	public void onTestSuccess(ITestResult result)
	{
		extentTestManager.getTest().log(Status.PASS, "Test Pass");
		attachScreenshot(result);
	}
	public void onTestFailure(ITestResult result)
	{
		extentTestManager.getTest().fail(result.getThrowable());
		attachScreenshot(result);
	}
	public void onTestSkipped(ITestResult result)
	{
		extentTestManager.getTest().skip("Test Skipped");
		attachScreenshot(result);
	}
	public void onFinish(ITestContext context)
	{
		extentManager.getInstance().flush();
	}
	
	private void attachScreenshot(ITestResult result) {

	    String path = ScreenshotUtils.captureScreenshot(
	            PageDriver.getDriver(),
	            result.getMethod().getMethodName());

	    try {
	        extentTestManager.getTest().addScreenCaptureFromPath(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}


