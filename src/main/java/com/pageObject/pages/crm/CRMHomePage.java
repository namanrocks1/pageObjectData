package com.pageObject.pages.crm;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.pageObject.base.Page;
import com.pageObject.pages.Accounts.AccountsPage;

public class CRMHomePage extends Page {

	//Home page of CRM
	public void verifyTasks() {
		getText("CRMLayout1_CSS");
		getText("CRMLayout2_CSS");
		getText("CRMLayout3_CSS");
		Assert.fail("Not found");
	}
	
}
