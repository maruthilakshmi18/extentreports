Feature: create the account in typicode site 

@post-propertyfile 
Scenario: 
	Verify to add the account in registration page using post call and response 
	Given I execute post call to register the account in registration page 
	And I pass it in delete rest call and validate the response status code and body 
	
@Post-propertyfileinBaseClass 
Scenario: 
	Verify to add the account in registration page using post call and response 
	Given I execute post call to register the account in registration page And I pass it in delete rest call and validate the response status code and body 
