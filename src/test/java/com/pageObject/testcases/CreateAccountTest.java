package com.pageObject.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.pageObject.base.Page;
import com.pageObject.pages.ZohoPage;
import com.pageObject.pages.Accounts.AccountsPage;
import com.pageObject.pages.Accounts.CreateAccountPage;
import com.pageObject.pages.crm.CRMHomePage;
import com.pageObject.utilities.Utilities;

public class CreateAccountTest extends Page {

	
	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void createAccountTest(Hashtable<String, String> data) {
		
		ZohoPage zp = new ZohoPage();
		CRMHomePage cp=zp.gotoCRM();
		cp.verifyTasks();
		AccountsPage ap=Page.menu.gotoAccounts();
		CreateAccountPage ca=ap.gotoCreateAccount();
		ca.AccountName(data.get("name"));
		
	}
}
