package com.pageObject.pages.Accounts;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pageObject.base.Page;

public class CreateAccountPage extends Page {

	
	public void AccountName(String name) {
		type("Name_CSS",name);
	}
}
