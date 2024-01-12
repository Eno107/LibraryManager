package org.example.StockOperations.timeReports.Income_Cost;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getCostMonthSteps {

    private double answer;

    @When("the getCostMonth method is called with the path")
    public void the_get_cost_month_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getCostMonth(path);
    }

    @Then("this month's cost should be returned, which is {double}")
    public void this_month_s_cost_should_be_returned_which_is(Double double1) {
        assertEquals(double1, answer);
    }

    @Then("the getCostMonth method should return {double}")
    public void the_get_cost_month_method_should_return(Double double1) {
        assertEquals(double1, answer);
    }

}
