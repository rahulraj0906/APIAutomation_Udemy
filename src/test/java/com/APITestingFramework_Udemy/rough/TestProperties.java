package com.APITestingFramework_Udemy.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	
	
	public static void main(String[] args) throws IOException {
		
		
		 Properties prop = new Properties();
		 FileInputStream fis =new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		prop.load(fis);
	}
	
	

}
