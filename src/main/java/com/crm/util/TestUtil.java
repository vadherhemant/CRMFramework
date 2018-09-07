package com.crm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 15;
	public static long IMPLICIT_WAIT = 15;
	protected static String DataProviderXLPath = "src/main/resources/FreeCRMTestData.xlsx";
	                                             
	
	public void switchToMainPanelFrame() {
		driver.switchTo().frame("mainpanel");
	}
	
	public static void CaptureScreenshot()  {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(srcFile, new File(currDir + "/CRM_Test_Screenshots/" + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object[][] getDataFromExcel(String sheetName) {
		FileInputStream file = null;
		Workbook book = null;
		
		try {
			file = new FileInputStream(DataProviderXLPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Sheet sheet = book.getSheet(sheetName);
		
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for(int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
			
		}
		return data;
	}

	public void switchToFrameDefaultContent() {
		driver.switchTo().defaultContent();
	}
}
