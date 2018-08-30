package com.crm.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.base.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.TestUtil;

public class HomePageTest extends TestBase{
	
	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	
/*	@Test (priority = 0)
	public void verifyPageTitleTest() {
		String title = homePage.getPageTitle();
		assertEquals(title, "CRMPRO", "title did not match");
	}*/
	
	@Test(priority = 1)
	public void verifyContactsLinkTest() {
		testUtil = new TestUtil();
		contactsPage = homePage.clickOnContacts();
		String title = contactsPage.getPageTitle();
		assertEquals(title, "CRMPRO","Failed to verify Contacts Page Title.");
		assertTrue(contactsPage.isContactsGridDisplayed());
	}

	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
