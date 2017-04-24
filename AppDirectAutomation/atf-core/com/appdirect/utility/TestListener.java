package com.appdirect.utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestListener implements IInvokedMethodListener {

	public static String testDescription;


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
}
