package com.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pageObject.base.Page;

public class HomePage extends Page {
	
	public void gotoSupport() {
		
		driver.findElement(By.cssSelector(".zh-support")).click();
	}
	
	public LoginPage gotoLogin() {
		
		click("Signin_CSS");
		
		return new LoginPage();
	}
	
	public void gotoSignUp() {
		driver.findElement(By.cssSelector(".zh-signup")).click();
	}
	
	public void getWFH() {
		
		driver.findElement(By.xpath("//p[contains(text(),'A suite of web and mobile applications')]")).getText();
	}
	
}
