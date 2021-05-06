package com.APITestingFramework_Udemy.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.APITestingFramework_Udemy.base.BaseTest;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseTest{
	
	public static Response DeleteCustomerAPIWithValidId(Hashtable<String,String> data) {
		
		Response resp = given().auth().basic(prop.getProperty("validsecretkey"), "").
		delete(prop.getProperty("CreateCustomerEndPoint")+"/"+ data.get("id"));
		
		return resp;
		
	}
	
}
