Feature: Testing getISBNName method

  Scenario: Retrieve ISBN of books alongside their titles
    Given a stock file path is provided
    * there are sold and bought books in the stock
    When getISBNName method is called with the path
    Then the method getISBNName should return the list of ISBNs combined with their titles
