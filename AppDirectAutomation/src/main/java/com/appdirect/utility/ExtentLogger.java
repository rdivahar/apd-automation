package com.appdirect.utility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.kstruct.gethostname4j.Hostname;

/**
* Logger Class for Generating the HTML Test Reports.
* @author sdivahar
*/
public final class ExtentLogger {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extReports;
	public static ExtentTest extTest;
	
	private ExtentLogger() {
	} 

	public static void startReport(String suiteName)
     {
         htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-reports/"+suiteName.toLowerCase()+".html");
         htmlReporter.config().setReportName("Test Report - AppDirectSignUp 1.0");
         htmlReporter.config().setDocumentTitle("AppDirect Automation Report"); 
         htmlReporter.config().setEncoding("UTF-8");
         extReports = new ExtentReports();
         extReports.setSystemInfo("Environment", "Pre-Production");
         extReports.setSystemInfo("Build", "AppDirect Release");
         extReports.setSystemInfo("Version", "1.0.0");
         extReports.setSystemInfo("TestSuite", "Basic Acceptance");
         extReports.setSystemInfo("Browser", "Chrome");
         extReports.setSystemInfo("Owner", "Test Automation Team");
         extReports.setSystemInfo("OS Name", System.getProperty("os.name"));
         extReports.setSystemInfo("HostName", Hostname.getHostname());
         extReports.setAnalysisStrategy(AnalysisStrategy.TEST);
         extReports.attachReporter(htmlReporter);
     }	
	 
	public static void startTest(String testCaseName, @Optional String testDescription){	
		 extTest = extReports.createTest(testCaseName, testDescription);
		 System.out.println("<INFO> : Test Exceution Started & InProgress");
	 }
	
	public static void getResults(ITestResult result, WebDriver driver) throws Exception{
		
		String screenCaptureName = result.getMethod().getMethodName().substring(4).toLowerCase()+"-"+DataGenerator.generateNumber(1);
		String testDescription = result.getMethod().getDescription();
		String testDefnition = testDescription.substring(testDescription.lastIndexOf("-")+1).substring(5);
		 
		if (result.getStatus()==ITestResult.SUCCESS){
			 extTest.log(Status.PASS, "Test Validated - "+testDefnition);
		 }
		 else if (result.getStatus()==ITestResult.FAILURE) {
			 String ScreenShotPath = ScreenShotUtil.getScreenShotPath(driver,screenCaptureName);
			 extTest.addScreenCaptureFromPath(ScreenShotPath);
			 extTest.log(Status.ERROR, result.getThrowable().getLocalizedMessage());
			 extTest.log(Status.FAIL, "Failed Test - "+ testDefnition+" <p> Refer the SNAPSHOT Below: </p>");	 
		}else {
			extTest.log(Status.SKIP, "Skipped the Test - " + testDefnition);
		} 
	 }
	 
	public static void flushReport(){
		 extReports.flush();
	 }
	
	public static void logPass(String successMessage){
		extTest.log(Status.PASS, successMessage);
	}
	
	public static void logFail(String failureMessage){
		extTest.log(Status.FAIL, failureMessage);
	}
	
	public static void logInfo(String infoMessage){
		extTest.log(Status.INFO, infoMessage);
	}
	
	public static void logError(String errorMessage){
		extTest.log(Status.ERROR, errorMessage);
	}
	
	public static void logWarning(String warnMessage){
		extTest.log(Status.WARNING, warnMessage);
	}
	
	public static void logFatal(String fatalMessage){
		extTest.log(Status.FATAL, fatalMessage);
	}
	
	public static void logSkip(String skipMessage){
		extTest.log(Status.SKIP, skipMessage);
	}
	
	public static void logDebug(String debugMessage){
		extTest.log(Status.DEBUG, debugMessage);
	}
	
	
	
	

}
