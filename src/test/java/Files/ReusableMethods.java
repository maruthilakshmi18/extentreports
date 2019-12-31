package Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import cucumber.api.java.en.Given;

public class ReusableMethods {

	public static JsonPath rawToJson(Response res) {
		String responsestring = res.asString();

		JsonPath js = new JsonPath(responsestring);

		return js;
	}

	public static XmlPath RawToXml(Response r) {
		String responsestring = r.asString();

		XmlPath xml = new XmlPath(responsestring);

		return xml;
	}
	

	public static String generateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
