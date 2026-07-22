package Utilities;

import com.aventstack.extentreports.ExtentTest;

public class extentTestManager {
	
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	public static ExtentTest getTest() {
		return test.get();
	}
	
	public static void setTest(ExtentTest extenttest)
	{
		test.set(extenttest);
	}
	public static void unload()
	{
		test.remove();
	}

}
