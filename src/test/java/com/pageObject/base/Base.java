package com.pageObject.base;

import org.testng.annotations.AfterSuite;

public class Base extends Page {

	
	@AfterSuite
	public void teardown() {
		
		Page.quit();
	}
}
