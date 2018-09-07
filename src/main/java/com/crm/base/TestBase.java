package com.crm.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.util.TestUtil;
import com.crm.util.WebEventListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties properties;
	public static EventFiringWebDriver e_driver;


	public TestBase() {

		properties = new Properties();

		try { 
			FileInputStream ip = new FileInputStream("src/main/resources/Config.Properties");
			properties.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialize() {
		
		String browserName = properties.getProperty("BrowserName");

		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", properties.getProperty("ChromeDriverPath"));
			driver = new ChromeDriver();
		} 
		else if (browserName.equalsIgnoreCase("FireFox")) {
			System.setProperty("webdriver.gecko.driver", properties.getProperty("FFDriverPath"));
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", properties.getProperty("EdgeDriverPath"));
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver", properties.getProperty("IEDriverPath"));
			driver = new InternetExplorerDriver();
		}

/*		e_driver = new EventFiringWebDriver(driver);
		WebEventListener eventListener = new WebEventListener();
		e_driver.register(eventListener);
		
		driver = e_driver;*/
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(properties.getProperty("url"));
		
		
	}

}
