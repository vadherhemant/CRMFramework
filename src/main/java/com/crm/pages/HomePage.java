package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;
import com.crm.util.TestUtil;

public class HomePage extends TestBase {

	TestUtil testUtil;

	@FindBy(xpath = "//td[contains(text(),'User: Hemant Vadher')]")
	WebElement LoggedInUserName;

	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutButton;

	@FindBy(xpath = "//a[@title='Contacts']")
	WebElement contactsPageElement;

	@FindBy(xpath = "//a[@title='New Contact']")
	WebElement newContact;

	public HomePage() {
		PageFactory.initElements(driver, this);
		testUtil = new TestUtil();
	}

	public ContactsPage clickOnNewContacts() {
		testUtil.switchToMainPanelFrame();
		Actions builder = new Actions(driver);
		builder.moveToElement(contactsPageElement).build().perform();
		newContact.click();
		return new ContactsPage();

	}

	public ContactsPage clickOnContacts() {
		testUtil.switchToMainPanelFrame();
		contactsPageElement.click();
		return new ContactsPage();
	}

	public String getPageTitle() {
		String title = driver.getTitle();

		return title;
	}

	public void logOutUser() {
		//switchToFrameMainPanel();
		logoutButton.click();
		//testUtil.switchToFrameDefaultContent();
	}

	public boolean isLoggedInUserNameDisplayed() {
		switchToFrameMainPanel();
		boolean isDisplayed = LoggedInUserName.isDisplayed();
		testUtil.switchToFrameDefaultContent();
		return isDisplayed;
	}

	public void switchToFrameMainPanel() {
		testUtil.switchToMainPanelFrame();
	}

}
