package com.appdirect.utility;

import org.apache.log4j.Logger;

public class Log {

	protected static Logger logger = Logger.getLogger(Log.class.getName());
	
	public static void info(String info){
		logger.info(info);
	}
	
	public static void error(String error){
		logger.error(error);
	}
	
	public static void error(String error, Throwable t){
		logger.error(error,t);
	}
	
	public static void fatal(String message, Throwable t){
		logger.fatal(message, t);
	}
	
	public static void warn(String warning){
		logger.warn(warning);
	}
	
	public static void warn(String message, Throwable t){
		logger.warn(message, t);
	}
}
