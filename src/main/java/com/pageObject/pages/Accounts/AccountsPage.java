package com.pageObject.pages.Accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pageObject.base.Page;

public class AccountsPage extends Page {

	
	public void verfiyButton() {
		driver.findElement(By.cssSelector("button#importButton")).isDisplayed();
		driver.findElement(By.cssSelector("lyte-drop-button#combo")).isDisplayed();
		
	}
	
	public void verifyFilter() {
		driver.findElement(By.cssSelector("div#lv_left_filter")).isDisplayed();
	}
	
	public void verifyBottomEle() {
		driver.findElement(By.cssSelector("div.totalCounttoLeft")).isDisplayed();
	}
	
	public void verifycheckbox() {
		driver.findElement(By.cssSelector("span.vam.chkbxIcon")).isSelected();
	}
	
	public CreateAccountPage gotoCreateAccount() {
		click("CreateAccounts_CSS");
		
		return new CreateAccountPage();
	}
}

