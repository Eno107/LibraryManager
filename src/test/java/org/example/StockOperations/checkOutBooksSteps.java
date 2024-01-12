package org.example.StockOperations;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.MockSetup;
import org.example.SharedSteps;
import project.librarymanager.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class checkOutBooksSteps {

    private Librarian librarian;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> originaList = MockSetup.getBooksWithDates();
    private ArrayList<Integer> quantities = new ArrayList<>();
    private BillNumber mockBillNumber;
    private String billPath;

    @Given("a librarian is logged in")
    public void a_librarian_is_logged_in() {
        librarian = new Librarian("username", "password");
    }

    @And("a list of books and a list of their wanted quantities is provided \\(including duplicates)")
    public void a_list_of_books_and_a_list_of_their_wanted_quantities_is_provided_including_duplicates() {
        books.add(new Book("0096184570112", "In Search of Lost Time", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 5));
        quantities.add(2);

        books.add(new Book("0096184570112", "In Search of Lost Time", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 5));
        quantities.add(3);

        books.add(new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10));
        quantities.add(2);
    }

    @When("the checkOutBooks method is called with the path, a list of books, and quantities")
    public void the_check_out_books_method_is_called_with_the_path_a_list_of_books_and_quantities() throws IOException {
        mockBillNumber = SharedSteps.getMockBillNumber();
        String path = SharedSteps.getPath();

        Librarian.billNumber = mockBillNumber;
        librarian.checkOutBooks(path, books, quantities);


        Manager.getLibrarians().clear();
        Manager.InstantiateLibrarians();
    }

    @Then("the bill file number should be incremented to {int}")
    public void the_bill_file_number_should_be_incremented_to(Integer int1) {
        billPath = "Bill" + 1 + ".txt";
    }

    @Then("the bill file should be created")
    public void the_bill_file_should_be_created() {
        File file = new File(billPath);
        assertTrue(file.exists());
    }

    @Then("the billing information should be updated with the purchased books and total price")
    public void the_billing_information_should_be_updated_with_the_purchased_books_and_total_price() throws IOException {
        Path path = Paths.get(billPath);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Title: \"Don Quixote\", Quantities: 2, OriginalPrice 6.59, Price: 13.18");
        expected.add("Title: \"In Search of Lost Time\", Quantities: 5, OriginalPrice 73.96, Price: 369.79999999999995");
        expected.add("");
        expected.add("Total price: 382.97999999999996 Date: " + new Date());
        expected.add("");

        try {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    assertEquals(expected.get(lineNumber++), line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        BillNumber.billNumber = 0;
        Files.delete(path);

        for (Book book : books) {
            for (Book value : originaList) {
                if (book.getISBN().equals(value.getISBN())) {
                    value.getDates().remove(value.getDates().size() - 1);
                    value.getQuantity().remove(value.getQuantity().size() - 1);
                }
            }

        }

    }
}
