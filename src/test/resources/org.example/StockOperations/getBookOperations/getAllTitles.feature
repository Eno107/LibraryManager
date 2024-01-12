Feature: Testing getAllTitles method

  Scenario: Retrieve the titles of books in stock
    Given a stock file path is provided
    * there are sold and bought books in the stock
    When getAllTitles method is called with the path
    Then the method getAllTitles should return the list stock book titles
