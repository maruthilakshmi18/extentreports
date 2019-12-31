package seleniumrest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;

import Files.PayLoad;
import Files.Resources;
import Files.ReusableMethods;
import TestRunner.MainClass;
import cucumber.api.java.en.Given;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonTraverse extends MainClass {

	@Given("^I execute get call to register the account in registration page and verify response body$")
	public void i_execute_get_call_to_register_the_account_in_registration_page_and_verify_response_body()
			throws Throwable {

		getData();

		RestAssured.baseURI = props.getProperty("HOST");
		Response res = given().header("Content-Type", "application/json").when().get(Resources.getResourceData()).then().log().headers().
				assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();

		String responsestring= res.asString();
		
		
		//JsonPath js=new JsonPath(responsestring);
		JSONArray array=new JSONArray(responsestring);
		System.out.println("Json obeject is:"+array);
		for (int i = 1; i < array.length(); i++) {
		System.out.println(array.getJSONObject(i).getInt("id"));
		
		//JsonPath js = ReusableMethods.rawToJson(res);
		

		/*int count = js.get("array.size()");
		System.out.println("array ----"+js.getList("array"));

		System.out.println("size of array is:"+count);*/

	
			//System.out.println(jsonArray);
	/*int id=jsonArray.getInt(1);
	System.out.println(id);*/
			
		}

	}

}
