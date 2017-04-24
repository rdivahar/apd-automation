package com.appdirect.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.appdirect.common.PageBase;
import com.appdirect.utility.ExtentLogger;

public class HomePage extends PageBase {

	WebDriver driver;
	
	public HomePage(WebDriver _driver) {
		super(_driver);
	}

	@FindBy(xpath=".//*[@class='login']/a")
	private WebElement login;
	
	@FindBy(xpath="html/body/footer/div/div[2]/div[1]/ul[3]/li[4]/a")
	private WebElement loginFooter;
	
	public void clickLogin(){
		try {
			login.click();
			ExtentLogger.logInfo("Clicked Login at AppDirect Home");
		} catch (Exception ex) {
			ExtentLogger.logError("Failed While Clicking the Login from the AppDirect Home");
		}
	}
	
	public void clickLogin(boolean isFooterLink){
		
		if (isFooterLink == true){
			try {
				loginFooter.click();
				ExtentLogger.logInfo("Clicked Login from the Footer at AppDirect Home");
			} catch (Exception ex) {
				ExtentLogger.logError("Failed While Clicking Login from the Footer Area at AppDirect Home");
			}
		}
	}
}
