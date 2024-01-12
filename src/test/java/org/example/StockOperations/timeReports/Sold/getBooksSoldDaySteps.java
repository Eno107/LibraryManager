package org.example.StockOperations.timeReports.Sold;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksSoldDaySteps {
    private String answer;

    @When("the getBooksSoldDay method is called with the path")
    public void the_get_books_sold_day_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksSoldDay(path);
    }

    @Then("today's sold books information should be returned")
    public void the_today_s_sold_books_information_should_be_returned() {
        String expected = "For Books Sold Today We Have:\n\n" +
                "For \"" + "In Search of Lost Time" + "\" We have sold in a day:\n" +
                "2 at " + MockSetup.today + "\n" +
                "For \"" + "Don Quixote" + "\" We have sold in a day:\n" +
                "3 at " + MockSetup.today + "\n" +
                "For \"" + "Ulysses" + "\" We have sold in a day:\n" +
                "For \"" + "Moby Dick" + "\" We have sold in a day:\n" +
                "For \"" + "One Hundred Years of Solitude" + "\" We have sold in a day:\n" +
                "For \"" + "The Great Gatsby" + "\" We have sold in a day:\n";
        assertEquals(expected, answer);
    }

    @Then("today's no sold books information should be returned")
    public void the_today_s_no_bought_books_information_should_be_returned() {
        String expected ="""
                For Books Sold Today We Have:

                In Search of Lost Time has had no purchases
                Ulysses has had no purchases
                """;
        assertEquals(expected, answer);
    }
}
