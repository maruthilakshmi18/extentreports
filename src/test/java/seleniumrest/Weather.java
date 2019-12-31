package seleniumrest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.testng.annotations.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Weather {
	
	

	public ValidatableResponse response;

	//Response response1;
	
	
	@Given("^I execute get call for weather report$")
	@Test
	public void I_execute_get_call_for_weather_report() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		response = RestAssured.given().header("Content-Type", "application/json").param("location", "KPHBs")
				.param("radius", "500").when().get("/Hyderabad").then().assertThat().statusCode(200);

		System.out.println("expected response:" + response.extract().response().body().print());

	}

	@Then("^I should validate the response status code and body message$")
	public void I_should_validate_the_response_status_code_and_body_message() {

		HashMap<String, String> results = response.extract().body().jsonPath().get();
		

		for (String key : results.keySet()) {
			System.out.println(key);
			System.out.println(results.get(key));
		}
		
		for (Entry key : results.entrySet()) {
			System.out.println(key.getKey()+" "+key.getValue());
		}


		response.contentType("application/json").extract().asString().compareToIgnoreCase("83 Percent");
		response.contentType("application/json").statusCode(200);

	}

	@Given("^I execute post call to register the account in registration page and I should validate the response status code and body$")
	public void I_execute_post_call_to_register_the_account_in_registration_page_and_I_should_validate_the_response_status_code_and_body() throws Throwable {

		RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		RequestSpecification httprequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "suni");
		requestParams.put("LastName", "l");
		requestParams.put("UserName", "hema");
		requestParams.put("Password", "password@1");
		requestParams.put("Email", "maruthi.g@gmail.com");
		System.out.println(requestParams);

		httprequest.body(requestParams.toString());

		Response response = httprequest.post("/register");

		System.out.println(response.body().asString());
		int code = response.getStatusCode();
		System.out.println(code);

	}

	
	@Given("^I execute get call to create the account and I should validate the response status code and body$")
	public void i_execute_post_call_to_create_the_account() throws Throwable {
         
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		response = RestAssured
				.given()
				.header("Content-Type", "application/json")
				.header("id", "5683")
				.when()
				.get("/employees")
				.then()
				.assertThat()
				.statusCode(200);

		System.out.println("expected response:" + response.extract().response().body().print());
		
		
	
		

}
	
	
	@And("^I execute the put to modify the data and I should validate the response$")
	public void i_execute_put_call_to_modify_the_data() throws Throwable {
	
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/update";
		RequestSpecification httprequest = RestAssured.given();
		httprequest.header("id", "5683");

		JSONObject requestParams = new JSONObject();
		requestParams.put("employee_name", "Lakshmi GNM");
		requestParams.put("employee_salary", "110000");
		requestParams.put("employee_age", "123");
		requestParams.put("profile_image", "");		

		httprequest.body(requestParams.toString());

		Response response = httprequest.put("/employees");

		System.out.println(response.body().asString());
		int code = response.getStatusCode();
		System.out.println(code);
		
		
	/*	HashMap<String, String> map=response.body().jsonPath().get();
		System.out.println(map.entrySet());
		*/
		
		
		

	}
	
	@Given("^I delete the updated account and validate the response and I should validate the response status code and body$")
	public void I_delete_the_updated_account_and_validate_the_response() throws Throwable {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/delete";
		RequestSpecification httprequest = RestAssured.given();
		httprequest.header("id", "5672");

		Response response = httprequest.header("id", "5683").delete("/employees");

		System.out.println(response.body().asString());
		int code = response.getStatusCode();
		System.out.println(code);
		
}
	
	
	
}
	

