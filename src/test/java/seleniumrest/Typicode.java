package seleniumrest;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Files.PayLoad;
import Files.Resources;
import Files.ReusableMethods;

public class Typicode {

	Properties prop = new Properties();
	private String ids;

	public void getData() throws IOException {
		System.out.println("welcome to restassured");

		FileInputStream fis = new FileInputStream("src\\test\\java\\Files\\environment.properties");
		prop.load(fis);

	}

	@Test
	@Given("^I execute post call to register the account in registration page$")
	public void i_execute_post_call_to_register_the_account_in_registration_page() throws Throwable {

		getData();

		// grab the response

		RestAssured.baseURI = prop.getProperty("HOST");

		Response res = given().header("Content-Type", "application/json").body(PayLoad.getbody()).when()
				.post(Resources.getResourceData()).then().assertThat().statusCode(201).and()
				.contentType(ContentType.JSON).body("body", equalTo("this is REST-Assured")).extract().response();

		// grab the id

		JsonPath js = ReusableMethods.rawToJson(res);
		int ids = js.get("id");
		System.out.println(ids);
	}

	// place the id in delete call
	@And("^I pass it in delete rest call and validate the response status code and body$")
	public void i_pass_it_in_delete_rest_call_and_validate_the_response_status_code_and_body() throws Throwable {
		Response resp = given().header("Content-Type", "application/json")
				// .body("{\"id\" : \"id\"}")
				.when().delete("/posts/" + ids).then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
				.extract().response();

		String str = resp.asString();
		System.out.println(str);

	}
}
