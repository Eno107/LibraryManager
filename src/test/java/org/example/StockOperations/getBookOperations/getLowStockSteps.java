package org.example.StockOperations.getBookOperations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.SharedSteps;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;
import project.librarymanager.Manager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class getLowStockSteps {

    ArrayList<Book> bookArrayList = new ArrayList<>();

    @When("getLowStock method is called with the path")
    public void get_low_stock_method_is_called_with_the_path() {
        BillNumber mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();

        Manager.billNumber = mockBillNumber;
        bookArrayList = Manager.getLowStock(path);
    }

    @Then("the method getLowStock should return a list of books with stock < {int}")
    public void the_method_get_low_stock_should_return_a_list_of_books_with_stock(Integer int1) {
        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",4));
        expectedList.add(new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",3));

        assertEquals(expectedList.size(), bookArrayList.size());
        for (int i=0;i<bookArrayList.size();i++){
            assertEquals(expectedList.get(i).getISBN(), bookArrayList.get(i).getISBN());
        }
    }

}

