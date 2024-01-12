Feature: Testing getBookFromCategory method


  Scenario: Retrieve books from a category
    Given a stock file path is provided
    And there are sold and bought books in the stock
    And a category is provided
    When getBookFromCategory method is called with the path & category
    Then the list of books from that category should be returned
