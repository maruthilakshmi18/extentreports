Feature: Get the weather report of the city 
@get 
Scenario:
Verify the weather of the city and complete get call status and response 
	Given I execute get call for weather report 
	Then I should validate the response status code and body message 
	
	
@post
Scenario:
Verify to add the account in registration page using post call and responses
	Given I execute post call to register the account in registration page and I should validate the response status code and body 
	
	
@put 
Scenario:
Verify to add the account in registration page using put call and response 
	Given I execute get call to create the account and I should validate the response status code and body 
	And I execute the put to modify the data and I should validate the response 
	
	
@Delete 
Scenario:
Verify to add the account in registration page using put call and response 
	Given I delete the updated account and validate the response and I should validate the response status code and body