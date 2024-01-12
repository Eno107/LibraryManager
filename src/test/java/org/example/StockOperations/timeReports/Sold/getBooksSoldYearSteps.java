package org.example.StockOperations.timeReports.Sold;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import org.junit.jupiter.api.Test;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksSoldYearSteps {

    private String answer;

    @When("the getBooksSoldYear method is called with the path")
    public void the_get_books_sold_year_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksSoldYear(path);
    }

    @Then("this year's sold books information should be returned")
    public void this_year_s_sold_books_information_should_be_returned() {

        StringBuilder expected = new StringBuilder("For Books Sold In A Year We Have\n\n");
        for (Book book : MockSetup.booksWithDates) {
            expected.append(book.getSoldDatesQuantitiesYear());
        }

        assertEquals(expected.toString(), answer);
    }

    @Then("this year's no sold books information should be returned")
    public void this_year_s_no_bought_books_information_should_be_returned() {
        assertEquals("""
                For Books Sold In A Year We Have

                In Search of Lost Time has had no purchases
                Ulysses has had no purchases
                """, answer
        );
    }
}
