package com.appdirect.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appdirect.common.TestBase;
import com.appdirect.constants.MessageConstants;
import com.appdirect.constants.TitleConstants;
import com.appdirect.constants.URLConstants;
import com.appdirect.pagefactory.HomePage;
import com.appdirect.pagefactory.LoginPage;
import com.appdirect.pagefactory.SignupPage;
import com.appdirect.utility.ExtentLogger;
import com.appdirect.utility.TestDataProvider;
import com.appdirect.utility.TestListener;

@Listeners(TestListener.class)
public class SignUpTests extends TestBase {
	
	public LoginPage loginPage;
	public SignupPage signupPage;
	
	@BeforeMethod
	public void signUpSetUp() throws Exception {
		homePage = new HomePage(getDriver());
		homePage.loginAndSwitchToMarketPlace();
		loginPage = new LoginPage(getDriver());
	}
	
	@Test(description="TC001 - Test SignUp via Available Access Triggers")
	public void testSignUpAccessTriggers() throws Exception{
		loginPage.clickSignUp();
		assertEquals(driver.getTitle(), TitleConstants.SIGNUP_PAGE);
		ExtentLogger.logPass("Verified Access Point I : User Can Access SignUp Page using SignUp Button");	
		signupPage = new SignupPage(getDriver());
		signupPage.gotoPreviousPage();
		loginPage.clickSignUp(true);
		assertEquals(driver.getTitle(), TitleConstants.SIGNUP_PAGE);
		ExtentLogger.logPass("Verified Access Point II : User Can Access SignUp Page using SignUp Footer Link");
	}
	
