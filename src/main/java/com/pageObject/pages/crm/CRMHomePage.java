package com.pageObject.pages.crm;

import org.openqa.selenium.By;

import com.pageObject.base.Page;
import com.pageObject.pages.Accounts.AccountsPage;

public class CRMHomePage extends Page {

	
	public void verifyTasks() {
		getText("CRMLayout1_CSS");
		getText("CRMLayout2_CSS");
		getText("CRMLayout3_CSS");
	}
	
	public AccountsPage gotoAccounts() {
		click("Accounts_CSS");
		
		return new AccountsPage();
	}
	
}
