package org.example;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class getBooksSoldTotalSteps{

    private String answer;

    @When("the getBooksSoldTotal method is called with the path")
    public void getBooksSoldTotalMethodIsCalledWithThePath() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksSoldTotal(path);
        assertNotNull(answer);
    }

    @Then("the total books sold information should be returned")
    public void theTotalBooksSoldInformationShouldBeReturned() {
        StringBuilder expected = new StringBuilder("For Books Sold in Total We Have\n\n");
        for (Book book: MockSetup.booksWithDates){
            expected.append(book.getSoldDatesQuantitiesTotal());
        }
        assertEquals(expected.toString(), answer);
    }
}
