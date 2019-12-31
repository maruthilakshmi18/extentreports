/*package seleniumrest;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;




public class DataProviderSampleTestng {

	
	//@Given("^I execute get call to create the account in registration page and verify response body$")
	public void addReport() throws Throwable {
	   
		RestAssured.baseURI = "http://api.openweathermap.org";
		Response response = RestAssured.given()
				.header("Content-Type", "application/json")
				.queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
				.body("{\r\n" + 
			    		"   \"external_id\": \"MURTHY411\",\r\n" + 
			    		"   \"name\": \"h Testdkjkx updated Station\",\r\n" + 
			    		"   \"latitude\": 12.34,\r\n" + 
			    		"   \"longitude\": 56.78,\r\n" + 
			    		"   \"altitude\": 933\r\n" + 
			    		"}")
				.when()
				.post("/data/3.0/stations")
				.then()
				.assertThat().statusCode(201).extract().response();
		
		System.out.println("response is :" +response.asString());

		   JsonPath js = ReusableMethods.rawToJson(response);
			int ids = js.get("id");
			System.out.println(ids);
		
	

}
	
}*/


//Data privider



package seleniumrest;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
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
import Files.ReusableMethods;

public class DataProviderSampleTestng extends MainClass {
	
    @Test(dataProvider="weatherreport")  
   
    public void addData(String externalidvalue, String namestring) throws Throwable {

		getData();

		// grab the response

		RestAssured.baseURI = props.getProperty("BASE");

		Response res = given().header("Content-Type", "application/json")
				.queryParam("appid", "a474909b9bd0b5205ac147da58080c0f")
				.body(PayLoad.getWeatherData(externalidvalue, namestring)).when().post(Resources.getResource()).then()
				.assertThat()
				.statusCode(201).and().contentType(ContentType.JSON).extract().response();

		//System.out.print("response is:" + res.asString());
		
	/*	String responsestring= res.asString();

	   JsonPath js = ReusableMethods.rawToJson(res);
		int ids = js.get("id");
		System.out.println(ids);
		
		JSONObject obj=new JSONObject(res);
		System.out.println("json obeject is:"+obj.length());
		int id=obj.getInt("0");
		System.out.println("all id are:" +id);
				
		JSONArray array=new JSONArray(responsestring);
		System.out.println(array);
		for(int i=0; i<array.length(); i++)
		{
			System.out.println(array.getJSONObject(i).getInt("0"));
		}*/

		
	}
		
    
    
    @DataProvider(name="weatherreport")
    public Object[][] parametrsProvided() {
    	
    return new	Object[][] {{"suni14", "suneetha"}, {"hema14", "sri"}, {"sailu14", "sailaja"}};
    
    }
    

}



