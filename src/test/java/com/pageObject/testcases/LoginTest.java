package com.pageObject.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.pageObject.pages.HomePage;
import com.pageObject.pages.LoginPage;
import com.pageObject.pages.ZohoPage;
import com.pageObject.utilities.Utilities;


public class LoginTest {

	@Test(dataProviderClass=Utilities.class,dataProvider="dp")
	public void loginTest(Hashtable<String, String> data) {
		HomePage hp=new HomePage();
		LoginPage lp = hp.gotoLogin();
		ZohoPage zp =lp.doLogin(data.get("username"), data.get("password"));
	}
	
}
