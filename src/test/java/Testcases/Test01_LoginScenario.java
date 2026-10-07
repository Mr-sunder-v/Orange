package Testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Framework.BaseTest;
import Listeners.listeners;
import Pages.LoginPage;
import Utilities.extentTestManager;
@Listeners(listeners.class)
public class Test01_LoginScenario extends BaseTest {
	@Test
	public void ValidateLogintest() {
		LoginPage login = new LoginPage();
		extentTestManager.getTest().info("Entering the details");
		login.login("Admin", "admin123");
		login.clickLogoutBtn();
		
	}

}
