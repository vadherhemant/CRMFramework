package com.crm.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.base.TestBase;
import com.crm.pages.CommonPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	CommonPage commonPage;
	SoftAssert softAssert = new SoftAssert();

	public LoginPageTest() {
		super();
		initialize();
		loginPage = new LoginPage();
	}

	@Test(priority = 0)
	public void pageTitleTest() {
		String title = loginPage.getPageTitle();
		softAssert.assertEquals(title, "#1 Free CRM software in the cloud for sales and service",
				"The page title teste got failed");

		softAssert.assertAll();
	}

	@Test(priority = 1)
	public void LoginTest() {
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
		String HomePageTitle = homePage.getPageTitle();
		softAssert.assertEquals(HomePageTitle, "CRMPRO", "Login Failed this is not a home page title.");
		softAssert.assertEquals(true, homePage.isLoggedInUserNameDisplayed());
		homePage.logOutUser();

		softAssert.assertAll();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		driver = null;
	}

}
