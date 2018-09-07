package com.crm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.base.TestBase;
import com.crm.util.TestUtil;

public class ContactsPage extends TestBase {

	TestUtil testUtil;

	@FindBy(xpath = "//td[@class='datacardtitle' and contains(text(),'Contacts')]")
	//@CacheLookup
	WebElement ContactsGrid;

	@FindBy(id = "first_name")
	//@CacheLookup
	WebElement firstName;

	@FindBy(id = "surname")
	//@CacheLookup
	WebElement surName;

	@FindBy(name = "client_lookup")
	//@CacheLookup
	WebElement companyName;

	@FindBy(xpath = "//input[@value = 'Load From Company']//following-sibling::input")
	//@CacheLookup
	WebElement saveButton;

	@FindBy(xpath = "//a[@_name]")
	//@CacheLookup
	WebElement companyEntered;
	
	@FindBy(xpath = "//div[@class='pagination']//a[contains(text(),'4')]")
	WebElement page2;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutButton;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
		testUtil = new TestUtil();
	}

	public void createNewContacts(String saluationTitle, String FirstName, String SurName, String CompanyName) {
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(saluationTitle);
		firstName.sendKeys(FirstName);
		surName.sendKeys(SurName);
		companyName.sendKeys(CompanyName);
		saveButton.click();
	}

	public void selectContactsByName(String name) {
		driver.findElement(
				By.xpath("//a[contains(text(),'" + name + "') and @context='contact']//parent::td//parent::tr//td"))
				.click();
	}
	
	public void logOutUser() {
		logoutButton.click();
	}


	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getFirstName() {
		return firstName.getText();
	}

	public String getSurName() {
		return surName.getText();
	}

	public String getCompanyName() {
		return companyEntered.getText();
	}

	public String getSaluationTitle() {
		
		Select select = new Select(driver.findElement(By.id("title")));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isContactsGridDisplayed() {
		return ContactsGrid.isDisplayed();
	}

	public boolean isRecordSelected(String name) {
		return driver
				.findElement(By.xpath("//a[contains(text(),'" + name
						+ "') and @context='contact']//parent::td//parent::tr//td//input[@type='checkbox']"))
				.isSelected();
	}
	
	public void gotoNextPage() {
		page2.click();
	}

}
