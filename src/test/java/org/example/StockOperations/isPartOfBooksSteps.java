package org.example.StockOperations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.*;

public class isPartOfBooksSteps {

    private String ISBN;

    private boolean answer;


    @Given("an ISBN is provided, that matches with a book on stock")
    public void an_isbn_is_provided_that_matches_with_a_book_on_stock() {
        ISBN = "3655736671389";
    }

    @Given("an ISBN is provided, that doesn't match with a book on stock")
    public void an_isbn_is_provided_that_does_not_match_with_a_book_on_stock() {
        ISBN = "0664687443145";
    }

    @When("isPartOfBooks method is called with the path & ISBN")
    public void is_part_of_books_method_is_called_with_the_path_isbn() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.isPartOfBooks(path, ISBN);
    }

    @Then("the method isPartOfBooks should return true")
    public void the_method_is_part_of_books_should_return_true() {
        assertTrue(answer);
    }


    @Then("the method isPartOfBooks should return false")
    public void the_method_is_part_of_books_should_return_false() {
        assertFalse(answer);
    }

}
