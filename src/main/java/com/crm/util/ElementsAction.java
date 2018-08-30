package com.crm.util;

import org.openqa.selenium.WebElement;

import com.crm.base.TestBase;

public class ElementsAction extends TestBase {

	public void click(WebElement ele) {

		try {
			//Remove sleep and implement fluent wait
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ele.click();
		


			
	}

}
