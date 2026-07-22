package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class PageDriver {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver init() {
		
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		getDriver().manage().window().maximize();
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		
		return driver.get();
	}
	
	public static void quitDriver() {
		
		getDriver().quit();
		driver.remove();
	}
	

}
