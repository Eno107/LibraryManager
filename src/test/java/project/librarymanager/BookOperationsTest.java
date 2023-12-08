package project.librarymanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookOperationsTest {

    FileOutputStream out;
    ObjectOutputStream objOut;

    private static final String FILE_PATH = "Books.txt";
    private static final String TEST_FILE_PATH = "BooksTesting.txt";

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

        try {
            FileInputStream in = new FileInputStream(TEST_FILE_PATH);
            ObjectInputStream objIn = new ObjectInputStream(in);

            ArrayList<Book> bookArrayList = new ArrayList<>();

            while (true){
                try{
                    bookArrayList.add((Book) objIn.readObject());
                } catch (EOFException e){
                    break;
                }
            }

            ArrayList<String> categoryArrayList  = BillNumber.getCategories("BooksTesting.txt");

            for (int i=0; i<bookArrayList.size(); i++){
                assertEquals(categoryArrayList.get(i), bookArrayList.get(i).getCategory());
            }

        } catch (IOException | ClassNotFoundException e){
            fail("Exception: " + e.getMessage());
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

}
