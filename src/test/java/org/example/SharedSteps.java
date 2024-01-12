package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import project.librarymanager.Administrator;
import project.librarymanager.BillNumber;
import project.librarymanager.Manager;

import java.io.IOException;

public class SharedSteps {

    private static String path;
    private static BillNumber mockBillNumber;

    @Given("a stock file path is provided")
    public void givenAStockFilePathIsProvided() {
        path = "../file.txt";
    }

    @And("there are sold and bought books in the stock")
    public void givenStockBooksWithDatesAreAvailable() throws IOException {
        mockBillNumber = MockSetup.createMockBillNumberWithDates();
    }

    @And("there are no sold and bought books in the stock")
    public void givenStockBooksWithoutDates(){
        mockBillNumber = MockSetup.createMockBillNumberWithoutDates();
    }

    @Given("there are librarians")
    public void there_are_librarians(){
        if (Manager.getLibrarians().isEmpty()) {
            Manager.InstantiateLibrarians();
        }
    }

    @Given("there are managers")
    public void there_are_managers(){
        if (Administrator.getManagers().isEmpty()) {
            Administrator.InstantiateManagers();
        }
    }

    @Given("there are administrators")
    public void there_are_admins(){
        if (Administrator.getAdmins().isEmpty()) {
            Administrator.InstantiateAdmins();
        }
    }

    public static BillNumber getMockBillNumber() {
        return mockBillNumber;
    }

    public static String getPath() {
        return path;
    }
}
