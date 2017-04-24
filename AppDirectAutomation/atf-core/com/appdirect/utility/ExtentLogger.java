package com.appdirect.utility;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public final class ExtentLogger {
	
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extReports;
	public static ExtentTest extTest;
	
	private ExtentLogger() {
	}
	
	public static void writeLog(String developerLogs) throws Exception{
		final Logger logger = Logger.getLogger("com.appdirect.utility");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd_HH:mm:ss");
		FileHandler fHandler = null;
		try {
			fHandler = new FileHandler("C:/temp/appdirect/automation/Log_" + dateFormat.format(Calendar.getInstance().getTime())+ ".log");
			logger.addHandler(fHandler);
			fHandler.setFormatter(new SimpleFormatter());
			logger.setUseParentHandlers(false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info(developerLogs);
	}	 

	public static void startReport(String suiteName)
     {
         htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+suiteName+".html");
         extReports = new ExtentReports();
         extReports.attachReporter(htmlReporter);
         htmlReporter.config().setReportName("AppDirect Automation Report");
         htmlReporter.config().setDocumentTitle("AppDirect Automation Report"); 
         htmlReporter.config().setTheme(Theme.STANDARD);
     }	
	 
	public static void startTest(String testCaseName, @Optional String testDescription){	
		 extTest = extReports.createTest(testCaseName, testDescription);
		 System.out.println("<INFO> : Test Exceution Started & InProgress");
	 }
	
 
	public static void getResults(ITestResult result) throws Exception{
		
		String testDescription = result.getMethod().getDescription();
		String testDefnition = testDescription.substring(testDescription.lastIndexOf("-")+1).substring(5);
		 
		if (result.getStatus()==ITestResult.SUCCESS){
			 extTest.log(Status.PASS, "Test Validated - "+testDefnition);
		 }
		 else if (result.getStatus()==ITestResult.FAILURE) {
			 String ScreenShotPath = ScreenShotUtil.getScreenShotPath(driver,result.getMethod().toString());
			 System.out.println("ScreenShot Path: "+ScreenShotPath);
			 //extTest.addScreenCaptureFromPath(ScreenShotPath);
			 extTest.log(Status.FAIL, "Application Failure On - "+ testDefnition);
			 
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
