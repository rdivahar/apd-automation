package com.appdirect.utility;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public final class AssertUtil extends Assert{

	public static SoftAssert assertSoft;
	
	private AssertUtil() {
		if (assertSoft != null){
			assertSoft = new SoftAssert();			
		}
	}
	
	public static void verifyEquals(String actual, String expected){
		assertSoft.assertEquals(actual, expected);
	}
	
	public static void verifyTrue(Boolean bexpected){
		assertSoft.assertTrue(bexpected);
	}
	
	public static void verifyNotTrue(Boolean bexpected){
		assertSoft.assertFalse(bexpected);
	}
	
	public static void verifyNotNull(Object object){
		assertSoft.assertNotNull(object);
	}
	
	public static void verifyNull(Object object){
		assertSoft.assertNull(object);
	}
	
	public static void verifyAll(){
		assertSoft.assertAll();
	}
}