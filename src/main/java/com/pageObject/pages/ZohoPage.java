package com.pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pageObject.base.Page;
import com.pageObject.pages.crm.CRMHomePage;

public class ZohoPage extends Page {

	
	
	public CRMHomePage gotoCRM() {
		
		click("CRM_XPATH");
		
		return new CRMHomePage();
	}
	
}
