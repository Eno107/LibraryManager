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

    // sets up test file with items to test
    // NOTE: no dates & quantities are provided to these items
    @BeforeEach
    void setUp() {
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

    // TODO: provide a wider variety of test items that fit most methods
    void setUpWithDatesAndQuantities(){

        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
            Date date1 = new Date();
            book.addDate(date1);
            book.addQuantity(2);
            objOut.writeObject(book);

            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
            Date date2 = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date2);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(6);
            objOut.writeObject(book);

            book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            Date date3 = new Date();
            book.addDate(date3);
            book.addQuantity(3);
            objOut.writeObject(book);

            book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",3);
            Date date4 = new Date();
            calendar = Calendar.getInstance();
            calendar.setTime(date4);
            calendar.add(Calendar.MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            objOut.writeObject(book);


            book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",7);
            Date date5 = new Date();
            calendar = Calendar.getInstance();
            calendar.setTime(date5);
            calendar.add(Calendar.YEAR, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(5);
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
        categories.add("Fiction");
        ArrayList<String> categoryArrayList  = BillNumber.getCategories("BooksTesting.txt");

            for (int i=0; i<categoryArrayList.size(); i++){
                assertEquals(categoryArrayList.get(i), categories.get(i));
            }
    }

    @Test
    void test_addBookToStock() {

        try{
            Book book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            BillNumber.addBookToStock(TEST_FILE_PATH, book);
            objOut.writeObject(book);

            ArrayList<Book> bookArrayList = BillNumber.getStockBooks(TEST_FILE_PATH);

            assertEquals(bookArrayList.get(bookArrayList.size()-1).getISBN(), book.getISBN());

        } catch (IOException e) {
            fail("Exception during setup");
        }
    }

    // Start of testing method "BillNumber.getBooksSoldDay()" {
    @Test
    public void test_getBooksSoldDay_emptyDates(){
        assertEquals(BillNumber.getBooksSoldDay(TEST_FILE_PATH),
                """
                        For Books Sold Today We Have:

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """);
    }

    @Test
    public void test_getBooksSoldDay(){
        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
            Date date1 = new Date();
            book.addDate(date1);
            book.addQuantity(2);
            objOut.writeObject(book);

            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
            Date date2 = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date2);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(6);
            objOut.writeObject(book);

            book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            Date date3 = new Date();
            book.addDate(date3);
            book.addQuantity(3);
            objOut.writeObject(book);

            book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",3);
            Date date4 = new Date();
            calendar = Calendar.getInstance();
            calendar.setTime(date4);
            calendar.add(Calendar.MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            objOut.writeObject(book);


            book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",7);
            Date date5 = new Date();
            calendar = Calendar.getInstance();
            calendar.setTime(date5);
            calendar.add(Calendar.YEAR, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(5);
            objOut.writeObject(book);


            assertEquals(BillNumber.getBooksSoldDay(TEST_FILE_PATH), "For Books Sold Today We Have:\n\n" +
                    "For \""+  "In Search of Lost Time" +"\" We have sold in a day:\n" +
                    "2 at "+ date1 +"\n" +
                    "For \"" + "Ulysses" +"\" We have sold in a day:\n" +
                    "For \"" + "Don Quixote" + "\" We have sold in a day:\n" +
                    "3 at "+ date3 + "\n" +
                    "For \"" + "One Hundred Years of Solitude" +"\" We have sold in a day:\n" +
                    "For \"" + "The Great Gatsby" +"\" We have sold in a day:\n");


        } catch (IOException e) {
            fail("Exception during setup");
        }
    }
    // } end of "BillNumber.getBooksSoldDay()"

}
