package Framework;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	ConfigReader config;
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		config = new ConfigReader();
		driver =PageDriver.init();
		String url = config.getProperty("url");
		driver.get(url);
	}
	
	@AfterMethod
	public void teardown() {
		PageDriver.quitDriver();
	}
}