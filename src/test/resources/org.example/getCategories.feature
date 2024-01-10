Feature: Testing getCategories method

  Scenario: Retrieve categories from stock books
    Given a stock file path is provided
    And there are sold books in the stock
    When getCategories method is called with the path
    Then the list of categories should be returned


    #  updateBooks, setInitialStock,addBookToStock, showStringStock, getIntBooksSoldMonth,
#  getIntBooksSoldYear, getIncomeDay, getIncomeMonth, getIncomeYear, getTotalBoughtBooksDay, getTotalBoughtBooksMonth,
#  getTotalBoughtBooksYear, getCostDay, getCostMonth, getCostYear, isPartOfBooks, getISBNName, getAllTitles,
#  getAllStock, removeDuplicatesSoldTitles



