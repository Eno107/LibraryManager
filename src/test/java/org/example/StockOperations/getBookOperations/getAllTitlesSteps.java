package org.example.StockOperations.getBookOperations;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getAllTitlesSteps {

    ArrayList<String> answerList = new ArrayList<>();

    @When("getAllTitles method is called with the path")
    public void get_all_titles_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answerList = mockBillNumber.getAllTitles(path);
    }

    @Then("the method getAllTitles should return the list stock book titles")
    public void the_method_get_all_titles_should_return_the_list_stock_book_titles() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("In Search of Lost Time");
        expected.add("Don Quixote");
        expected.add("Ulysses");
        expected.add("Moby Dick");
        expected.add("One Hundred Years of Solitude");
        expected.add("The Great Gatsby");

        assertEquals(expected, answerList);
    }


}
