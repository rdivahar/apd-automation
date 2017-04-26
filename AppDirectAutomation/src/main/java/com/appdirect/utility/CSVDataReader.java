package com.appdirect.utility;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Parameters;

import com.opencsv.CSVReader;

/**
* Class to Read the Test Data from the CSV File. 
* [Setting as a Placeholder for the future use]
* @author sdivahar
*/

public final class CSVDataReader {
	public static List<String[]> csvData;
	public HashMap<String,String> tesData=new HashMap<String,String>();
	
	@Parameters({"testDataFile"})
	public void getCSVData(String csvDataFile) throws Exception{
		@SuppressWarnings("resource")
		CSVReader reader=new CSVReader(new BufferedReader(new FileReader(csvDataFile)));
		 csvData=reader.readAll();
	}

	public HashMap<String, String> getData(int testCaseCount){
		String[] keys=csvData.get(0);
		String[] values=csvData.get(testCaseCount);
		for(int i=0;i<keys.length;i++){
			tesData.put(keys[i], values[i]);
		}
		return tesData;
	}
}

