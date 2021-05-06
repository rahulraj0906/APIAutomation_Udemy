package com.APITestingFramework.ExcelReader;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.APITestingFramework_Udemy.base.BaseTest;

public class DataUtil extends BaseTest{

	@DataProvider(name="data", parallel=true)
	public Object[][] getData(Method m) {

		ExcelReader excel = new ExcelReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\testdata.xlsx");

		int rows = excel.getRowCount(Constants.TESTDATA_SHEET);

		String testName = m.getName();

		// Find test case starting row number

		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excel.getCellData(Constants.TESTDATA_SHEET, 0, testCaseRowNum);

			if (testCaseName.equalsIgnoreCase(testName))
				break;
		}

		System.out.println("Row number for test case name is : " + testCaseRowNum);

		// Checking total test data rows in test case:-

		int dataStartRowNum = testCaseRowNum + 2;

		int testRows = 0;

		while (!excel.getCellData(Constants.TESTDATA_SHEET, 0, dataStartRowNum + testRows).equals("")) {

			testRows++;
		}

		System.out.println("Total rows of test data for the mentioned test case are: " + testRows);

		// Checking total columns in test case:-

		int colStartColNum = testCaseRowNum + 1;

		int testCols = 0;

		while (!excel.getCellData(Constants.TESTDATA_SHEET, testCols, colStartColNum).equals("")) {

			testCols++;
		}

		System.out.println("Total columns in test case are: " + testCols);

		// Printing data

		Object[][] data = new Object[testRows][1];
		
		int i=0;

		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {
			
			Hashtable<String,String> table = new Hashtable<String,String>();

			for (int cNum = 0; cNum < testCols; cNum++) {

				String testdata= excel.getCellData(Constants.TESTDATA_SHEET, cNum, rNum);
				String colName= excel.getCellData(Constants.TESTDATA_SHEET, cNum, colStartColNum);
				
				table.put(colName, testdata);

			}
			
			data[i][0]=table;
			i++;
		}

		return data;

		
	}
}
