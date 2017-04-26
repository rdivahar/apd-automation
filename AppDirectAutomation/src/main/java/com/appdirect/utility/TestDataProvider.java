package com.appdirect.utility;

import org.testng.annotations.DataProvider;



public class TestDataProvider extends DataGenerator{
	
	 @DataProvider(name = "ValidSignUpCreds")
	 public static Object[][] validSignUpEmail() {
	 return new Object[][] {{generateEmail()}, {generateEmail()}};
	 }
	 
	 @DataProvider(name = "ValidInternalSignUpCreds")
	 public static Object[][] validInternalSignUpEmail() {
	 return new Object[][] {{generateInternalDomainEmail()}, {generateInternalDomainEmail()}};
	 }
	 
	 @DataProvider(name = "AlreadyRegisteredEmail")
	 public static Object[][] alreadyRegisteredEmail() {
	 return new Object[][] {{generateEmail()}};
	 }
	 
	 @DataProvider(name = "NonActivatedEmail")
	 public static Object[][] nonActivatedEmail() {
	 return new Object[][] {{generateEmail()}};
	 }
	 
	 @DataProvider(name = "InValidEmailFormat")
	 public static Object[][] nonActisvatedEmail() {
	 return new Object[][] {{generateInvalidEmail()}, {generateNumber(2)}, {generateString(6)}};
	 }
	 
	 @DataProvider(name = "InValidLoginCreds")
	 public static Object[][] inValidLoginCreds() {
	 return new Object[][] {{generateEmail(), generateString(6)}, {generateEmail(), generateString(7)}};
	 }
}

