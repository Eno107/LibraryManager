Feature: Testing getBooksSoldTotal method

  Scenario: Retrieve total books sold information
    Given a stock file path is provided
    And there are sold books in the stock
    When getBooksSoldTotal method is called with the path
    Then the total books sold information should be returned

  Scenario: Retrieve total books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold books in the stock
    When getBooksSoldTotal method is called with the path
    Then an appropriate message indicating no sold books should be returned