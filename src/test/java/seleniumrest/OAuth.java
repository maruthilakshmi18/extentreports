package seleniumrest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import pojo.Api;
import pojo.Courses;
import pojo.GetCourse;
import pojo.WebAutomation;

public class OAuth {
	WebDriver driver;
	public String code;
	public String accessToken;
	String[] expected= {"Selenium Webdriver Java","Cypress","Protractor"};	

	@Given("^I hit the post method to get token and pss it to get method call to get response$")
	public void i_hit_the_post_method_to_get_token_and_pss_it_to_get_method_call_to_get_response() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "D:\\Selenicucumber\\chromedriver_win32_78\\chromedriver.exe");
		driver = new ChromeDriver();

		// driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		driver.get(
				"https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");

		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("maruthi.gnml@gmail.com");
		driver.findElement(By.xpath("//*[@id='identifierNext']/span/span")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Password@5289");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(Keys.ENTER);
		System.out.println(" loggedin successfully");

		Thread.sleep(10000);
		// driver.findElement(By.xpath("//*[@id='view_container']/div/div/div[2]/div/div[1]/div/form/span/div/div[1]/div[3]")).click();

	}

	@And("^I get the code from url$")
	public void i_get_the_code_from_url() throws Throwable {

		String url = driver.getCurrentUrl();

		System.out.println(url);

		String partialcode = url.split("code=")[1];

		code = partialcode.split("&scope")[0];

		System.out.println(code);
	}

	@And("^I hit the post call and get the access token$")
	public void i_hit_the_post_call_and_get_the_access_token() throws Throwable {

		String response =

				given()

						.urlEncodingEnabled(false)

						.queryParams("code", code)

						.queryParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.queryParams("grant_type", "authorization_code")

						.queryParams("state", "verifyfjdss")

						.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

						// .queryParam("scope",
						// "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

						.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").expect()
						.defaultParser(Parser.JSON)

						.when()

						.post("https://www.googleapis.com/oauth2/v4/token").asString();

		// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		accessToken = jsonPath.getString("access_token");

		System.out.println("Access token is:" + accessToken);

	}

	@And("^I hit the get call and validate the response$")
	public void i_hit_the_get_call_and_validate_the_response() throws Throwable {

		GetCourse r2 = given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

				.when()

				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
	
		System.out.println(r2);
		System.out.println("LinkedIn url is:" + r2.getLinkedIn());
		System.out.println("url is:" + r2.getUrl());
		System.out.println("price of SOAP is :" + r2.getCourses().getApi().get(1).getPrice());

		List<Api> courses = r2.getCourses().getApi();
		System.out.println("courses size is:"+courses.size());

		for (int i = 0; i < courses.size(); i++) {
			System.out.println(courses.get(i).getCourseTitle());
			if (courses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println("soap course price is:"+courses.get(i).getPrice());
			}
			
			ArrayList<String> a=new ArrayList<String>();
			List<WebAutomation> coursetitles=r2.getCourses().getWebAutomation();
			
			System.out.println("course titles size is:"+coursetitles.size());
			
			for(int j=0; j<coursetitles.size(); j++)
			{
				a.add(coursetitles.get(j).getCourseTitle());
			}
			
			System.out.println(a);
			
			List<String> expectedlist=Arrays.asList(expected);
			
			assertTrue(expectedlist.equals(a));
			
		
			
				}

	}

}
