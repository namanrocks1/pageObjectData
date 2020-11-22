package com.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pageObject.base.Page;
import com.pageObject.pages.Accounts.AccountsPage;
import com.pageObject.pages.crm.CRMHomePage;

public class LoginPage extends Page {

	public ZohoPage doLogin(String username, String password) {
		
		
		type("userName_CSS",username);
		click("Next_CSS");
		type("Pass_CSS",password);
		click("Next_CSS");
		
		return new ZohoPage();
	}
	
	public void gotoforgetPass() {
		driver.findElement(By.cssSelector("a[onclick='goToForgotPassword();']")).click();
	}
	
	
	public void VerifySecureText() {
		driver.findElement(By.cssSelector("div.product_head")).getText();
	}
	
	
	
}
