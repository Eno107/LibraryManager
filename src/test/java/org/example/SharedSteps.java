package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import project.librarymanager.BillNumber;

public class SharedSteps {

    private static String path;
    private static BillNumber mockBillNumber;

    @Given("a stock file path is provided")
    public void givenAStockFilePathIsProvided() {
        path = "../file.txt";
    }

    @And("there are sold books in the stock")
    public void givenStockBooksWithDatesAreAvailable() {
        mockBillNumber = MockSetup.createMockBillNumberWithDates();
    }

    public static BillNumber getMockBillNumber() {
        return mockBillNumber;
    }

    public static String getPath() {
        return path;
    }
}
