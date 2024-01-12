Feature: Testing getTotalBoughtBooksMonth method

  Scenario: Retrieve the number of this month's sold books when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getTotalBoughtBooksMonth method is called with the path
    Then this month's number of bought books should be returned, which is 12

  Scenario: Retrieve the number of this month's sold books when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getTotalBoughtBooksMonth method is called with the path
    Then the getTotalBoughtBooksMonth method should return 0
