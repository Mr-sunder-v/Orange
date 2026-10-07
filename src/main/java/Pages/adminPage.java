package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Framework.BaseTest;
import Framework.PageDriver;
import Utilities.waitUtilis;

public class adminPage extends BaseTest {
	
	private WebDriver driver = PageDriver.getDriver();
	waitUtilis wait = new waitUtilis(driver);
	
	
	//locators
	
	By admin = By.xpath("//span[text()='Admin']");
	
	By addBtn = By.xpath("//button[text()=' Add ']");
	By userRole = By.xpath("(//div[@tabindex='0'])[1]");
	By selectRole = By.xpath("(//span[text()='Admin'])[2]");
	By employeeName = By.xpath("//input[@placeholder='Type for hints...']");
	By selectdropdown = By.xpath("//span[text()='Timothy Lewis Amiano']");
	By status = By.xpath("(//div[@tabindex='0'])[2]");
	By selectStatus = By.xpath("//span[text()='Enabled']");
	By password = By.xpath("(//input[@type='password'])[1]");
	By conformPassword = By.xpath("(//input[@type='password'])[2]");
	By saveBtn = By.xpath("//button[@type='submit']");
	By username = By.xpath("(//label[text()='Username']/following::div//input)[1]");
	By sucessMsg = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
	
	//Actions
	
	public void clickAdminTab()
	{
		driver.findElement(admin).click();
	}
	
	public void clickAddBtn()
	{
		driver.findElement(addBtn).click();
	}
	public void selectRole()
	{
		driver.findElement(userRole).click();
		driver.findElement(selectRole).click();
	
	}
	
	public void enterEmployeeName(String worker)
	{
		driver.findElement(employeeName).sendKeys(worker);
		wait.elementToBeVisible(selectdropdown);
		driver.findElement(selectdropdown).click();
	}
	
	public void enterName(String name)
	{
		driver.findElement(username).sendKeys(name);
	}
	
	public void selectStatus()
	{
		driver.findElement(status).click();
		driver.findElement(selectStatus).click();
	}
	
	public void enterPassword(String passwords)
	{
		driver.findElement(password).sendKeys(passwords);
		driver.findElement(conformPassword).sendKeys(passwords);
	}

	public void clickSaveBtn()
	{
		driver.findElement(saveBtn).click();
	}
	public String getMessage()
	{
		wait.elementToBeVisible(sucessMsg);
		String message = driver.findElement(sucessMsg).getText();
		return message;
	}
	
	public void createuser(String name,String passwords,String worker)
	{
	
		wait.elementToBeVisible(admin);
		clickAdminTab();
		wait.elementToBeVisible(addBtn);
		clickAddBtn();
		wait.elementToBeVisible(userRole);
		selectRole();
		enterEmployeeName(worker);
		enterName(name);
		selectStatus();
		enterPassword(passwords);
		clickSaveBtn();
		
	}
}
