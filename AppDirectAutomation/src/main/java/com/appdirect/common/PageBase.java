package com.appdirect.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.appdirect.utility.ExtentLogger;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;


/**
 * Base Class for all the Page Factory Classes Contains Common Methods.
 * @author sdivahar
 */

public class PageBase {

	public WebDriver driver;
	
	public final Logger logger = Logger.getLogger("com.appdirect.utility");
	
	public PageBase(WebDriver _driver) {
	        driver = _driver;
	        this.ensurePageReadyState();
	        PageFactory.initElements(driver, this);
	}
	 
	public void ensurePageReadyState(){
		 ExpectedCondition<Boolean> pageLoadCondition = new
	                ExpectedCondition<Boolean>() {
	                    public Boolean apply(WebDriver driver) {
	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState;").equals("complete");
	                    }
	                };
	        WebDriverWait wait = new WebDriverWait(driver, 20);
	        wait.until(pageLoadCondition);
	}
	 
	public void waitForElementVisible(WebElement Element, int maxWaitTime){
		WebDriverWait wait = new WebDriverWait(driver,maxWaitTime);
		wait.until(ExpectedConditions.visibilityOf(Element));
	}
	 
	public void WaitForElementClickable(WebElement Element, int maxWaitTime){
		WebDriverWait wait = new WebDriverWait(driver,maxWaitTime);
		wait.until(ExpectedConditions.elementToBeClickable(Element));
	}	
		
	public void refreshPage(){
		driver.navigate().refresh();
		this.ensurePageReadyState();
		ExtentLogger.logInfo("Refreshed the WebPage & Loaded the Elements : "+getPageTitle());
	}
	
	public void click(WebElement element){
		waitForElementVisible(element, 100);
		if (element.isDisplayed()){
			element.click();
		}else{
			ExtentLogger.logFatal("The element "+element.getText()+" is Not Visible");
		}
	}
	
	public void clearAndSendKeys(WebElement element, String textToEnter){
		waitForElementVisible(element, 100);
		if (element.isDisplayed()){
			element.clear();
			element.sendKeys(textToEnter);
		}else{
			ExtentLogger.logFatal("The element "+element.getText()+" is Not Visible");
		}
	}
	
	public Boolean hasActiveFocus(WebElement webElement){
		return webElement.equals(driver.switchTo().activeElement());
	}
	
	public void gotoPreviousPage(){
		driver.navigate().back();;
		this.ensurePageReadyState();
		ExtentLogger.logInfo("Navigated back to the Previous Page : " +getPageTitle());
	}
	
	public String getValidationMessage(WebElement webElement){
		return webElement.getAttribute("validationMessage");
	}
	
	public String getPlaceHolderText(WebElement webElement){
		return webElement.getAttribute("placeholder");
	}
	
	public String getPageTitle(){
		return driver.getTitle();
	}
	
	public String getPageURL(){
		return driver.getCurrentUrl();
	}
}