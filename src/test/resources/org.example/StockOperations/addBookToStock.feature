Feature: Testing addBookToStock method

  Scenario: Add a new book to stock
    Given a stock file path is provided
    * there are no sold and bought books in the stock
    * a new book with a unique ISBN is provided
    When addBookToStock method is called with the path and the book
    Then the stock should include the new book

  Scenario: Update stock quantity for an existing book
    Given a stock file path is provided
    * there are no sold and bought books in the stock
    * an existing book with a known ISBN is provided
    When addBookToStock method is called with the path and the book
    Then the stock quantity for the existing book should be increased