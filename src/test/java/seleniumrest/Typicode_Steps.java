package seleniumrest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import Files.PayLoad;
import Files.Resources;
import Files.ReusableMethods;
import TestRunner.MainClass;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Typicode_Steps extends MainClass {

	@Test
	@Given("^I execute post call to register the account in registration page And I pass it in delete rest call and validate the response status code and body$")
	public void I_execute_post_call_to_register_the_account_in_registration_And_Pass_it_to_Delete_call()
			throws Throwable {

		getData();

		
		// grab the response

		RestAssured.baseURI = props.getProperty("HOST");

		Response res = given().header("Content-Type", "application/json").body(PayLoad.getbody()).when().post(Resources.getResourceData()).then()
				.assertThat().statusCode(201).and().contentType(ContentType.JSON)
				.body("body", equalTo("this is REST-Assured")).extract().response();

		// grab the id
		/*String responsestring = res.asString();
		System.out.println(responsestring);
		JsonPath js = new JsonPath(responsestring);*/
		
		JsonPath js=ReusableMethods.rawToJson(res);
		int ids = js.get("id");
		System.out.println(ids);

		// pass it to delete method
		Response resp = given().header("Content-Type", "application/json")
				// .body("{\"id\" : \"id\"}")
				.when().delete("/posts/" + ids).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();

		String str = resp.asString();
		System.out.println(str);

	}

}
