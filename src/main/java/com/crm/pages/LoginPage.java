package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;
import com.crm.util.ElementsAction;

public class LoginPage extends TestBase {

	// Locators
	@FindBy(name = "username")
	WebElement usernameTextBox;

	@FindBy(name = "password")
	WebElement PasswordTextBox;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement LoginButton;

	@FindBy(xpath = "//img[@alt='free crm logo']")
	WebElement FreeCRMLogo;

	// Constructor
	public LoginPage() {
		PageFactory.initElements(driver, this);

	}

	// Methods
	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public void enterUsername(String userName) {
		usernameTextBox.sendKeys(properties.getProperty("username"));
	}

	public void enterPassword(String userPassword) {
		PasswordTextBox.sendKeys(properties.getProperty("password"));
	}

	public void clickLogin() {
		ElementsAction EA = new ElementsAction();
		EA.click(LoginButton);
	}
	
	public HomePage loginCRM(String user, String password) {
			enterUsername(user);
			enterPassword(password);
			clickLogin();
			return new HomePage();

	}
}
