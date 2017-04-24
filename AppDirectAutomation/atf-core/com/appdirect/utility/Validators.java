package com.appdirect.utility;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public final class Validators extends Assert{

	private Validators() {
	}
	
	public static String getValidationMessage(WebElement webElement){
		return webElement.getAttribute("validationMessage");
	}
}

