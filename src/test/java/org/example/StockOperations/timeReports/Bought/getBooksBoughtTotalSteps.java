package org.example.StockOperations.timeReports.Bought;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getBooksBoughtTotalSteps {

    private String answer;

    @When("the getBooksBoughtTotal method is called with the path")
    public void getBooksSoldTotalMethodIsCalledWithThePath() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();
        answer = mockBillNumber.getBooksBoughtTotal(path);
    }

    @Then("the total books bought information should be returned")
    public void theTotalBooksSoldInformationShouldBeReturned() {
        StringBuilder expected = new StringBuilder("For Books Bought in Total We Have\n\n");
        for (Book book: MockSetup.booksWithDates){
            expected.append(book.getBoughtDatesQuantitiesTotal());
        }
        assertEquals(expected.toString(), answer);
    }

    @Then("the total no bought books information should be returned")
    public void theNoSoldBooksInformationShouldBeReturned(){
        String expected = """
                For Books Bought in Total We Have
                                
                We have made no purchases on "In Search of Lost Time"
                We have made no purchases on "Ulysses"
                """;
        assertEquals(expected, answer);
    }
}