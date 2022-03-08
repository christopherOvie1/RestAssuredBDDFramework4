Feature: Validating Place API Test

  Scenario Outline: Using AddplaceAPI to Verify that place is added succesfully
    Given Addplace playload having parameters "<name>" and "<language>" and "<address>"
    When user makes a request to the "AddplaceAPI" endpoint with "POST" request
    Then user should expect to see a success status code 200
    And confirms "status" in the response body  is "OK"
    And confirms "scope" in the response body  is "APP"
    And verify that place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name       | language | address                       |
      | chris ovie | English  | 235 salford street Manchester |

  @DeletePlace
  Scenario: Using DeleteplaceAPI verify that place is deleted succesfully
    Given Delete payload
    When user makes a request to the "deletePlaceAPI" endpoint with "POST" request
    Then user should expect to see a success status code 200
    And confirms "status" in the response body  is "OK"
