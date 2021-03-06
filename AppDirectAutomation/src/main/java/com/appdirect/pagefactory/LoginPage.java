package com.appdirect.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.appdirect.common.PageBase;
import com.appdirect.utility.ExtentLogger;


/**
* Extends the PageBase and Contains Page Objects and Methods of Login Page.
* @author sdivahar
*/
public class LoginPage extends PageBase {

	
	public LoginPage(WebDriver _driver) {
		super(_driver);
	}

	@FindBy(id="username")	
	public WebElement userNameTxt;

	@FindBy(id="password")
	protected WebElement passwordTxt;

	@FindBy(name="signin")
	private WebElement loginBtn;
	
	@FindBy(linkText="Sign Up")
	private WebElement signUpBtn;
	
	@FindBy(css=".adb-footnote>p>a")
	private WebElement signUpLink;
	
	@FindBy(css=".adb-container_header--title.adb-container_header--item")
	public WebElement loginHeader;
	
	public void login(String emailAddress, String userPassword ){
		try {
			ExtentLogger.logInfo("Logging In With Username : "+emailAddress+" and Password : "+userPassword);
			userNameTxt.clear();
			userNameTxt.sendKeys(emailAddress);
			passwordTxt.clear();
			passwordTxt.sendKeys(userPassword);
			loginBtn.click();
			ExtentLogger.logInfo("Navigated to "+ getPageTitle());
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While Logging with Username = ["+emailAddress+"] and Password = ["+userPassword+"]");
		}
	}
	
	public void clickSignUp(){
		try {
			ExtentLogger.logInfo("Clicking SignUp at AppDirect Marketplace LoginPage");
			signUpBtn.click();
			ExtentLogger.logInfo("Navigated to the Page : "+ getPageTitle());
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While Clicking SignUp at AppDirect Marketplace LoginPage");
		}
	}
	
	public void clickSignUp(boolean isFootNoteLink){
		if (isFootNoteLink){
			try {
				ExtentLogger.logInfo("Clicking SignUp FooterNote Link at AppDirect Marketplace LoginPage");
				signUpLink.click();
				ExtentLogger.logInfo("Navigated to the Page: "+ getPageTitle());
			} catch (Exception ex) {
				ExtentLogger.logError("Failed While Clicking SignUp FooterNote Link at AppDirect Marketplace LoginPage. ErrorLog : "+ ex.toString());
			}
		}else{
			this.clickSignUp();
		}
	}	
}
