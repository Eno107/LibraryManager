Feature: Testing getCategories method

  Scenario: Retrieve categories from stock books
    Given a stock file path is provided
    And there are sold books in the stock
    When getCategories method is called with the path
    Then the list of categories should be returned


    #,addBookToStock, showStringStock, getIntBooksSoldMonth,
#  getIntBooksSoldYear, getIncomeDay, getIncomeMonth, getIncomeYear, getTotalBoughtBooksDay, getTotalBoughtBooksMonth,
#  getTotalBoughtBooksYear, getCostDay, getCostMonth, getCostYear, isPartOfBooks, getISBNName, getAllTitles,
#  getAllStock, removeDuplicatesSoldTitles



  # Scenario: Retrieve total books sold information when there are no sold books
  # Given a stock file path is provided
  # And there are no sold books in the stock
  # When the getBooksSoldTotal method is called with the path
  # Then an appropriate message indicating no sold books should be returned
