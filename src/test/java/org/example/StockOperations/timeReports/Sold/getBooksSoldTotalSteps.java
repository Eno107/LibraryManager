package org.example.StockOperations.timeReports.Sold;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
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
    }

    @Then("the total books sold information should be returned")
    public void theTotalBooksSoldInformationShouldBeReturned() {
        StringBuilder expected = new StringBuilder("For Books Sold in Total We Have\n\n");
        for (Book book: MockSetup.booksWithDates){
            expected.append(book.getSoldDatesQuantitiesTotal());
        }
        assertEquals(expected.toString(), answer);
    }

    @Then("the total no sold books information should be returned")
    public void theNoSoldBooksInformationShouldBeReturned(){
        String expected = "For Books Sold in Total We Have\n" +
                "\n" +
                "In Search of Lost Time has had no purchases\n" +
                "Ulysses has had no purchases\n";
        assertEquals(expected, answer);
    }
}
