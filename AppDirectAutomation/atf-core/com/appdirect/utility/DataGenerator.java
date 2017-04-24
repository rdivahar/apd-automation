package com.appdirect.utility;

import java.util.Random;

public class DataGenerator{

	public DataGenerator(){
	}
	
	public static String possibleChars = "abcdefghijklmnopqrstuvwxyz";
    public static char[] possibleCharsArray = possibleChars.toCharArray();
    public static int possibleCharsAvailable = possibleChars.length();
    public static Random rp = new Random();

    public static String generateNumber(int len){
    	int i;
    	if (len == 0){
    		return "0";
    		}
    	StringBuilder numString = new StringBuilder();
    	for (i = 0; i < len; i++){
    		numString.append((int)(getRandomNumber()));
    		}
    	return numString.toString();
       }

    public static int getRandomNumber(){
    	return rp.nextInt();
    }
       
    public static String generateString(int num){
    	char[] result = new char[num];
        while (num-- > 0)
        {
            result[num] = possibleCharsArray[rp.nextInt(possibleCharsAvailable)];
        }
        return new String(result);
    }
          
    public static String generateEmail(){
    	StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append(generateString(6)).append("-");
        emailBuilder.append(generateString(3)).append("@gmail.com");
        return emailBuilder.toString();
    }
    
    public static String generateInternalDomainEmail(){
    	StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append(generateString(6)).append("-");
        emailBuilder.append(generateString(3)).append("@appdirect.com");
        return emailBuilder.toString();
    }
    
    public static String generateInvalidEmail(){
    	StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append(generateString(6)).append("-");
        emailBuilder.append(generateString(3)).append("@gmail");
        return emailBuilder.toString();
    }
    
}

