Feature: Testing getTotalBoughtBooksDay method

  Scenario: Retrieve the number of today's sold books when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getTotalBoughtBooksDay method is called with the path
    Then today's number of bought books should be returned, which is 4

  Scenario: Retrieve the number of today's sold books when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getTotalBoughtBooksDay method is called with the path
    Then the getTotalBoughtBooksDay method should return 0
