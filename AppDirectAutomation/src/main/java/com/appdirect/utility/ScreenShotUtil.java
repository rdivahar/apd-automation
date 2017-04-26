package com.appdirect.utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
* Utility Class for Capturing the Screenshots.
* @author sdivahar
*/
public final class ScreenShotUtil{

	public static String imageFilePath = System.getProperty("user.dir")+"/test-reports/snapshots/";
	
	private ScreenShotUtil() {
	}
	
	public static void takeScreenShot(WebDriver driver, String imageName) throws Exception{
		
		String imagePath = imageFilePath+imageName+".png";
		
		try {
			File file = new File(imageFilePath);
			if (!file.exists()){
				file.mkdirs();
			}
			TakesScreenshot captureScreen = ((TakesScreenshot)driver);
			File imageSource = captureScreen.getScreenshotAs(OutputType.FILE);
			File imageDestination = new File(imagePath);
			FileUtils.copyFile(imageSource, imageDestination);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		
     public static String getScreenShotPath(WebDriver driver, String imageName) throws Exception
     {
    	 String imagePath = imageFilePath+imageName+".png";
    	 takeScreenShot(driver,imageName);
    	 return imagePath;   
     }
}

