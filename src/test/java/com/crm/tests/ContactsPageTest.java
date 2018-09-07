package com.crm.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.base.TestBase;
import com.crm.pages.ContactsPage;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.crm.util.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	SoftAssert softAssert = new SoftAssert();

	public ContactsPageTest() {
		initialize();
		loginPage = new LoginPage();
	}

	@BeforeMethod
	public void setup() {
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
	}

	@Test(priority = 1)
	public void selectTest2CheckBoxTest() {
		contactsPage = homePage.clickOnContacts();
		contactsPage.gotoNextPage();
		contactsPage.selectContactsByName("test 2 last name 2");
		softAssert.assertTrue(contactsPage.isRecordSelected("test 2 last name 2"));
		contactsPage.selectContactsByName("test name last test name");
		softAssert.assertTrue(contactsPage.isRecordSelected("test name last test name"));
		contactsPage.logOutUser();
		
		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] getDataFromCRMExcel() {
		return TestUtil.getDataFromExcel("contacts");
	}

	@Test(priority = 2, dataProvider = "getDataFromCRMExcel")
	public void createNewContacts(String title, String fName, String lName, String cName) {
		contactsPage = homePage.clickOnNewContacts();
		contactsPage.createNewContacts(title, fName, lName, cName);
		softAssert.assertEquals(contactsPage.getFirstName(), fName);
		softAssert.assertEquals(contactsPage.getSurName(), lName);
		softAssert.assertEquals(contactsPage.getCompanyName(), cName);
		contactsPage.logOutUser();

		softAssert.assertAll();
	}


	@AfterClass
	public void tearDown() {
		driver.quit();
		driver=null;
	}

}