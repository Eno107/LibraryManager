Feature: Testing isPartOfBooks method

  Scenario: ISBN matches with a book on stock
    Given a stock file path is provided
    * an ISBN is provided, that matches with a book on stock
    * there are sold and bought books in the stock
    When isPartOfBooks method is called with the path & ISBN
    Then the method isPartOfBooks should return true

  Scenario: ISBN doesn't match with a book on stock
    Given a stock file path is provided
    * an ISBN is provided, that doesn't match with a book on stock
    * there are sold and bought books in the stock
    When isPartOfBooks method is called with the path & ISBN
    Then the method isPartOfBooks should return false
