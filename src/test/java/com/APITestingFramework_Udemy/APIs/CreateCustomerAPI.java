package com.APITestingFramework_Udemy.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.APITestingFramework_Udemy.base.BaseTest;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseTest{
	
	public static Response sendPostToCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data) {
		
		Response resp = given().auth().basic(prop.getProperty("validsecretkey"), "").
		formParam("name", data.get("name")).formParam("email",data.get("email")).formParam("description", data.get("description"))
		.post(prop.getProperty("CreateCustomerEndPoint"));
		
		return resp;
		
	}
	
     public static Response sendPostToCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String> data) {
		
		Response resp = given().auth().basic(prop.getProperty("invalidsecretkey"), "").
		formParam("name", data.get("name")).formParam("email",data.get("email")).formParam("description", data.get("description"))
		.post(prop.getProperty("CreateCustomerEndPoint"));
		
		return resp;
		
	}
	
	
	         

}
