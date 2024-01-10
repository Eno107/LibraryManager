package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import project.librarymanager.BillNumber;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class getCategoriesSteps {
    private String path;
    private ArrayList<String> categories;
    private BillNumber mockBillNumber;

    @Given("a stock file path is provided")
    public void givenAStockFilePathIsProvided() {
        path = "../file.txt";
    }

    @Given("there are sold books in the stock")
    public void givenStockBooksWithDatesAreAvailable() {
        mockBillNumber = MockSetup.createMockBillNumberWithDates();
    }

    @When("getCategories method is called with the path")
    public void whenGetCategoriesMethodIsCalledWithDates() {
        categories = mockBillNumber.getCategories(path);
    }

    @Then("the list of categories should be returned")
    public void thenTheListOfCategoriesShouldBeReturned(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Modernist");
        expected.add("Novel");
        expected.add("Fiction");
        expected.add("Adventure fiction");
        expected.add("Magic realism");
        expected.add("Tragedy");
        for (int i=0; i<expected.size(); i++){
            assertEquals(expected.get(i), categories.get(i));
        }
    }


}