package project.librarymanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookOperationsTest {

    FileOutputStream out;
    ObjectOutputStream objOut;

    private static final String TEST_FILE_PATH = "BooksTesting.txt";
    Date today = new Date();

    // NOTE: sets up test file with sold items
    @BeforeEach
    void setUp() {
        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            // book is sold today
            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
            book.addDate(today);
            book.addQuantity(2);
            objOut.writeObject(book);

            // book is sold today
            book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            book.addDate(today);
            book.addQuantity(3);
            objOut.writeObject(book);

            // book is sold tomorrow
            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(6);
            objOut.writeObject(book);

            // book is sold yesterday
            book = new Book("9515267203718","Moby Dick","Adventure fiction","Cardinal Publishers Group",7.00,10.00,"Herman Melville",5);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            objOut.writeObject(book);

            // book is sold a month ago
            book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",3);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            objOut.writeObject(book);

            // book is sold after a year
            book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",7);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.YEAR, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(5);
            objOut.writeObject(book);
        } catch (IOException e) {
            fail("Exception during setup");
        }
    }

    // NOTE: HELPER METHOD, sets up test file with unsold items
    void setUpWithoutDates() {
        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
            objOut.writeObject(book);

            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
            objOut.writeObject(book);

        } catch (IOException e) {
            fail("Exception during setup");
        }
    }


    @Test
    void test_setInitialStock() {
        try{
            BillNumber.setInitialStock(TEST_FILE_PATH);

            FileInputStream in = new FileInputStream(TEST_FILE_PATH);
            ObjectInputStream objIn = new ObjectInputStream(in);
            
            ArrayList<Book> bookArrayList = new ArrayList<>();
            ArrayList<Book> expected_list = BillNumber.getInitialStock();
            
            while (true){
                try{
                    bookArrayList.add((Book) objIn.readObject());
                } catch (EOFException e){
                    break;
                }
            }

            for (int i=0;i<bookArrayList.size();i++){
                assertEquals(bookArrayList.get(i).getISBN(), expected_list.get(i).getISBN(), "ISBN mismatch at book with index " + i);
                assertEquals(bookArrayList.get(i).getStock(), expected_list.get(i).getStock(), "Stock mismatch at book with index " + i);
            }

        } catch (IOException | ClassNotFoundException e){
            fail("Exception: " + e.getMessage());
        }
    }

    @Test
    void test_getCategories(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Modernist");
        categories.add("Novel");
        categories.add("Fiction");
        categories.add("Adventure fiction");
        categories.add("Magic realism");
        categories.add("Tragedy");
        ArrayList<String> categoryArrayList  = BillNumber.getCategories("BooksTesting.txt");

            for (int i=0; i<categoryArrayList.size(); i++){
                assertEquals(categoryArrayList.get(i), categories.get(i));
            }
    }

    // Start of testing method "BillNumber.addBookToStock()" {
    @Test
    void test_addBookToStock_bookAlreadyInStock() {

        try{
            Book book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            BillNumber.addBookToStock(TEST_FILE_PATH, book);

            ArrayList<Book> bookArrayList = BillNumber.getStockBooks(TEST_FILE_PATH);
            for (Book value : bookArrayList) {
                if (book.getISBN().equals(value.getISBN())) {
                    assertEquals(10 + book.getStock(), value.getStock());
                }
            }

        } catch (IOException e) {
            fail("Exception during setup");
        }
    }

    @Test
    void test_addBookToStock_newBook(){
        try{
            Book book = new Book("4535777140780","Lolita","Novel","Ingram Content Group, Inc",10.00,14.40,"Vladimir Nabokov",9);
            BillNumber.addBookToStock(TEST_FILE_PATH, book);

            ArrayList<Book> bookArrayList = BillNumber.getStockBooks(TEST_FILE_PATH);
            assertEquals(bookArrayList.get(bookArrayList.size() - 1).getISBN(), book.getISBN());

        } catch (IOException e) {
            fail("Exception during setup");
        }
    }
    // } end of "BillNumber.addBookToStock()" testing


    // Start of testing method "BillNumber.getBooksSoldDay()" {
    @Test
    public void test_getBooksSoldDay_emptyDates(){
        setUpWithoutDates();
        assertEquals(BillNumber.getBooksSoldDay(TEST_FILE_PATH),
                """
                        For Books Sold Today We Have:

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """);
    }

    @Test
    public void test_getBooksSoldDay(){
           assertEquals(BillNumber.getBooksSoldDay(TEST_FILE_PATH), "For Books Sold Today We Have:\n\n" +
                    "For \""+  "In Search of Lost Time" +"\" We have sold in a day:\n" +
                    "2 at "+ today +"\n" +
                    "For \"" + "Don Quixote" +"\" We have sold in a day:\n" +
                    "3 at "+ today + "\n" +
                    "For \"" + "Ulysses" + "\" We have sold in a day:\n" +
                    "For \"" + "Moby Dick" + "\" We have sold in a day:\n" +
                    "For \"" + "One Hundred Years of Solitude" +"\" We have sold in a day:\n" +
                    "For \"" + "The Great Gatsby" +"\" We have sold in a day:\n");
    }
    // } end of "BillNumber.getBooksSoldDay()" testing

}
