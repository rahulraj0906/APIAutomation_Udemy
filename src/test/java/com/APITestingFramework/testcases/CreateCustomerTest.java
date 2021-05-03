package com.APITestingFramework.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APITestingFramework_Udemy.base.BaseTest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CreateCustomerTest extends BaseTest{

	
	@Test
	public void ValidateCreateCustomerwithValidSecretKey() {
		
		Response resp = given().auth().basic("sk_test_51IkZ2BSIRLtbaN8VzcmgjCfWSpNL6mTaO3U973HbuvBTbDPVlmVXt8NDr2KmgrKU2fyIBYsS51bXdCm3HKbeoTVF00ZXAHjO2X", "").
		formParam("email","rahul.raj09@yahoo.co.in").formParam("description", "This is first customer created for testing")
		.post("/customers");
		
		resp.prettyPrint();
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 200);
		
	}
	
	@Test
	public void ValidateCreateCustomerwithInValidSecretKey() {
		
		Response resp = given().auth().basic("sk_test_51Ik565Z2BSIRLtbaN8VzcmgjCfWSpNL6mTaO3U973HbuvBTbDPVlmVXt8NDr2KmgrKU2fyIBYsS51bXdCm3HKbeoTVF00ZXAHjO2X", "").
		formParam("email","rahul.raj09@yahoo.co.in").formParam("description", "This is second customer created for testing")
		.post("/customers");
		
		resp.prettyPrint();
		System.out.println(resp.statusCode());
		Assert.assertEquals(resp.statusCode(), 401);
		
	}
}
