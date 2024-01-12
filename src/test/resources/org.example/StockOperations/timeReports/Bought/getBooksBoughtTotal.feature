Feature: Testing  getBooksBoughtTotal method

  Scenario: Retrieve total books bought information when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getBooksBoughtTotal method is called with the path
    Then the total books bought information should be returned

  Scenario: Retrieve total books sold information when there are no sold books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getBooksBoughtTotal method is called with the path
    Then the total no bought books information should be returned
