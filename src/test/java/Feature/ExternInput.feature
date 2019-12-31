Feature: Get the data externally weather site 
@get 
Scenario Outline: 
	Verify to create account in registration page using get call and response 
	Given I execute get call to create the account "<externalidvalue>" and "<namestring>" in registration page and verify response body
	Examples:
	| externalidvalue| namestring|
	|suni14| suneetha|
	 |hema14| sri|
	  |sailu14| sailaja|

@staticjsonn
Scenario:
	Verify to create account in registration page using get call and response 
	Given I execute post call to create the account in registration page and verify response body
    And I validate the id from response
    And I execute get call to get the created data
	
	