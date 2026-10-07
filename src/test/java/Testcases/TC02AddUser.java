package Testcases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Framework.BaseTest;
import Listeners.listeners;
import Pages.LoginPage;
import Pages.adminPage;
import Utilities.extentTestManager;

@Listeners(listeners.class)
public class TC02AddUser extends BaseTest {
	@Test
	public void addUser()
	{
		LoginPage page = new LoginPage();
		extentTestManager.getTest().info("Login Page");
		page.login("Admin","admin123");
		
		adminPage admin = new adminPage();
		extentTestManager.getTest().info("Admin Page");
		admin.createuser("Sunder2", "Test@12345","Timothy Lewis Amiano");
		
		String message = admin.getMessage();
		
		Assert.assertTrue(message.contains("Successfully Saved"));
		page.clickLogoutBtn();
	}

}
