package com.appdirect.utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener, IInvokedMethodListener  {

	public static String testDescription;
	public static String testParameters = "";

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		Log.info("Test Started : "+result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Log.info("Test Success : "+result.getMethod().getMethodName());
		testParameters = result.getMethod().getMethodName();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Log.error("Test Failed : "+result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Log.warn("Test Skipped : "+result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		Log.info("Test Finished : "+ context.getName());
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub		
	}
}
