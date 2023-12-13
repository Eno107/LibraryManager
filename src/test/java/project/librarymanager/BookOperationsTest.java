package project.librarymanager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookOperationsTest {

    // TODO
    @TempDir
    File tempDir;


    FileOutputStream out;
    ObjectOutputStream objOut;

    private static final String TEST_FILE_PATH = "BooksTesting.txt";


    Date today = new Date();

    ArrayList<Book> startBookArrayList = new ArrayList<>();

    // NOTE: sets up test file with sold & purchased items & >5 stock
    @BeforeEach
    void setUp() {
        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            // book is sold today
            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",6);
            book.addDate(today);
            book.addQuantity(2);
            book.addPurchasedDate(today);
            book.addQuantitiesPurchased(1);
            objOut.writeObject(book);
            startBookArrayList.add(book);

            // book is sold today
            book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            book.addDate(today);
            book.addQuantity(3);
            book.addPurchasedDate(today);
            book.addQuantitiesPurchased(3);
            objOut.writeObject(book);
            startBookArrayList.add(book);

            // book is sold tomorrow
            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",7);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(6);
            book.addPurchasedDate(calendar.getTime());
            book.addQuantitiesPurchased(6);
            objOut.writeObject(book);
            startBookArrayList.add(book);

            // book is sold yesterday
            book = new Book("9515267203718","Moby Dick","Adventure fiction","Cardinal Publishers Group",7.00,10.00,"Herman Melville",6);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            book.addPurchasedDate(calendar.getTime());
            book.addQuantitiesPurchased(2);
            objOut.writeObject(book);
            startBookArrayList.add(book);

            // book is sold a month ago
            book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",7);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.MONTH, -1);
            book.addDate(calendar.getTime());
            book.addQuantity(2);
            book.addPurchasedDate(calendar.getTime());
            book.addQuantitiesPurchased(2);
            objOut.writeObject(book);
            startBookArrayList.add(book);

            // book is sold after a year
            book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",5);
            calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.YEAR, 1);
            book.addDate(calendar.getTime());
            book.addQuantity(5);
            book.addPurchasedDate(calendar.getTime());
            book.addQuantitiesPurchased(5);
            objOut.writeObject(book);
            startBookArrayList.add(book);
        } catch (IOException e) {
            fail("Exception during setup");
        }
    }

    // NOTE: HELPER METHOD, sets up test file with unsold items & <5 stock
    void setUpWithoutDates() {
        try {
            out = new FileOutputStream(TEST_FILE_PATH);
            objOut = new ObjectOutputStream(out);

            Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",4);
            objOut.writeObject(book);

            book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",3);
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
        assertEquals("""
                        For Books Sold Today We Have:

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, BillNumber.getBooksSoldDay(TEST_FILE_PATH)
                );
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


    // Start of testing method "BillNumber.getBooksBoughtDay()" {
    @Test
    public void test_getBooksBoughtDay_noneBought(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Bought Today We Have

                        We have made no purchases on "In Search of Lost Time"
                        We have made no purchases on "Ulysses"
                        """,
                BillNumber.getBooksBoughtDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksBoughtDay(){

        String expected = "For Books Bought Today We Have\n\n" +
                "For \"" + "In Search of Lost Time" + "\" We have bought in a day:\n" +
                "1 at "+today+"\n" +
                "For \"" + "Don Quixote" + "\" We have bought in a day:\n" +
                "3 at "+today+"\n" +
                "For \"" + "Ulysses" + "\" We have bought in a day:\n" +
                "For \"" + "Moby Dick" + "\" We have bought in a day:\n" +
                "For \"" + "One Hundred Years of Solitude" + "\" We have bought in a day:\n" +
                "For \"" + "The Great Gatsby" + "\" We have bought in a day:\n";


       assertEquals(expected, BillNumber.getBooksBoughtDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getBooksBoughtDay()" testing


    // Start of testing method "BillNumber.getIntBooksSoldDay()" {
    @Test
    public void test_getIntBooksSoldDay_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, BillNumber.getIntBooksSoldDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldDay(){
        assertEquals(5, BillNumber.getIntBooksSoldDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getIntBooksSoldDay()" testing


    // Start of testing method "BillNumber.getIncomeDay()" {
    @Test
    public void test_getIncomeDay_noSoldBooks(){
        setUpWithoutDates();
        System.out.println(BillNumber.getIncomeDay(TEST_FILE_PATH));

        assertEquals(0, BillNumber.getIncomeDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIncomeDay(){
        assertEquals(167.69, BillNumber.getIncomeDay(TEST_FILE_PATH));
    }
    // end of "BillNumber.getIncomeDay()" testing


    // Start of testing method "BillNumber.getTotalBoughtBooksDay()" {
    @Test
    public void test_getTotalBoughtBooksDay_noSoldBooks(){
        setUpWithoutDates();

        assertEquals(0, BillNumber.getTotalBoughtBooksDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getTotalBoughtBooksDay(){
        assertEquals(4, BillNumber.getTotalBoughtBooksDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getTotalBoughtBooksDay()" testing


    // Start of testing method "BillNumber.getCostDay()" {
    @Test
    public void test_getCostDay_noSoldBooks(){
        setUpWithoutDates();

        assertEquals(0, BillNumber.getCostDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getCostDay(){
        assertEquals(80, BillNumber.getCostDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getCostDay()" testing


    // Start of testing method "BillNumber.isPartOfBooks()" {
    @Test
    public void test_isPartOfBooks_notPart(){
        Book book = new Book("0664687443145","Hamlet","Tragedy","Publishers Group West",12.00,14.99,"William Shakespeare",12);
        assertFalse(BillNumber.isPartOfBooks(TEST_FILE_PATH, book.getISBN()));
    }

    @Test
    public void test_isPartOfBooks(){
        Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
        assertTrue(BillNumber.isPartOfBooks(TEST_FILE_PATH, book.getISBN()));
    }
    // } end of "BillNumber.isPartOfBooks()" testing


    @Test
    public void test_getAllStock(){

       ArrayList<Integer> allStock = BillNumber.getAllStock(TEST_FILE_PATH);

       for (int i=0; i<allStock.size(); i++){
           assertEquals(startBookArrayList.get(i).getStock(), allStock.get(i));
       }
    }


    //TODO: Admin methods without file operations
    //TODO: @beforeAll instantiate managers
    @BeforeAll
    public static void instantiateManagers(){
        Administrator.InstantiateManagers();
    }

    @Test
    public void test_instantiateManager(){

        ArrayList<Manager> expectedManagerArrayList = new ArrayList<>();
        Manager mag = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        expectedManagerArrayList.add(mag);
        mag = new Manager("Lui54","y@.3FYrn","Lui",900,"(912) 218-2594","lu@manager.com") ;
        expectedManagerArrayList.add(mag);
        mag = new Manager("1","2","TestManager",900,"(912) 623-5353","TestEmail@librarian.com");
        expectedManagerArrayList.add(mag);

        Administrator.InstantiateManagers();
        ArrayList<Manager> managerArrayList = Administrator.getManagers();

        assertEquals(expectedManagerArrayList.size(), managerArrayList.size(), "size");
        for (int i=0; i<managerArrayList.size(); i++){
            assertEquals(expectedManagerArrayList.get(i).getUsername(), managerArrayList.get(i).getUsername(), "username");
            assertEquals(expectedManagerArrayList.get(i).getPassword(), managerArrayList.get(i).getPassword(), "password");
            assertEquals(expectedManagerArrayList.get(i).getName(), managerArrayList.get(i).getName(), "name");
            assertEquals(expectedManagerArrayList.get(i).getSalary(), managerArrayList.get(i).getSalary(), "salary");
            assertEquals(expectedManagerArrayList.get(i).getPhone(), managerArrayList.get(i).getPhone(), "phone");
            assertEquals(expectedManagerArrayList.get(i).getEmail(), managerArrayList.get(i).getEmail(), "email");
        }
    }


    @Test
    public void test_partOfManager_notPart(){
        Manager mag = new Manager("2","2","TestManager",900,"(912) 623-5353","TestEmail@librarian.com");
        assertFalse(Administrator.partOfManager(mag));
        mag = new Manager("5","95","TestManager#2",1000,"(912) 623-9999","#2TestEmail@librarian.com");
        assertFalse(Administrator.partOfManager(mag));
    }

    @Test
    public void test_partOfManager(){
        Manager mag = new Manager("1","2","TestManager",900,"(912) 623-5353","TestEmail@librarian.com");
        assertTrue(Administrator.partOfManager(mag));
    }
    // end

}
