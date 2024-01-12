package org.example;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.librarymanager.Administrator;
import project.librarymanager.BillNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getSalariesSteps {

    private Double answer;

    @When("the method to retrieve the total sum of salaries is called")
    public void the_method_to_retrieve_the_total_sum_of_salaries_is_called() {
        answer = Administrator.getSalaries();
    }

    @Then("the total sum of salaries for all employees should be {double}")
    public void the_total_sum_of_salaries_for_all_employees_should_be(Double double1) {
        assertEquals(double1, answer);
    }

}
