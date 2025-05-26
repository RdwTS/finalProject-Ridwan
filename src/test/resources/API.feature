@api
  Feature: Run API

#  Scenario: running TestNG suite
#  Given running TestNG suite "src/test/resources/API-test.xml"

    Background:
      Given the API base URL is "https://dummyapi.io/"
      And the header "app-id" is set to "63a804408eb0cb069b57e43a"

    Scenario: Get user list
      When I send a GET request to "data/v1/user/"
      Then the response status code should be 200
      And the response body "data.id.size()" should be 20

    Scenario: Get user by ID
      When I send a GET request to "data/v1/user/682aacd35944cbd169142c64"
      Then the response status code should be 200
      And the response should contain user ID equals to "682aacd35944cbd169142c64" ID

    Scenario: Get user by invalid ID
      When I send a GET request to "data/v1/user/A"
      Then the response status code should be 400

    Scenario: Update user
      When I send a PUT request to "data/v1/user/682aacd35944cbd169142c64" to update firstName to "updateUser1"
      Then the response status code should be 200
      And the response should contain firstName "updateUser1"

    Scenario: Validate JSON Schema
      When I send a GET request to "data/v1/user/682aacd35944cbd169142c64"
      Then the response status code should be 200
      And the response should match JSON schema "getSingleUserJsonSchema.json"

    Scenario: Create new user and Delete user
      When I send a POST request to "data/v1/user/create" firstName "Tegar" and lastName "Setiawan" andwith valid user payload
      Then the response status code should be 200
      And the response should contain firstName "Tegar" and lastName "Setiawan"
      And I store the created user ID
      When I send a DELETE request to "data/v1/user/{createdUserId}"
      Then the response status code should be 200

    Scenario: Get tag list, store "sea" tag, and verify posts filtered by the tag
      When I send a GET request to "data/v1/tag"
      Then the response status code should be 200
      And I extract and store the tag value "sea" from the response body
      When I send a GET tag request to "data/v1/tag/{seaTag}/post"
      Then the response status code should be 200
      And each item in response body "data.tags" should contain "sea"