Feature: Testing getTotalBoughtBooksYear method

  Scenario: Retrieve the number of this year's sold books when there are bought books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When the getTotalBoughtBooksYear method is called with the path
    Then this year's number of bought books should be returned, which is 12

  Scenario: Retrieve the number of this year's sold books when there are no bought books
    Given a stock file path is provided
    And there are no sold and bought books in the stock
    When the getTotalBoughtBooksYear method is called with the path
    Then the getTotalBoughtBooksYear method should return 0
