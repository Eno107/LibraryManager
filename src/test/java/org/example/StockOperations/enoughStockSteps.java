package org.example.StockOperations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Librarian;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class enoughStockSteps {

    private String ISBN;
    private int quantity;

    private boolean answer;

    @Given("a book ISBN that is part of the stock is provided")
    public void a_book_isbn_that_is_part_of_the_stock_is_provided() {
        ISBN = "0096184570112";
    }

    @Given("the quantity of it we want to purchase is provided and is enough")
    public void the_quantity_of_it_we_want_to_purchase_is_provided_and_is_enough() {
        quantity = 5;
    }

    @Given("the quantity of it we want to purchase is provided and is not enough")
    public void the_quantity_of_it_we_want_to_purchase_is_provided_and_is_not_enough() {
        quantity = 20;
    }

    @When("EnoughStock method is called with the path, isbn, and quantity")
    public void enough_stock_method_is_called_with_the_path_isbn_and_quantity() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();

        Librarian.billNumber = mockBillNumber;
        answer = Librarian.EnoughStock(path, ISBN, quantity);
    }

    @Then("the method EnoughStock should return true")
    public void the_method_enough_stock_should_return_true() {
        assertTrue(answer);
    }

    @Then("the method EnoughStock should return false")
    public void the_method_enough_stock_should_return_false() {
        assertFalse(answer);
    }

}
