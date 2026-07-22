package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class waitUtilis {
	private WebDriverWait wait;
	
	public waitUtilis(WebDriver driver)
	{
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void elementToBeVisible(By locator)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	

}
