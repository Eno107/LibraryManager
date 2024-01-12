package org.example.StockOperations.timeReports.Bought;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import org.junit.jupiter.api.Test;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksBoughtYearSteps {

    private String answer;

    @When("the getBooksBoughtYear method is called with the path")
    public void the_get_books_bought_year_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksBoughtYear(path);
    }

    @Then("this year's bought books information should be returned")
    public void this_year_s_bought_books_information_should_be_returned() {

        StringBuilder expected = new StringBuilder("For Books Bought In A Year We Have\n\n");
        for (Book book : MockSetup.booksWithDates) {
            expected.append(book.getBoughtDatesQuantitiesYear());
        }
        assertEquals(expected.toString(), answer);
    }

    @Then("this year's no bought books information should be returned")
    public void this_year_s_no_bought_books_information_should_be_returned() {

        assertEquals("""
                For Books Bought In A Year We Have

                We have made no purchases on "In Search of Lost Time"
                We have made no purchases on "Ulysses"
                """, answer);

    }
}
