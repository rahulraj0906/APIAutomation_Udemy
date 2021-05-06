package com.APITestingFramework.testcases;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.APITestingFramework.ExcelReader.DataUtil;
import com.APITestingFramework.ExcelReader.TestUtil;
import com.APITestingFramework_Udemy.APIs.CreateCustomerAPI;
import com.APITestingFramework_Udemy.APIs.DeleteCustomerAPI;
import com.APITestingFramework_Udemy.base.BaseTest;
import com.APITestingFramework_Udemy.listeners.ExtentListeners;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest{

	
	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	public void ValidateDeleteCustomerAPIWithValidId(Hashtable<String,String> data) {
		
		Response resp = DeleteCustomerAPI.DeleteCustomerAPIWithValidId(data);
		
		resp.prettyPrint();
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		
		/*
		 * String actual_id=resp.jsonPath().get("id"); 
		 * Assert.assertEquals(actual_id,data.get("id"),"ID not matching");
		 */
		
		//To check if key is present in JSON body or not
		/*
		 * JSONObject jsonObject = new JSONObject(resp.asString());
		 * jsonObject.has("id"); Assert.assertTrue(jsonObject.has("id"),
		 * "ID key is not present");
		 */
		
		Assert.assertTrue(TestUtil.jsonHaskey(resp.asString(), "id"),"ID key is not present");
		
		
		//To check if value is present in JSON body or not
		
		/*
		 * String actual_id= jsonObject.get("id").toString();
		 * Assert.assertEquals(actual_id, data.get("id"),"ID is not matching");
		 */
		
		String actual_id=TestUtil.getJsonkeyValue(resp.asString(), "id");
		Assert.assertEquals(actual_id, data.get("id"), "ID not matching");
		
	}
	
	
}
