package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Framework.BaseTest;
import Framework.PageDriver;
import Utilities.waitUtilis;

public class LoginPage extends BaseTest{
	
	
	private WebDriver driver = PageDriver.getDriver();
	waitUtilis wait = new waitUtilis(driver);
	
	
	//locators
	
	By username = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By LoginBtn = By.xpath("//button[@type='submit']");
	By profile = By.xpath("//p[@class='oxd-userdropdown-name']");
	By logoutBtn = By.xpath("//a[@href='/web/index.php/auth/logout']");
	
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
	
	public void clickLogoutBtn()
	{
		wait.elementToBeVisible(profile);
		driver.findElement(profile).click();
		driver.findElement(logoutBtn).click();
		wait.elementToBeVisible(username);
	}
	
	
	//business logic
	
	public void login(String user,String pass) {
		
//		waitUtilis wait = new waitUtilis(driver);
		wait.elementToBeVisible(username);
		enterUsername(user);
		enterPassword(pass);
		clickLoginBtn();
	}

}
