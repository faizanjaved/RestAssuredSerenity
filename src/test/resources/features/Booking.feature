Feature: Booking API

  Scenario: Create a new booking in the API
    Given Booking API is available
    When I add a booking in the API
    Then the response has 200 response code
    And the booking requests response contains the correct json data


  Scenario: Create a new booking in the API with data table
    Given Booking API is available
    When I add a booking in the API with following
      | firstname | lastname | totalprice | depositpaid | additionalneeds |
      | Faizan    | Javed    | 100        | true        | Lunch           |
      | Ahmed     |          | 250        | false       | Dinner          |
    Then the response has following response code
      | code |
      | 200  |
      | 500  |

  Scenario: Delete a booking in the API
    Given Booking API is available
    When I delete a booking in the API
    Then the response has 201 response code
    When getting the same booking with Id
    Then the response has 404 response code


  Scenario Outline: Update a booking in the API
    Given Booking API is available
    When I update booking with following
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <additionalneeds> |
    Then the response has <code> response code
    And the booking requests response contains the correct json data
    Examples:
      | firstname | lastname | totalprice | depositpaid | additionalneeds | code |
      | Faizan    | Javed    | 100        | true        | Lunch           | 200  |
      | Bob       |          | 300        | true        | Dinner          | 400  |
      |           | Jon      | 300        | true        | Dinner          | 400  |