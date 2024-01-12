Feature: Testing  getBooksSoldTotal method

  Scenario: Retrieve total books sold information when there are sold books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getBooksSoldTotal method is called with the path
    Then the total books sold information should be returned

  Scenario: Retrieve total books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getBooksSoldTotal method is called with the path
    Then the total no sold books information should be returned
