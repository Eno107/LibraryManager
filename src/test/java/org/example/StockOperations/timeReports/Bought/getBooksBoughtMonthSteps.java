package org.example.StockOperations.timeReports.Bought;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import org.junit.jupiter.api.Test;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksBoughtMonthSteps {

    private String answer;

    @When("the getBooksBoughtMonth method is called with the path")
    public void the_get_books_bought_month_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksBoughtMonth(path);
    }

    @Then("this month's bought books information should be returned")
    public void this_month_s_bought_books_information_should_be_returned() {
        StringBuilder expected = new StringBuilder("For Books Bought In A Month We Have\n\n");
        for (Book book : MockSetup.booksWithDates) {
            expected.append(book.getBoughtDatesQuantitiesMonth());
        }

        assertEquals(expected.toString(), answer);
    }

    @Then("this month's no bought books information should be returned")
    public void this_month_s_no_bought_books_information_should_be_returned() {
        assertEquals("For Books Bought In A Month We Have\n" +
                "\n" +
                "We have made no purchases on \"In Search of Lost Time\"\n" +
                "We have made no purchases on \"Ulysses\"\n", answer);
    }

}
