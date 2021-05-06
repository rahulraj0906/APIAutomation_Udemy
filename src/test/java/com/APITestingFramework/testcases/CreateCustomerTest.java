package com.APITestingFramework.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.APITestingFramework.ExcelReader.DataUtil;
import com.APITestingFramework_Udemy.APIs.CreateCustomerAPI;
import com.APITestingFramework_Udemy.base.BaseTest;
import com.APITestingFramework_Udemy.listeners.ExtentListeners;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest{

	
	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	public void ValidateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data) {
		
		Response resp = CreateCustomerAPI.sendPostToCreateCustomerAPIWithValidSecretKey(data);
		//To print the customised logs in report
		  ExtentListeners.testReport.get().info(data.toString());
		
		resp.prettyPrint();
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		
	}
	
	
	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	  public void ValidateCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String> data) {
	  
	  Response resp = CreateCustomerAPI.sendPostToCreateCustomerAPIWithInValidSecretKey(data);
	  //To print the customised logs in report
	  ExtentListeners.testReport.get().info(data.toString());
	  
	  resp.prettyPrint(); 
	  System.out.println(resp.statusCode());
	  Assert.assertEquals(resp.statusCode(), 401);
	  
	  }
	 
	 
	
	
}
