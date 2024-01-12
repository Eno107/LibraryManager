package org.example.StockOperations.timeReports.Bought;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getTotalBoughtBooksDaySteps {

    private int answer;

    @When("the getTotalBoughtBooksDay method is called with the path")
    public void the_get_int_books_sold_day_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getTotalBoughtBooksDay(path);
    }

    @Then("today's number of bought books should be returned, which is {int}")
    public void today_s_number_of_bought_books_should_be_returned_which_is(Integer int1) {
        assertEquals(int1, answer);
    }

    @Then("the getTotalBoughtBooksDay method should return {int}")
    public void the_get_total_bought_books_day_method_should_return(Integer int1) {
        assertEquals(int1, answer);
    }
}
