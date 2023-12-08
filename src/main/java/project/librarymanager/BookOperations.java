package project.librarymanager;

import java.util.ArrayList;

public interface BookOperations {

    void updateBooks(ArrayList<Book> arr);

    ArrayList<Book> getStockBooks(String path);

    void setInitialStock(String path);

    ArrayList<String> getCategories(String path);

    ArrayList<Book> getBookFromCategory(String path, String category);

    void addBookToStock(String path, Book book);

    String showStringStock(String path);

    boolean partOfCateogriesChecker(ArrayList<String> categoriesStock,String category);

    String getBooksSoldTotal(String path);

    String getBooksBoughtTotal(String path);

    String getBooksSoldDay(String path);

    String getBooksSoldMonth(String path);

    String getBooksSoldYear(String path);

    String getBooksBoughtDay(String path);

    String getBooksBoughtMonth(String path);

    String getBooksBoughtYear(String path);

    int getIntBooksSoldDay(String path);

    int getIntBooksSoldMonth(String path);

    int getIntBooksSoldYear(String path);

    double getIncomeDay(String path);

    double getIncomeMonth(String path);

    double getIncomeYear(String path);

    int getTotalBoughtBooksDay(String path);

    int getTotalBoughtBooksMonth(String path);

    int getTotalBoughtBooksYear(String path);

    double getCostDay(String path);

    double getCostMonth(String path);

    double getCostYear(String path);

    boolean isPartOfBooks(String path, String ISBN);

    ArrayList<String> getISBNName(String path);

    ArrayList<String> getAllTitles(String path);

    ArrayList<Integer> getAllStock(String path);
}
