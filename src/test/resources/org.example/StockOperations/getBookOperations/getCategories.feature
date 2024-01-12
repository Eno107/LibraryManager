Feature: Testing getCategories method

  Scenario: Retrieve categories from stock books
    Given a stock file path is provided
    And there are sold and bought books in the stock
    When getCategories method is called with the path
    Then the list of categories should be returned
