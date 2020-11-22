package com.pageObject.base;

import org.openqa.selenium.WebDriver;

import com.pageObject.pages.Accounts.AccountsPage;

public class TopMenu {
	
	//Menu page
	public static WebDriver driver;
	public TopMenu(WebDriver driver) {
		
		this.driver=driver;
	}
	
	
	
	public AccountsPage gotoAccounts() {
		Page.click("Accounts_CSS");
		
		return new AccountsPage();
		
	}

}
