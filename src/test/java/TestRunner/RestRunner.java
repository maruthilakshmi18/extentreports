package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
plugin= {"pretty", "html:target/cucumber-RestReports"},
strict=true,
features = "src/test/java/Feature/Oauth.feature",
tags= {"@get"},
glue= {"seleniumrest"},
monochrome=true,
dryRun=false


)

public class RestRunner {
	
	

}