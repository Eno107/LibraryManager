Feature: Testing  getBooksSoldTotal method

  Scenario: Retrieve total books sold information
    Given a stock file path is provided
    And there are sold books in the stock
    When the getBooksSoldTotal method is called with the path
    Then the total books sold information should be returned