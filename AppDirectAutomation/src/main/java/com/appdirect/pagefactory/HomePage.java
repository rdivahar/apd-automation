package com.appdirect.pagefactory;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.appdirect.common.PageBase;
import com.appdirect.constants.TitleConstants;
import com.appdirect.utility.ExtentLogger;

/**
* Extends the PageBase and Contains Page Objects and Methods of Home Page.
* @author sdivahar
*/
public class HomePage extends PageBase {
	
	public HomePage(WebDriver _driver) {
		super(_driver);
	}

	@FindBy(id="login")
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
	
	public void loginAndSwitchToMarketPlace(){
		try {
			this.clickLogin();
			ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
		    driver.switchTo().window(tabs_windows.get(1));
		    ExtentLogger.logInfo("Switched to the new Tab OnClick Login Button = "+getPageTitle());
		} catch (Exception e) {
			ExtentLogger.logError("Failed While Loading the New MarketPlace Tab from the AppDirect Home");
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
	
	public boolean verifyMarketplaceLoadedAsNewTab(){
		Boolean bLoaded = false;		
		ArrayList<String> tabs_windows = new ArrayList<String>(driver.getWindowHandles());
		try {
			if (tabs_windows.size() > 1){
				driver.switchTo().window(tabs_windows.get(1));
				bLoaded = driver.getTitle().equalsIgnoreCase(TitleConstants.LOGIN_PAGE);
			}else{
				bLoaded = false;
				throw new Exception("Failed to Load the New Tab, the number of Open Tabs is less than Two");
			}
			return bLoaded;
		} catch (Exception e) {
			ExtentLogger.logError("Failed While Clicking Login from the Footer Area at AppDirect Home");
			return bLoaded = false;
		}	
	}
}
