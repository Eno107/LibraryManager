Feature: Testing getIntBooksSoldYear method

  Scenario: Retrieve the number of this year's sold books when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getIntBooksSoldYear method is called with the path
    Then this year's number of sold books should be returned, which is 13

  Scenario: Retrieve the number of this year's sold books when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getIntBooksSoldYear method is called with the path
    Then the getIntBooksSoldYear method should return 0
