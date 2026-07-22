package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		
		if (extent==null)
		{
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
			spark.config().setReportName("Login Test");
			spark.config().setDocumentTitle("Login Test Report");
			spark.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Sunder");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}

}
