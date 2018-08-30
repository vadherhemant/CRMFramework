package com.crm.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.CommonPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	CommonPage commonPage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialize();
		loginPage = new LoginPage();
	}
	
	
	@Test (priority=0)
	public void pageTitleTest() {
		String title = loginPage.getPageTitle();
		assertEquals(title, "#1 Free CRM software in the cloud for sales and service", "Thte page title teste got failed");
	}
	
	@Test (priority=1)
	public void LoginTest() {
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
		String HomePageTitle = homePage.getPageTitle();
		assertEquals(HomePageTitle, "CRMPRO","Login Failed this is not a home page title.");
		
		assertEquals(true, homePage.isLoggedInUserNameDisplayed());
		homePage.logOutUser();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
