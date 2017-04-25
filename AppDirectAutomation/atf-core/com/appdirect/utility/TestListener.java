package com.appdirect.utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class TestListener implements IInvokedMethodListener, IRetryAnalyzer  {

	public static String testDescription;
	int minRetry = 0;
	int maxRetry = 1;

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
	    // TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	public void beforeInvocation(IInvokedMethod m, ITestResult tr) {
	    // TODO Auto-generated method stub
	    //This give the Annotation Test object
	    org.testng.annotations.Test t=m.getTestMethod().getMethod().getAnnotation(org.testng.annotations.Test.class);
	    TestListener.testDescription = t.description();
	    System.out.println(TestListener.testDescription);

	}

	@Override
	public boolean retry(ITestResult result) {
		if (minRetry<=maxRetry){
			minRetry++;
			System.out.println("Working Raju Bhai");
			return true;
		}else{
			return false;			
		}
	}
}
