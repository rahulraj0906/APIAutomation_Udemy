package com.APITestingFramework_Udemy.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.APITestingFramework.ExcelReader.ExcelReader;

import io.restassured.RestAssured;

public class BaseTest {
	
	public static Properties prop= new Properties();
	public FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\Excel\\testdata.xlsx");
	
	@BeforeSuite
	public void setUp() {
		
		
		try {
			fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		RestAssured.baseURI=prop.getProperty("baseURI");
		RestAssured.basePath=prop.getProperty("basePath");
	}
	
	
	@AfterSuite
	public void tearDown() {
		
		
	}

}
