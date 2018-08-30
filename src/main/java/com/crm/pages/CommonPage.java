package com.crm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.base.TestBase;
import com.crm.util.TestUtil;

public class CommonPage extends TestBase {

	TestUtil testUtil;
	
	@FindBy(xpath = "//a[contains(text(),'Logout')]")
	WebElement logoutButton;
	
	
	public CommonPage(){
		
		PageFactory.initElements(driver, this);
		System.out.println(this);
	}
	
	
	public void logOutUser() {
		switchToFrameMainPanel();
		logoutButton.click();
		testUtil.switchToFrameDefaultContent();
	}
	
	public void switchToFrameMainPanel() {
		testUtil.switchToMainPanelFrame();
	}

	
	
}
