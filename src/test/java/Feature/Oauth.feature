Feature: Test Oauth-Deserializing the response with Pojo class
@get
Scenario: verify response of post method by hitting Oauth
Given I hit the post method to get token and pss it to get method call to get response
And I get the code from url
And I hit the post call and get the access token
And I hit the get call and validate the response