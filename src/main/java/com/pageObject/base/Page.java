package com.pageObject.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObject.utilities.ExcelReader;
import com.pageObject.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {
	
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log =  Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\resource\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browsertest;
	
	
		public static WebDriver driver;
		public static TopMenu menu;
		
		
		public Page() {
		
		if(driver==null) {
			
			try {
				fis = new FileInputStream("C:\\Users\\Shree\\eclipse-workspace\\PageObjectBasics\\src\\test\\resources\\com\\w2a\\resource\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("ObjectRepository File Loaded");
			
			try {
				fis= new FileInputStream("C:\\Users\\Shree\\eclipse-workspace\\PageObjectBasics\\src\\test\\resources\\com\\w2a\\resource\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.debug("Config File Loaded");
			
			
			//Jenkins Browser Filter COnfiguration
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
				
				browsertest=System.getenv("browser");
			} else {
				browsertest=Config.getProperty("browser");
			}
			
			Config.setProperty("browser", browsertest);
			
			
			if(Config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\resource\\executables\\chromedriver.exe");
				log.debug("ChromeDriver setup done");	
		
		HashMap<String,Object> prefs=new HashMap<String, Object>();
		
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		
		ChromeOptions options =new ChromeOptions();
		
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-infobars");
		
		
		driver = new ChromeDriver(options);
		}
			else if(Config.getProperty("browser").equals("firefox"))
			{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\resource\\executables\\geckodriver.exe");
			driver= new FirefoxDriver();
			log.debug("Firefox Driver setup done");
			
		}
		
		else if(Config.getProperty("browser").equals("IExplorer"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\resource\\executables\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			log.debug("InternetExplorer Setup setup done");
		}
				
			
			
			driver.get(Config.getProperty("siteurl"));
			log.debug("Site URL launched");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("timeout")), TimeUnit.SECONDS);
			wait= new WebDriverWait(driver,15);
			menu=new TopMenu(driver);
	
	}
		}
		
	public static void quit() {
		driver.quit();
	}	
		
	public static void click(String locator) {
		
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicked on the locator:- "+locator);
		
	}
	
	public static void type(String locator, String text) {
		if(locator.endsWith("_CSS")) {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty(locator)))).sendKeys(text);
		}
		else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(text);
			
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(text);
		}
		test.log(LogStatus.INFO, "Clicked on the locator:- "+locator+"Enter the value:- "+text);
		
	}
	
	public static String textvalue;
	
	public static void getText(String locator) {
		
		if(locator.endsWith("_CSS")) {
			
			textvalue=wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty(locator)))).getText();
		}
		else if(locator.endsWith("_XPATH")) {
			textvalue=driver.findElement(By.xpath(OR.getProperty(locator))).getText();
			
		}else if(locator.endsWith("_ID")) {
			textvalue=driver.findElement(By.id(OR.getProperty(locator))).getText();
		}
		test.log(LogStatus.INFO, "Clicked on the locator:- "+locator+"Enter the value:- "+textvalue);
	}
	
	public static boolean isElementPresent(By by) {
		
		try{
			driver.findElement(by);
			return true;
		}
		catch(Exception e) {
			
			return false;
		}
	}
	
	
	
	public static WebElement dropdown;
	
	public void select(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
		else if(locator.endsWith("_XPATH")) {
			dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
			
		}else if(locator.endsWith("_ID")) {
			dropdown=driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Locator is:- "+locator+"Entered value in the locator "+value);
		
	}
}
