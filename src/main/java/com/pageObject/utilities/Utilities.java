package com.pageObject.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import com.pageObject.base.Page;
import com.relevantcodes.extentreports.LogStatus;

public class Utilities extends Page {
	
	
	public static String ScreenshotPath;
	public static  String ScreenshotName;
	
	public static void getScreenshot() throws IOException {
		
		TestTimeStamp st=new TestTimeStamp();
		ScreenshotName= st.getDate();
		File srcfile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+ScreenshotName));
		
	}
	
	
	public static void verify(String actual,String expected) {
		
		try {
			Assert.assertEquals(actual, expected);
		}
		catch(Throwable t){
			
			Reporter.log("Verification failed due to- "+ t.getMessage());
			Reporter.log("<br>");
			Reporter.log("<br>");
			Reporter.log("<a target='_blank' href="+TestTimeStamp.getDate()+"><img src="+ScreenshotName+" height=400 width=500></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			//test.log(LogStatus.FAIL, "Verification failed Due to" + t.getMessage());
			//test.log(LogStatus.FAIL, test.addScreenCapture(ScreenshotName));
		}
		
	}

	@DataProvider(name="dp")
	public Object[][] getData(Method m){
		
		String sheetName=m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
				
		System.out.println(rows);
		System.out.println(cols);
		Object[][] data = new Object[rows-1][1]; //[4][1]
		Hashtable<String,String> table=null;
		
		for(int rowNum=2;rowNum<=rows;rowNum++) {
			table=new Hashtable<String, String>();
			for(int colNum=0; colNum<cols;colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum)); 
				data[rowNum-2][0]=table; //[0][0] []
			}
		}
		
		return data;
		
		
	}
	
	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName="Test_Suite";
		int rows=excel.getRowCount(sheetName);
		
		for(int rNum=2;rNum<=rows;rNum++) {
			
			String cellValue=excel.getCellData(sheetName, "TCID", rNum);
			
			
			if(cellValue.equalsIgnoreCase(testName)) {
				
				String runValue=excel.getCellData(sheetName, "RunMode", rNum);
				
				if(runValue.equalsIgnoreCase("Y")) {
					
					return true;
			}
				else {
					return false;
				}
		}
		}
		return false;
	}
}
