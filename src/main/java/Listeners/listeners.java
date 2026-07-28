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
		//fetch the failure information
		
		String testName;
				String currentUrl;
				String exceptionType;
				String exceptionMessage;
				String stackTrace;
				String PageName = "unknown";
				String Locator = "unknown";
	
				
				testName = result.getMethod().getMethodName();
				currentUrl = PageDriver.getDriver().getCurrentUrl();
				Throwable error = result.getThrowable();
				stackTrace = Arrays.toString(error.getStackTrace());
				exceptionType = error.getClass().getSimpleName();
				exceptionMessage = error.getMessage();
				
				failureDetails details = new failureDetails(testName, PageName, currentUrl, Locator, exceptionType, exceptionMessage, stackTrace);
				
				
		extentTestManager.getTest().fail(result.getThrowable());
		attachScreenshot(result);
		
		try {
			AIService service = new AIService();
			
			AIResponse aiResponse = service.failureAnalyzer(details);
			
			extentTestManager.getTest().info(aiResponse.getResponse());
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		 if (PageDriver.getDriver() == null) {
		        System.out.println("Driver is null. Screenshot skipped.");
		        return;
		    }

		 try {
	    String path = ScreenshotUtils.captureScreenshot(
	            PageDriver.getDriver(),
	            result.getMethod().getMethodName());

	    
	        extentTestManager.getTest().addScreenCaptureFromPath(path);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
}


