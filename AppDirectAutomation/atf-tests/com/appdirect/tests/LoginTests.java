package com.appdirect.tests;

import com.appdirect.common.TestBase;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appdirect.pagefactory.HomePage;
import com.appdirect.pagefactory.LoginPage;
import com.appdirect.pagefactory.SignupPage;
import com.appdirect.utility.ExtentLogger;


public class LoginTests extends TestBase {
	
	HomePage homePage;
	LoginPage loginPage;
	SignupPage signupPage;
		
	@Test(description="BATS001 - Test whether a user Can Access the Login via Available Trigger Points")
	public void testLoginAccessPoints() throws Exception{
		homePage = new HomePage(getDriver());
		homePage.clickLogin();
		loginPage = new LoginPage(getDriver());
		assertTrue(loginPage.userNameTxt.isDisplayed());
		ExtentLogger.logPass("Pass - AccessPoint I"); 
		super.gotoHomePage();
		homePage.clickLogin(true);
		assertTrue(loginPage.userNameTxt.isDisplayed());
		ExtentLogger.logPass("Pass - AccessPoint II");
	}
	
	@Test(description="TC002 - Test whether a User Can SignUp with Valid Credentials")
	@Parameters({"validEmail"})
	public void testValidUserCanSignUp(String emailAddress) throws Exception{
		homePage = new HomePage(getDriver());
		homePage.clickLogin();
		loginPage = new LoginPage(getDriver());
		loginPage.clickSignUp();
		signupPage = new SignupPage(getDriver());
		assertTrue(signupPage.doSignUp(emailAddress));
		ExtentLogger.logPass("Machan Passed !!");
	}
}

