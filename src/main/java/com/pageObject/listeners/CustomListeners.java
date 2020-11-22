package com.pageObject.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.pageObject.base.Page;
import com.pageObject.utilities.ExcelReader;
import com.pageObject.utilities.MonitoringMail;
import com.pageObject.utilities.TestConfig;
import com.pageObject.utilities.TestTimeStamp;
import com.pageObject.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends Page implements ITestListener, ISuiteListener  {
	
	public static String messageBody;
	
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\resource\\excel\\testdata.xlsx");
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test =rep.startTest(result.getName().toUpperCase());
	
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(LogStatus.PASS, result.getName().toUpperCase()+"Test is passed" );
		rep.endTest(test);
		rep.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		
		
		// TODO Auto-generated method stub
		Reporter.log("Login failed");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			Utilities.getScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a target='_blank' href="+TestTimeStamp.getDate()+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href="+TestTimeStamp.getDate()+"><img src="+Utilities.ScreenshotName+" height=400 width=500></img></a>");
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Test is failed"+"Failed due to exception:- "+ result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.ScreenshotName));
		
		rep.endTest(test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		test.log(LogStatus.SKIP, result.getName()+"Skipped the testcase as the status is NO");
		rep.endTest(test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/PageObjectTest/HTML_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
