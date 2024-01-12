package org.example.StockOperations.timeReports.Bought;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import org.junit.jupiter.api.Test;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksBoughtDaySteps {

    private String answer;

    @When("the getBooksBoughtDay method is called with the path")
    public void the_get_books_bought_day_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksBoughtDay(path);
    }

    @Then("today's bought books information should be returned")
    public void today_s_bought_books_information_should_be_returned() {
        String expected = "For Books Bought Today We Have\n\n" +
                "For \"" + "In Search of Lost Time" + "\" We have bought in a day:\n" +
                "1 at " + MockSetup.today + "\n" +
                "For \"" + "Don Quixote" + "\" We have bought in a day:\n" +
                "3 at " + MockSetup.today + "\n" +
                "For \"" + "Ulysses" + "\" We have bought in a day:\n" +
                "For \"" + "Moby Dick" + "\" We have bought in a day:\n" +
                "For \"" + "One Hundred Years of Solitude" + "\" We have bought in a day:\n" +
                "For \"" + "The Great Gatsby" + "\" We have bought in a day:\n";


        assertEquals(expected, answer);
    }


    @Then("today's no bought books information should be returned")
    public void today_s_no_bought_books_information_should_be_returned() {
        assertEquals("""
                        For Books Bought Today We Have

                        We have made no purchases on "In Search of Lost Time"
                        We have made no purchases on "Ulysses"
                        """,
            answer);

    }

}
