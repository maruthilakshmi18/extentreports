package seleniumrest;

import Files.ReusableMethods;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StaticJson {

	private Response res;
	private String ids;

	@Given("^I execute post call to create the account in registration page and verify response body$")
	public void i_execute_post_call_to_create_the_account_in_registration_page_and_verify_response_body()
			throws Throwable {

		RestAssured.baseURI = "http://api.openweathermap.org";
		res = RestAssured.given().header("Content-Type", "application/json")
				.queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
				.body(ReusableMethods.generateStringFromResource("src\\test\\java\\Files\\StaticData.Json")).when()
				.post("/data/3.0/stations").then().assertThat().statusCode(201).extract().response();

		System.out.println("post response is:" + res.prettyPrint());

		/*
		 * String response=res.asString();
		 * 
		 * JsonPath js = new JsonPath(response);
		 * 
		 * String ids = js.getString("ID"); System.out.println("id value iss:"+ids);
		 */

	}

	@And("^I validate the id from response$")
	public void i_validate_the_id_from_response() throws Throwable {

	//	i_execute_post_call_to_create_the_account_in_registration_page_and_verify_response_body();

		JsonPath js = ReusableMethods.rawToJson(res);
		ids = js.getString("ID");
		System.out.println("id value iss:" + ids);
	}

	@And("^I execute get call to get the created data$")
	public void i_execute_get_call_to_get_the_created_data() throws Throwable {

	//	i_execute_post_call_to_create_the_account_in_registration_page_and_verify_response_body();
	//	i_validate_the_id_from_response();

		Response resp = RestAssured.given().header("Content-Type", "application/json")
				.queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
				.body(ReusableMethods.generateStringFromResource("src\\test\\java\\Files\\StaticData.Json")).when()
				.get("/data/3.0/stations/" + ids).then().assertThat().statusCode(200).extract().response();

		System.out.println("get response is :" + resp.prettyPrint());
	}

}
