package com.crm.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.base.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;

public class HomePageTest extends TestBase {

	HomePage homePage;
	LoginPage loginPage;
	ContactsPage contactsPage;
	SoftAssert softAssert = new SoftAssert();

	public HomePageTest() {
		super();
		initialize();
		loginPage = new LoginPage();
	}

	@BeforeMethod
	public void setup() {
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
	}

	@Test(priority = 0)
	public void verifyPageTitleTest() {
		softAssert.assertEquals(homePage.getPageTitle(), "CRMPRO", "title did not match");
		homePage.logOutUser();
		
		softAssert.assertAll();
	}

	@Test(priority = 1)
	public void verifyContactsLinkTest() {
		contactsPage = homePage.clickOnContacts();
		String title = contactsPage.getPageTitle();
		softAssert.assertEquals(title, "CRMPRO", "Failed to verify Contacts Page Title.");
		softAssert.assertTrue(contactsPage.isContactsGridDisplayed());
		contactsPage.logOutUser();

		softAssert.assertAll();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		driver=null;
	}
}
