package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Framework.BaseTest;
import Framework.PageDriver;
import Utilities.waitUtilis;

public class LoginPage extends BaseTest{
	
	
	private WebDriver driver = PageDriver.getDriver();
	
	
	//locators
	
	By username = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='pass']");
	By LoginBtn = By.xpath("//button[@type='submit']");
	
	//Actions
	public void enterUsername(String user) {
		driver.findElement(username).sendKeys(user);
	}
	public void enterPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}
	public void clickLoginBtn() {
		driver.findElement(LoginBtn).click();
	}
	
	
	//business logic
	
	public void login(String user,String pass) {
		
		waitUtilis wait = new waitUtilis(driver);
		wait.elementToBeVisible(username);
		enterUsername(user);
		enterPassword(pass);
		clickLoginBtn();
	}

}
