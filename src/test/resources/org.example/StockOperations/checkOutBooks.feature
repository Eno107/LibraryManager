Feature: Testing checkOutBooks method

  Scenario: Check out books with duplicate entries and update stock and billing information
    Given a librarian is logged in
    And a stock file path is provided
    * there are sold and bought books in the stock
    * a list of books and a list of their wanted quantities is provided (including duplicates)
    When the checkOutBooks method is called with the path, a list of books, and quantities
    Then the bill file number should be incremented to 1
    And the bill file should be created
    * the billing information should be updated with the purchased books and total price
