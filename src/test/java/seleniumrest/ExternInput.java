
package seleniumrest;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.PayLoad;
import Files.Resources;
import TestRunner.MainClass;
import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExternInput extends MainClass {
	
   // @Test(dataProvider="weatherreport")
    
    @Given("^I execute get call to create the account \"([^\"]*)\" and \"([^\"]*)\" in registration page and verify response body$")
    public void i_execute_get_call_to_create_the_account_and_in_registration_page_and_verify_response_body(String externalidvalue, String namestring) throws Throwable {

		getData();

		// grab the response

		RestAssured.baseURI = props.getProperty("BASE");

		Response res = RestAssured.given().header("Content-Type", "application/json")
				.queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
				.body(PayLoad.getWeatherData(externalidvalue, namestring)).when().post(Resources.getResource()).then()
				.assertThat()
				.statusCode(201).and().contentType(ContentType.JSON).extract().response();

		System.out.print("Response is:" + res.asString());

	/*	JsonPath js = ReusableMethods.rawToJson(res);
		int ids = js.get("id");
		System.out.println(ids);*/

		
	}
    
   /* @DataProvider(name="weatherreport")
    public Object[][] parametrsProvided() {
    	
    return new	Object[][] {{"suni14", "suneetha"}, {"hema14", "sri"}, {"sailu14", "sailaja"}};
    
    }*/
    

}
