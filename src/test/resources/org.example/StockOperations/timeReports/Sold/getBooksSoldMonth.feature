Feature: Testing  getBooksSoldMonth method

  Scenario: Retrieve this month's sold books information when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getBooksSoldMonth method is called with the path
    Then this month's sold books information should be returned

  Scenario: Retrieve total books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getBooksSoldMonth method is called with the path
    Then this month's no sold books information should be returned