	@Test(dataProvider = "ValidSignUpCreds", dataProviderClass = TestDataProvider.class, description="TC002 - Test User Can SignUp with Valid Email Address")
	public void testValidUserSignUp(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailAddress);
		assertEquals(signupPage.successLabel.getText(), MessageConstants.SIGNUP_CONFIRMATION);
		ExtentLogger.logPass("Verified: User Can SignUp with Valid Email Address");
	}
	
	@Test
	(dataProvider = "ValidInternalSignUpCreds", dataProviderClass = TestDataProvider.class, 
	 description="TC002.A - Test an Internal User CAN SignUp with Valid Credentials")
	public void testValidInternalUserSignUp(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailAddress);
		assertEquals(signupPage.successLabel.getText(), MessageConstants.SIGNUP_CONFIRMATION_INTERNAL);
		ExtentLogger.logPass("Verified: The Internal User Can SignUp with Valid Email Address");
	}
	
	@Test(dataProvider = "AlreadyRegisteredEmail", dataProviderClass = TestDataProvider.class, 
    description="TC003 - Test User Cannot SignUp with Already Registered EmailAddress")
	public void testRegisteredUserCannotSignUp(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(getDriver());
		signupPage.doSignUp(emailAddress);
		signupPage.gotoPreviousPage();
		loginPage.clickSignUp();
		signupPage.doSignUp(emailAddress);
		assertEquals(signupPage.errorLabel.getText(), MessageConstants.EMAIL_ALREADY_REGISTERED);
		ExtentLogger.logPass("Verified: User Cannot SignUp with Already Registered EmailAddress");
	}
	
	@Test(description= "TC004 - Test User Cannot SignUp with Blank EmailAddress Entry")
	@Parameters({"blankEmailAddress"})
	public void testUserCannotSignUpWithBlankEmail(String emailEntry) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailEntry);
		assertEquals(signupPage.getValidationMessage(signupPage.userEmailTxt), MessageConstants.BLANK_EMAIL_FORMAT);
		ExtentLogger.logPass("Verified: The User Cannot SignUp with Blank EmailAddress Entry");
	}
	
	@Test(dataProvider = "InValidEmailFormat", dataProviderClass = TestDataProvider.class, description="TC005 - Test User Cannot SignUp with Invalid EmailAddress Format")
	public void testSignUpWithInvalidEmail(String emailEntry) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailEntry);
		assertTrue(signupPage.getValidationMessage(signupPage.userEmailTxt).contains(MessageConstants.INVALID_EMAIL_FORMAT));
		ExtentLogger.logInfo("The Application Notifies = "+signupPage.getValidationMessage(signupPage.userEmailTxt));
		ExtentLogger.logPass("Verified: User Cannot SignUp with Invalid EmailAddress Format");
	}
	
	@Test(dataProvider = "NonActivatedEmail", dataProviderClass = TestDataProvider.class, description="TC006 - Test Non Activated User Cannot Enter Account Setup Page OnRefresh")
	public void testSignUpNonActivatedEmailOnRefresh(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailAddress);
		signupPage.refreshPage();
		assertEquals(driver.getTitle(), TitleConstants.SIGNUP_PAGE);
		ExtentLogger.logPass("Verified: User Cannot Enter with Already Registered EmailAddress");
	}
	
	@Test(dataProvider = "InValidLoginCreds", dataProviderClass = TestDataProvider.class, description="TC007 - Test a User Can SignUp immediately after an Incorrect Login Attempt")
	public void testSignUpAfterInvalidLogin(String userEmail, String userPassword) throws Exception{
		loginPage.login(userEmail,userPassword);
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(userEmail);
		assertEquals(signupPage.successLabel.getText(), MessageConstants.SIGNUP_CONFIRMATION);
		ExtentLogger.logPass("Verified: The User Can SignUp immediately after an Incorrect Login Attempt");
	}
	
	@Test(dataProvider = "ValidSignUpCreds", description="TC008 - Test Application behaviour after a successful User SignUp Registration", dataProviderClass = TestDataProvider.class)
	public void testSignUpRegistrationConfirmation(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.doSignUp(emailAddress);
		assertTrue(signupPage.validateSignUpConfirmation(emailAddress));
		ExtentLogger.logPass("Verified: the Application behaviour after a successful User SignUp Registration");
	}
	
	@Test(description="TC009 - Test Application behavior after a successful User SignUp Registration")
	@Parameters({"loginEmail"})
	public void testSignUpFooterArea(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		assertTrue(signupPage.validateFooterEmbeddedLinks());
		signupPage.doSignUp(emailAddress);
		assertTrue(signupPage.validateFooterEmbeddedLinks());
		ExtentLogger.logPass("Verified: the Application behaviour after a successful User SignUp Registration");
	}
	
	@Test(dataProvider = "ValidSignUpCreds", description="TC010 - Test AppDirect Logo Behavior at SignUp Page", dataProviderClass = TestDataProvider.class)
	public void testSignUpAccessHomeWithLogo(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		signupPage.clickAppDirectLogo();
		assertEquals(getCurrentPageURL(), URLConstants.MARKETPLACE_HOME);
		signupPage.gotoPreviousPage();
		signupPage.doSignUp(emailAddress);
		signupPage.clickAppDirectLogo();
		assertEquals(getCurrentPageURL(), URLConstants.MARKETPLACE_HOME);
		ExtentLogger.logPass("Verified: the AppDirect Logo behaviour at SignUp Page");
	}
	
	@Test(dataProvider = "ValidSignUpCreds", description="TC011 - Test SignUp Email Field Validations", dataProviderClass = TestDataProvider.class)
	public void testSignUpEmailFieldValidation(String emailAddress) throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		assertEquals(signupPage.getDefaultEmailFieldText(), MessageConstants.DEFAULT_EMAIL);
		assertTrue(signupPage.isSignUpEmailFieldHasFocus());
		assertTrue(signupPage.isSignUpEmailFieldDisplays(emailAddress));
		ExtentLogger.logPass("Verified: the AppDirect Logo behaviour at SignUp Page");
	}
	
	@Test(description="TCNF001 - Test the Localization Behaviour Of Application at SignUp Page")
	public void testSignUpLocalization() throws Exception{
		loginPage.clickSignUp();
		signupPage = new SignupPage(driver);
		//TODO: Localization Validation
		ExtentLogger.logPass("Verified: the Localization Behaviour Of Application at SignUp Page");
	}
	
	@Test(description="TC000FF - Test to intentionally simulate Failure case to Check Error Capturing")
	public void testSimulateFailure() throws Exception{
		assertTrue(false,"Failed to Simulate the Failure -");
		ExtentLogger.logWarning("Run Into an Assertion Error - Possible Product Defect");
		ExtentLogger.logFail("INFO: Test The ScreenCapture Capability");
	}
}

