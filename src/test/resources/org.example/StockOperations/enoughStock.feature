Feature: Testing EnoughStock method

  Scenario: Check if book has enough stock for a purchase (it does)
    Given a stock file path is provided
    * there are sold and bought books in the stock
    * a book ISBN that is part of the stock is provided
    * the quantity of it we want to purchase is provided and is enough
    When EnoughStock method is called with the path, isbn, and quantity
    Then the method EnoughStock should return true

  Scenario: Check if book has enough stock for a purchase (it doesn't)
    Given a stock file path is provided
    * there are sold and bought books in the stock
    * a book ISBN that is part of the stock is provided
    * the quantity of it we want to purchase is provided and is not enough
    When EnoughStock method is called with the path, isbn, and quantity
    Then the method EnoughStock should return false
