package com.appdirect.tests;

import com.appdirect.common.TestBase;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appdirect.pagefactory.HomePage;
import com.appdirect.pagefactory.LoginPage;
import com.appdirect.pagefactory.SignupPage;
import com.appdirect.utility.ExtentLogger;
import com.appdirect.utility.Log;
import com.appdirect.utility.TestDataProvider;


public class LoginTests extends TestBase {
	
	LoginPage loginPage;
	SignupPage signupPage;
	
	@Test(description="BATS001 - Test whether the user is taken to AppDirect Marketplace Page in a new Tab on clicking the Login Button")
	public void testLoginOpensNewTab(){
		homePage = new HomePage(getDriver());
		Log.info(driver.getTitle());
		homePage.clickLogin();
		assertTrue(homePage.verifyMarketplaceLoadedAsNewTab());
		ExtentLogger.logPass("Pass - testLoginOpensNewTab"); 
	}
	
	@Test(description="BATS002 - Test whether a user Can Access the Login via Available Trigger Points")
	public void testLoginAccessPoints() throws Exception{
		homePage = new HomePage(getDriver());
		homePage.loginAndSwitchToMarketPlace();
		loginPage = new LoginPage(getDriver());
		assertTrue(loginPage.userNameTxt.isDisplayed());
		ExtentLogger.logPass("Pass - testLoginAccessPoints"); 
	}
	
	@Test(description="BATS003 - Test whether a User Can SignUp with Valid Credentials", dataProvider = "ValidSignUpCreds", dataProviderClass = TestDataProvider.class) 
	public void testValidUserCanSignUp(@Optional String emailAddress) throws Exception{
		homePage = new HomePage(getDriver());
		homePage.loginAndSwitchToMarketPlace();
		loginPage = new LoginPage(getDriver());
		loginPage.clickSignUp();
		signupPage = new SignupPage(getDriver());
		assertTrue(signupPage.doSignUp(emailAddress));
		ExtentLogger.logPass("Test Passed");
	}
}

