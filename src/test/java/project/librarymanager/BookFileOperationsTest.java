
//TODO : isPartOfBooks(), getAllStock()

package project.librarymanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookFileOperationsTest {

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
    public void test_showStringStock_noData(){
        setUpWithoutDates();
        assertEquals(""" 
             Currently in Stock:
             Book [ISBN=0096184570112, title=In Search of Lost Time, category=Modernist, supplier=Ingram Content Group, Inc, sellingPrice=73.96, originalPrice=65.0, author=Marcel Proust, stock=4]
             Book [ISBN=4647500268094, title=Ulysses, category=Fiction, supplier=Baker & Taylor, sellingPrice=18.0, originalPrice=15.0, author=James Joyce, stock=3]
             """,BillNumber.showStringStock(TEST_FILE_PATH));
    }
    @Test
    public void test_showStringStock(){
        assertEquals("Currently in Stock:\n" +
                "Book [ISBN=0096184570112, title=In Search of Lost Time, category=Modernist, supplier=Ingram Content Group, Inc, sellingPrice=73.96, originalPrice=65.0, author=Marcel Proust, stock=6]\n" +
                "Book [ISBN=0629778828041, title=Don Quixote, category=Novel, supplier=BCH Fulfillment & Distribution, sellingPrice=6.59, originalPrice=5.0, author=Miguel de Cervantes, stock=10]\n" +
                "Book [ISBN=4647500268094, title=Ulysses, category=Fiction, supplier=Baker & Taylor, sellingPrice=18.0, originalPrice=15.0, author=James Joyce, stock=7]\n" +
                "Book [ISBN=9515267203718, title=Moby Dick, category=Adventure fiction, supplier=Cardinal Publishers Group, sellingPrice=10.0, originalPrice=7.0, author=Herman Melville, stock=6]\n" +
                "Book [ISBN=3655736671389, title=One Hundred Years of Solitude, category=Magic realism, supplier=Ingram Content Group, Inc, sellingPrice=16.99, originalPrice=13.0, author=Gabriel Garcia Marquez, stock=7]\n" +
                "Book [ISBN=3115666367951, title=The Great Gatsby, category=Tragedy, supplier=Ingram Content Group, Inc, sellingPrice=11.95, originalPrice=10.0, author=F. Scott Fitzgerald, stock=5]\n",BillNumber.showStringStock(TEST_FILE_PATH));
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

    // start of testing "BillNumber.getBooksSoldMonth()"
    @Test
    public void test_getBooksSoldMonth_emptydate(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Sold In A Month We Have

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, BillNumber.getBooksSoldMonth(TEST_FILE_PATH)
        );
    }
    @Test
    public void test_getBooksSoldMonth () {
        assertEquals( "For Books Sold In A Month We Have:\n\n" +
                "For \""+  "In Search of Lost Time" +"\" We have sold in a month:\n" +
                "2 at "+ today +"\n" +
                "For \"" + "Don Quixote" +"\" We have sold in a month:\n" +
                "3 at "+ today + "\n" +
                "For \"" + "Ulysses" + "\" We have sold in a month:\n" +
                "6 at "+ today + "\n" +
                "For \"" + "Moby Dick" + "\" We have sold in a month:\n" +
                "2 at "+ today + "\n" +
                "For \"" + "One Hundred Years of Solitude" +"\" We have sold in a month:\n" +
                "For \"" + "The Great Gatsby" +"\" We have sold in a month:\n", BillNumber.getBooksSoldMonth(TEST_FILE_PATH));
    }
    // end of testing "BillNumber.getBooksSoldMonth()"
    //start of testing "BillNumber.getBooksSoldTotal()"
    @Test
    public void test_getBooksSoldTotal_noDate(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Sold in Total We Have

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, BillNumber.getBooksSoldTotal(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksSoldTotal(){
        assertEquals( "For Books Sold in Total We Have:\n\n" +
                "For \""+  "In Search of Lost Time" +"\" We have sold in a month:\n" +
                "2 at "+ today +"\n" +
                "For \"" + "Don Quixote" +"\" We have sold in a month:\n" +
                "3 at "+ today + "\n" +
                "For \"" + "Ulysses" + "\" We have sold in a month:\n" +
                "6 at "+ today + "\n" +
                "For \"" + "Moby Dick" + "\" We have sold in a month:\n" +
                "2 at "+ today + "\n" +
                "For \"" + "One Hundred Years of Solitude" +"\" We have sold in a month:\n" +
                "For \"" + "The Great Gatsby" +"\" We have sold in a month:\n", BillNumber.getBooksSoldMonth(TEST_FILE_PATH));

    }

    //end of testing "BillNumber.getBooksSoldTotal()"

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

    //start of testing "BillNumber.getBooksBoughtMonth()"
    @Test
    public void test_getBooksBoughtMonth_noData(){
        setUpWithoutDates();
        assertEquals("For Books Bought In A Month We Have\n" +
                "\n" +
                "We have made no purchases on \"In Search of Lost Time\"\n" +
                "We have made no purchases on \"Ulysses\"\n", BillNumber.getBooksBoughtMonth(TEST_FILE_PATH));
    }
    @Test
    public void test_getBooksBoughtMonth(){
        String exp = "\"For Books Bought In A Month We Have\\n\\n\" +\n\n" +
                "\"For \"In Search of Lost Time\" We have bought in a month: +\n " +
                "1 at "+today+"\n" +
                "\"For \"Don Quixote\" + \"\" We have bought in a month:\\n\" +\n" +
                "3 at " +today+ "\n" +
                "\"For \"Ulysses\" + \"\" We have bought in a month:\\n\" +\n" +
                "\"For \"Moby Dick\" + \"\" We have bought in a month:\\n\" +\n" +
                "\"For \"One Hundred Years of Solitude\" + \"\" We have bought in a month:\\n\" +\n" +
                "\"For \"The Great Gatsby\" + \"\" We have bought in a month:\\n\";\n";
        assertEquals(exp, BillNumber.getBooksBoughtMonth(TEST_FILE_PATH));
    }
    //end of testing "BillNumber.getBooksBoughtMonth"


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
    // Start of testing "BillNumber.getIntSoldMonth()"
    @Test
    public void test_getIntBooksSoldMonth_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, BillNumber.getIntBooksSoldMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldMonth(){
        assertEquals(13,BillNumber.getIntBooksSoldMonth(TEST_FILE_PATH));
    }
    //end of testing "BillNumber.getIntSoldMonth()"

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

    //Start of testing "BillNumber.getIncomeMonth"
    @Test
    public void test_getIncomeMonth_noSale(){
        setUpWithoutDates();
        assertEquals(0,BillNumber.getIncomeMonth(TEST_FILE_PATH));
    }
    @Test
    public void test_getIncomeMonth(){
        assertEquals(295.69,BillNumber.getIncomeMonth(TEST_FILE_PATH));
    }

    //End of testing  "BillNumber.getIncomeMonth"
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

    // Start of testing method "BillNumber.getTotalBoughtBooksMonth()"
    @Test
    public void test_getTotalBoughtBooksMonth_noSales(){
        setUpWithoutDates();
        assertEquals(0,BillNumber.getTotalBoughtBooksMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getTotalBoughtBooksMonth(){
        assertEquals(12,BillNumber.getTotalBoughtBooksMonth(TEST_FILE_PATH));
    }
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

    //start of "BillNumber.getCostMonth()" testing
    @Test
    public void test_getCostMonth_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, BillNumber.getCostMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getCostMonth(){
        assertEquals(184,BillNumber.getCostMonth(TEST_FILE_PATH));
    }
    // End of testing method "BillNumber.getCostMonth"
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

    // Start of testing method "BillNumber.getAllStock()"{
    @Test
    public void test_getAllStock(){

       ArrayList<Integer> allStock = BillNumber.getAllStock(TEST_FILE_PATH);

       for (int i=0; i<allStock.size(); i++){
           assertEquals(startBookArrayList.get(i).getStock(), allStock.get(i));
       }
    }
    // } end of "BillNumber.getAllStock()" testing

    // Start of testing method "BillNumber.updateBooks()" {
    @Test
    void testUpdateBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("0096184570112", "In Search of Lost Time", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 5));
        books.add(new Book("4647500268094", "Ulysses", "Fiction", "Baker & Taylor", 15.00, 18.00, "James Joyce", 2));

        try {
            BillNumber.updateBooks(TEST_FILE_PATH, books);

            ArrayList<Book> updatedBooks = BillNumber.getStockBooks(TEST_FILE_PATH);
            assertEquals(books.size(), updatedBooks.size(), "Number of books in file after update");

            for (int i = 0; i < books.size(); i++) {
                assertEquals(books.get(i).getISBN(), updatedBooks.get(i).getISBN(), "ISBN match for book at index " + i);
                assertEquals(books.get(i).getTitle(), updatedBooks.get(i).getTitle(), "Title match for book at index " + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // } end of testing method "BillNumber.updateBooks()"

    // Start of testing method "BillNumber.getBookFromCathegory() {
    @Test
    void testGetBookFromCategory() {
        try {
            BillNumber.setInitialStock(TEST_FILE_PATH);
        } catch (IOException e) {
            fail("Exception during test setup");
        }

        String categoryToTest = "Modernist";

        ArrayList<Book> booksInCategory = BillNumber.getBookFromCategory(TEST_FILE_PATH, categoryToTest);

        assertNotNull(booksInCategory);
        assertFalse(booksInCategory.isEmpty());

        for (Book book : booksInCategory) {
            assertEquals(categoryToTest, book.getCategory());
        }
    }
    // } end of testing method "BillNumber.getBooksFromCathegory()

    @Test
    public void test_getISBN_noEntry(){
        setUpWithoutDates();
        String str = "[0096184570112 - In Search of Lost Time, 4647500268094 - Ulysses]";
        assertEquals("[0096184570112 - In Search of Lost Time, 4647500268094 - Ulysses]", BillNumber.getISBNName(TEST_FILE_PATH));
    }

    @Test
    public void test_getISBN(){
        assertEquals("[0096184570112 - In Search of Lost Time, 0629778828041 - Don Quixote, 4647500268094 - Ulysses, 9515267203718 - Moby Dick, 3655736671389 - One Hundred Years of Solitude, 3115666367951 - The Great Gatsby]",BillNumber.getISBNName(TEST_FILE_PATH));
    }

    @Test
    void testRemoveDuplicatesSoldTitles() {
        try {
            // Create test data with duplicate titles and quantities
            ArrayList<String> titles = new ArrayList<>(Arrays.asList("Book1", "Book2", "Book1", "Book3"));
            ArrayList<Integer> quantities = new ArrayList<>(Arrays.asList(5, 3, 2, 4));

            BillNumber.removeDuplicatesSoldTitles(titles, quantities);

            ArrayList<String> expectedTitles = new ArrayList<>(Arrays.asList("Book1", "Book2", "Book3"));
            ArrayList<Integer> expectedQuantities = new ArrayList<>(Arrays.asList(7, 3, 4));

            assertEquals(expectedTitles, titles, "Titles mismatch after removing duplicates");
            assertEquals(expectedQuantities, quantities, "Quantities mismatch after removing duplicates");
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }

}
