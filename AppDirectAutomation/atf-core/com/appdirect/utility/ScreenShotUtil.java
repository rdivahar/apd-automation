package com.appdirect.utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public final class ScreenShotUtil extends Assert{

	public static String imageFilePath = System.getProperty("user.dir")+"/test-output/Reports/";
	
	private ScreenShotUtil() {
	}
	
	public static void takeScreenShot(WebDriver driver, String imageName) throws Exception{
		String imagePath = imageFilePath+imageName+".png";
		TakesScreenshot captureScreen = ((TakesScreenshot)driver);
		File imageSource = captureScreen.getScreenshotAs(OutputType.FILE);
		File imageDestination = new File(imagePath);
		FileUtils.copyFile(imageSource, imageDestination);
	}
		
     public static String getScreenShotPath(WebDriver driver, String imageName) throws Exception
     {
    	 String imagePath = imageFilePath+imageName+".png";
    	 takeScreenShot(driver,imageName);
    	 return imagePath;   
     }
}

