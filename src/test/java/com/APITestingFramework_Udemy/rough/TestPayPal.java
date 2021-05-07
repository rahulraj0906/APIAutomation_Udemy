package com.APITestingFramework_Udemy.rough;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIAutomation_Udemy.pojo.Orders;
import com.APIAutomation_Udemy.pojo.PurchaseUnits;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class TestPayPal {
	
	static String access_token;
	static String client_id="AXiDCJhrdjclGk0TMQgYdQ_QIxmH2oc-9FHYgk1LXJ8z96N6arE6pr9tYFfl4-1S_4ZINSLU_yDtOOcu";
	static String secret_key="EHgU0Ql5iwNfz3KElUjhYySxmFGtREMcvB2galmdcvB7GTeIBasXMEE8-4-36aHYik3a7RTm0YCwyw2-";
	static String order_id;
	
	
	@Test(priority=1)
	public void getAuthKey() {
		
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		
		Response resp=given().formParam("grant_type", "client_credentials").
		auth().preemptive().basic(client_id, secret_key).
		post("/v1/oauth2/token");
		
		resp.prettyPrint();
		
		access_token=resp.jsonPath().get("access_token").toString();
	}
	
	@Test(priority=2,dependsOnMethods="getAuthKey")
	public void createOrder() {
		
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		ArrayList<PurchaseUnits> al = new ArrayList<PurchaseUnits>();
		al.add(new PurchaseUnits("USD","500.00"));
		
		Orders order = new Orders("CAPTURE",al);
		/*
		 * String jsonBody = "{\r\n" + "  \"intent\": \"CAPTURE\",\r\n" +
		 * "  \"purchase_units\": [\r\n" + "    {\r\n" + "      \"amount\": {\r\n" +
		 * "        \"currency_code\": \"USD\",\r\n" +
		 * "        \"value\": \"100.00\"\r\n" + "      }\r\n" + "    }\r\n" + "  ]\r\n"
		 * + "}";
		 */
		Response resp=given().auth().oauth2(access_token).contentType(ContentType.JSON)
				.body(order).post("v2/checkout/orders");
		
		resp.prettyPrint();
		 order_id=resp.jsonPath().get("id").toString();
		Assert.assertEquals(resp.jsonPath().get("status"), "CREATED");
	}
	
	@Test(priority=3,dependsOnMethods={"getAuthKey","createOrder"})
	public void getOrder(){
		
		System.out.println("-----------Getting the Order----------------");
		
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		
		Response resp=given().auth().oauth2(access_token).contentType(ContentType.JSON)
				.get("v2/checkout/orders/"+order_id);
		
		resp.prettyPrint();
		Assert.assertEquals(resp.getStatusCode(), 200);
	}
}


