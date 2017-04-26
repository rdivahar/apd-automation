package com.appdirect.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;
import com.appdirect.common.PageBase;
import com.appdirect.constants.MessageConstants;
import com.appdirect.constants.URLConstants;
import com.appdirect.utility.ExtentLogger;

/**
* Extends the PageBase and Contains Page Objects and Methods of SignUp Page.
* @author sdivahar
*/
public class SignupPage extends PageBase {

	WebDriver driver;
	SoftAssert assertSafe;
	
	public SignupPage(WebDriver _driver) {
		super(_driver);
	}

	@FindBy(name="emailAddress")	
	public WebElement userEmailTxt;

	@FindBy(name="userSignupButton")
	private WebElement signUpBtn;
	
	@FindBy(id="signupLoginLink")
	public WebElement loginLink;
	
	@FindBy(id="partnerRegisterLink")
	public WebElement parterSignupLink;
	
	@FindBy(css=".adb-primary_nav--link.en")
	private WebElement appDirectLogo;
	
	@FindBy(css=".footer__appdirect>a")
	private WebElement copyRightsLink;
	
	@FindBy(linkText="Help Center")
	private WebElement helpCenterLink;
	
	@FindBy(linkText="Privacy Policy")
	private WebElement privPolicyLink;
	
	@FindBy(id="terms")
	private WebElement termsConditionsLink;
	
	@FindBy(linkText="Contact")
	private WebElement contactLink;
	
	@FindBy(css=".js-content>p")
	public WebElement errorLabel;
	
	@FindBy(css=".adb-local_alert--content")
	public WebElement successNotification;
	
	@FindBy(css=".adb-local_alert--content>h3")
	public WebElement successLabel;
	
	@FindBy(css=".adb-local_alert--content>p")
	public WebElement successContentLabel;
	
	public Boolean doSignUp(String emailAddress){
		try {
			clearAndSendKeys(userEmailTxt, emailAddress);
			click(signUpBtn);
			this.ensurePageReadyState();
			ExtentLogger.logInfo("Clicked SignUp With Username : "+emailAddress);
			return true;
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While Signing Up with Username : "+emailAddress);
			return false;
		}
	}
	
	public void clickAppDirectLogo(){
		try {
			click(appDirectLogo);
			ExtentLogger.logInfo("Clicked AppDirect Logo at the SignUp Page");
		} catch (Exception e) {	
	}
	}
	
	public String getDefaultEmailFieldText(){
		userEmailTxt.clear();
		return getPlaceHolderText(userEmailTxt);
	}
	
	public Boolean isSignUpEmailFieldHasFocus(){
		Boolean bFocus = false;
		try {
			bFocus = hasActiveFocus(userEmailTxt);
			ExtentLogger.logInfo("The Active Focus is On - ["+userEmailTxt.getAttribute("name")+"] Field");
		} catch (Exception e){
			ExtentLogger.logError("Failed While Getting the Active Focus Status Of - "+userEmailTxt.getText());
		}
		return bFocus;
	}
	
	public Boolean isSignUpEmailFieldDisplays(String enteredEmail){
		Boolean bDisplay = false;
		try {
			clearAndSendKeys(userEmailTxt, enteredEmail);
			bDisplay = userEmailTxt.getAttribute("value").equals(enteredEmail);
			ExtentLogger.logInfo("The Element ["+userEmailTxt.getAttribute("name")+"] field displays - "+userEmailTxt.getAttribute("value"));
		} catch (Exception e){
			ExtentLogger.logError("Failed While Displaying the Content in the ["+userEmailTxt.getAttribute("name")+"] Field");
		}
		return bDisplay;
	}
		
	//Need to Fix the Below Confirmation Information.
	public Boolean validateSignUpConfirmation(String emailAddress){
		try {
			assertSafe = new SoftAssert();
			assertSafe.assertEquals(successLabel.getText(), MessageConstants.SIGNUP_CONFIRMATION);
			assertSafe.assertTrue(loginLink.isDisplayed());
			assertSafe.assertTrue(parterSignupLink.isDisplayed());
			assertSafe.assertAll();
			ExtentLogger.logInfo("Verified the Availability Of " + MessageConstants.SIGNUP_CONFIRMATION +", "+ 
			loginLink.getText() + ", " + parterSignupLink.getText());
			return true;
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While Validating the Content Of the Registration Confirmation at "+getPageTitle());
			return false;
		}	
	}
	
	public Boolean validateFooterEmbeddedLinks(){
		try {
			assertSafe = new SoftAssert();
			assertSafe.assertEquals(copyRightsLink.getAttribute("href"), URLConstants.POWEREDBY_APPDIRECT);
			assertSafe.assertEquals(helpCenterLink.getAttribute("href"), URLConstants.HELP_CENTER);
			assertSafe.assertEquals(privPolicyLink.getAttribute("href"), URLConstants.PRIV_POLICY);
			assertSafe.assertEquals(contactLink.getAttribute("href"), URLConstants.CONTACT);
			assertSafe.assertEquals(termsConditionsLink.getAttribute("href"), URLConstants.TERMS_CONDITIONS);
			assertSafe.assertAll();
			ExtentLogger.logInfo("Verified the Embedded Links for = ["+copyRightsLink.getText()+", "+helpCenterLink.getText()+", "+
			privPolicyLink.getText()+", "+contactLink.getText()+", "+termsConditionsLink.getText()+"]");
			return true;
		} catch (Exception e) {
			ExtentLogger.logError("Failed While Validating the Footer Embedded Links at "+getPageTitle());
			return false;
		}
	}
}
