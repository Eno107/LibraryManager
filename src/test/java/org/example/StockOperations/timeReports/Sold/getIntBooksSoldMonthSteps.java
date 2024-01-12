package org.example.StockOperations.timeReports.Sold;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getIntBooksSoldMonthSteps {

    private int answer;

    @When("the getIntBooksSoldMonth method is called with the path")
    public void the_get_int_books_sold_month_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getIntBooksSoldMonth(path);
    }

    @Then("this month's number of sold books should be returned, which is {int}")
    public void this_month_s_number_of_sold_books_should_be_returned_which_is(Integer int1) {
        assertEquals(int1, answer);
    }

    @Then("the getIntBooksSoldMonth method should return {int}")
    public void the_get_int_books_sold_month_method_should_return(Integer int1) {
        assertEquals(int1, answer);
    }

}
