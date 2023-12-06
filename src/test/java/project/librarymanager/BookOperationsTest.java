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
}
