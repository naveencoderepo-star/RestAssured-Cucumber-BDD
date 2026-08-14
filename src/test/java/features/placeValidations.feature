Feature: Validating the place APIs

  Scenario: Verify if place is added successfully using AddPlaceAPI
    Given AddPlaceAPI is available with payload
    When user call AddPlaceAPI with valid post http request method,
    Then the API call is successful and response status code is 200
    And "Status" in response body is "OK"
    And "scope" in response body is "APP"