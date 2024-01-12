Feature: Testing getLowStock method

  Scenario: Retrieve books with low stock
    Given a stock file path is provided
    * there are no sold and bought books in the stock
    When getLowStock method is called with the path
    Then the method getLowStock should return a list of books with stock < 5

