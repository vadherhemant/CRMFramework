package com.crm.tests;

import org.testng.annotations.AfterMethod;
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
	}

	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage();
		homePage = loginPage.loginCRM(properties.getProperty("username"), properties.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getDataFromCRMExcel() {
		return TestUtil.getDataFromExcel("contacts");
	}

	@Test(priority = 2, dataProvider = "getDataFromCRMExcel")
	public void createNewContacts(String title, String fName, String lName, String cName) {
		contactsPage = homePage.clickOnNewContacts();
		//contactsPage.createNewContacts("Mr.", "firstName entered4", "surName entered4", "companyName entered4");
		contactsPage.createNewContacts(title, fName, lName, cName);
		softAssert.assertEquals(contactsPage.getFirstName(), fName);
		softAssert.assertEquals(contactsPage.getSurName(), lName);
		softAssert.assertEquals(contactsPage.getCompanyName(), cName);
		//System.out.println(contactsPage.getSaluationTitle());
		homePage.logOutUser();
		
		softAssert.assertAll();
	}

/*	@Test(priority = 1)
	public void selectTest2CheckBoxTest() {
		contactsPage = homePage.clickOnContacts();
		softAssert.assertFalse(contactsPage.isRecordSelected("test 2 last name 2"));
		contactsPage.selectContactsByName("test 2 last name 2");
		softAssert.assertTrue(contactsPage.isRecordSelected("test 2 last name 2"));

		softAssert.assertFalse(contactsPage.isRecordSelected("test name last test name"));
		contactsPage.selectContactsByName("test name last test name");
		softAssert.assertTrue(contactsPage.isRecordSelected("test name last test name"));
		homePage.logOutUser();
		softAssert.assertAll();
	}*/

	
	@AfterMethod
	public void tearDown() {
		 //driver.quit();
	}

}