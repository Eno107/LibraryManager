package org.example.StockOperations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class addBookToStockSteps {

    private Book book;
    private BillNumber mockBillNumber;

    private ArrayList<Book> originalArrayList = MockSetup.getBooksWithoutDates(); // Create a copy
    private final int originalStock = 4;
    private final int originalSize = originalArrayList.size();

    @Given("a new book with a unique ISBN is provided")
    public void a_new_book_with_a_unique_isbn_is_provided() {
        book = new Book("9515267203718", "Moby Dick", "Adventure fiction", "Cardinal Publishers Group", 7.00, 10.00, "Herman Melville", 6);
    }

    @Given("an existing book with a known ISBN is provided")
    public void an_existing_book_with_a_known_isbn_is_provided() {
        book = new Book("0096184570112", "In Search of Lost Time", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 4);

    }

    @When("addBookToStock method is called with the path and the book")
    public void add_book_to_stock_method_is_called_with_the_path_and_the_new_book() throws IOException {
        mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();

        mockBillNumber.addBookToStock(path, book);
    }

    @Then("the stock quantity for the existing book should be increased")
    public void the_stock_quantity_for_the_existing_book_should_be_increased() {
        ArrayList<Book> bookArrayList = mockBillNumber.getStockBooks(SharedSteps.getPath());
        for (Book value : bookArrayList)
            if (value.getISBN().equals(book.getISBN())) {
                assertEquals(book.getStock() + originalStock, value.getStock());
                value.RemoveStock(4);
            }

    }

    @Then("the stock should include the new book")
    public void the_stock_should_include_the_new_book() {
        ArrayList<Book> bookArrayList = mockBillNumber.getStockBooks(SharedSteps.getPath());

        assertEquals(originalSize + 1, bookArrayList.size());

        assertEquals(book.getISBN(), bookArrayList.get(bookArrayList.size() - 1).getISBN());

        originalArrayList.remove(bookArrayList.size() - 1);
    }
}
