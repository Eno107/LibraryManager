package org.example.StockOperations.getBookOperations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class getISBNNameSteps {

    ArrayList<String> answerList = new ArrayList<>();

    @When("getISBNName method is called with the path")
    public void get_isbn_name_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answerList = mockBillNumber.getISBNName(path);
    }

    @Then("the method getISBNName should return the list of ISBNs combined with their titles")
    public void the_method_get_isbn_name_should_return_the_list_of_isb_ns_combined_with_their_titles() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("0096184570112 - In Search of Lost Time");
        expected.add("0629778828041 - Don Quixote");
        expected.add("4647500268094 - Ulysses");
        expected.add("9515267203718 - Moby Dick");
        expected.add("3655736671389 - One Hundred Years of Solitude");
        expected.add("3115666367951 - The Great Gatsby");

        assertEquals(expected, answerList);
    }


}
