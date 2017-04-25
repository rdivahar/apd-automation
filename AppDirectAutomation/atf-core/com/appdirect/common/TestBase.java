package com.appdirect.common;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.appdirect.pagefactory.HomePage;
import com.appdirect.utility.ExtentLogger;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {

	public WebDriver driver;
	public String testName;
	public String testDescription = null;
	private Properties testProp;
	private FileInputStream inputSettings;
	public HomePage homePage;
	
	public TestBase(){
		this.loadTestSettings();	
	}
	
	@BeforeSuite
	@Parameters("browser")
	public void loadTestReport(@Optional("Chrome") String browserName){
		ExtentLogger.startReport("AppDirectReport-"+browserName);
		System.out.println("Before @BeforeSuite");
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void loadAppDirectAndStartTest(@Optional("Chrome") String browserName, Method method) throws Exception{
		testName = method.getName();
		Test test = method.getAnnotation(Test.class);
		testDescription = test.description();
		System.out.println("=========================================================");
		//System.out.println(test.dataProvider());
		System.out.println(testName+" : "+testDescription);
		driver = loadDriver(browserName);
		ExtentLogger.startTest(testName, testDescription);		
	}
		
	@AfterMethod
	public void closeAppDirectAndLogResults(ITestResult result) throws Exception{
		System.out.println(result.getMethod().getDescription());
		ExtentLogger.getResults(result, driver);
		closeBrowser();
	}
	
	@AfterSuite
	public void flushResults() throws AddressException, MessagingException{
		ExtentLogger.flushReport();
		System.out.println("After @AfterSuite");
		//GoogleMail mailSender = new GoogleMail();
		//mailSender.sendEmail("sahayadivahar", "latitude5510dell", "maildivahar@gmail.com", "FirstEmail", "TestReport");
	}
	
	public void loadTestSettings(){
		try {
			setTestProp(new Properties());
			inputSettings = new FileInputStream("configuration/settings.properties");
			getTestProp().load(inputSettings);	
		} catch (Exception Ex) {
			Assert.fail("Failed to Load the Settings ");
			Ex.printStackTrace();
		}	
	}
	
	public void loadPageAndMaximize(String targetURL){
		//driver.manage().window().maximize();
		driver.navigate().to(targetURL);
	}
	
    public WebDriver loadDriver(String browserType) {

    	switch (browserType) {
            case "FireFox":
            	System.setProperty("webdriver.gecko.driver", getTestProp().getProperty("FireFoxDriverPath"));
            	DesiredCapabilities desiredFFCap = DesiredCapabilities.firefox();
            	desiredFFCap.setCapability("marionette", true);
            	driver =  new FirefoxDriver(desiredFFCap);
            	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            	driver.manage().window().maximize(); 
            	break;
            case "InternetExplorer":
            	DesiredCapabilities desiredIECap = DesiredCapabilities.internetExplorer();
            	//desiredIECap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            	desiredIECap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                System.setProperty("webdriver.ie.driver", getTestProp().getProperty("IEDriverPath"));
                driver =  new InternetExplorerDriver(desiredIECap);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            case "Chrome":
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--start-maximized");
            	//options.addArguments("--disable-web-security");
                //options.addArguments("--no-proxy-server");
            	Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
            	ChromeDriverManager.getInstance().setup();
                //System.setProperty("webdriver.chrome.driver", getTestProp().getProperty("ChromeDriverPath"));
                driver =  new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                System.out.println("<INFO> : Loaded the Chrome Driver");
                break;
            case "Safari":
            	//TO-DO
            	break;
            case "Opera":
            	//TO-DO
            	break;
            case "Edge":
            	//TO-DO
            	break;
            default:
            	System.out.println(driver.toString()+ "Not Supported");
            	break;
        }
    	
    	System.out.println("<INFO> : Loaded the Browser as "+ browserType);
    	loadPageAndMaximize(getTestProp().getProperty("APPDIRECT_PROD_URL"));
        System.out.println("<INFO> : Browser is Launched with the URL " + getTestProp().getProperty("APPDIRECT_PROD_URL"));
		
        return driver;
    }
	
	public WebDriver getDriver(){
		return this.driver;
	}
	
	public void setDriver(WebDriver webDriver){
		this.driver = webDriver;
	}
	
	public void testSetUp(String browserName){
		try {
			this.loadTestSettings();
			this.loadDriver(browserName);
		} catch (Exception Ex) {
			Assert.fail("Failed to Load the Browser "+ browserName);
			Ex.printStackTrace();
		}
	}
	
	public void closeBrowser(){
		try {
			this.driver.quit();
			System.out.println("<INFO> : Closing the Browser");
		} catch (Exception Ex) {
			Ex.printStackTrace();
		}
	}

	public Properties getTestProp() {
		return testProp;
	}

	public void gotoHomePage(){
		driver.navigate().to(getTestProp().getProperty("APPDIRECT_PROD_URL"));
	}
	
	public void setTestProp(Properties testProp) {
		this.testProp = testProp;
	}
	
	
	public String getCurrentPageTitle(){
		return driver.getTitle();
	}
	
	public String getCurrentPageURL(){
		return driver.getCurrentUrl();
	}
}
