package org.example.StockOperations.getBookOperations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBookFromCategorySteps {

    private String category;

    ArrayList<Book> bookArrayList = new ArrayList<>();

    @And("a category is provided")
    public void a_category_is_provided() {
        category = "Modernist";
    }

    @When("getBookFromCategory method is called with the path & category")
    public void get_book_from_category_method_is_called_with_the_path_category() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        bookArrayList = mockBillNumber.getBookFromCategory(path, category);
    }

    @Then("the list of books from that category should be returned")
    public void the_list_of_books_from_that_category_should_be_returned() {

        assertNotNull(bookArrayList);
        assertFalse(bookArrayList.isEmpty());

        ArrayList<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",6));

        assertEquals(expectedBooks.size(), bookArrayList.size());


        assertEquals(expectedBooks.get(0).getCategory(), bookArrayList.get(0).getCategory());
    }



}
