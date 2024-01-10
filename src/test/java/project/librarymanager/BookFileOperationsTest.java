package project.librarymanager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookFileOperationsTest {

    FileOutputStream out;
    ObjectOutputStream objOut;
    private static final BillNumber billNumber = new BillNumber();
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

            out.close();
            objOut.close();
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

            out.close();
            objOut.close();
        } catch (IOException e) {
            fail("Exception during setup");
        }
    }

    @AfterAll
    static void tearDown() throws IOException {
        Path path = Paths.get(TEST_FILE_PATH);
        Files.delete(path);
    }


    // Start of method testing for "BillNumber.showStringStock()" {
    @Test
    public void test_showStringStock_noData(){
        setUpWithoutDates();
        assertEquals(""" 
             Currently in Stock:
             Book [ISBN=0096184570112, title=In Search of Lost Time, category=Modernist, supplier=Ingram Content Group, Inc, sellingPrice=73.96, originalPrice=65.0, author=Marcel Proust, stock=4]
             Book [ISBN=4647500268094, title=Ulysses, category=Fiction, supplier=Baker & Taylor, sellingPrice=18.0, originalPrice=15.0, author=James Joyce, stock=3]
             """,billNumber.showStringStock(TEST_FILE_PATH));
    }
    @Test
    public void test_showStringStock(){
        assertEquals("Currently in Stock:\n" +
                "Book [ISBN=0096184570112, title=In Search of Lost Time, category=Modernist, supplier=Ingram Content Group, Inc, sellingPrice=73.96, originalPrice=65.0, author=Marcel Proust, stock=6]\n" +
                "Book [ISBN=0629778828041, title=Don Quixote, category=Novel, supplier=BCH Fulfillment & Distribution, sellingPrice=6.59, originalPrice=5.0, author=Miguel de Cervantes, stock=10]\n" +
                "Book [ISBN=4647500268094, title=Ulysses, category=Fiction, supplier=Baker & Taylor, sellingPrice=18.0, originalPrice=15.0, author=James Joyce, stock=7]\n" +
                "Book [ISBN=9515267203718, title=Moby Dick, category=Adventure fiction, supplier=Cardinal Publishers Group, sellingPrice=10.0, originalPrice=7.0, author=Herman Melville, stock=6]\n" +
                "Book [ISBN=3655736671389, title=One Hundred Years of Solitude, category=Magic realism, supplier=Ingram Content Group, Inc, sellingPrice=16.99, originalPrice=13.0, author=Gabriel Garcia Marquez, stock=7]\n" +
                "Book [ISBN=3115666367951, title=The Great Gatsby, category=Tragedy, supplier=Ingram Content Group, Inc, sellingPrice=11.95, originalPrice=10.0, author=F. Scott Fitzgerald, stock=5]\n",billNumber.showStringStock(TEST_FILE_PATH));
    }
    // } end of "BillNumber.showStringStock()"


    // Start of method testing for "BillNumber.setInitialStock()" {
    @Test
    void test_setInitialStock() {
        try{
            billNumber.setInitialStock(TEST_FILE_PATH);

            FileInputStream in = new FileInputStream(TEST_FILE_PATH);
            ObjectInputStream objIn = new ObjectInputStream(in);

            ArrayList<Book> bookArrayList = new ArrayList<>();
            ArrayList<Book> expected_list = billNumber.getInitialStock();

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

            in.close();
            objIn.close();
        } catch (IOException | ClassNotFoundException e){
            fail("Exception: " + e.getMessage());
        }
    }
    // } end of "BillNumber.setInitialStock()" testing


    @Test
    void test_getCategories(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Modernist");
        categories.add("Novel");
        categories.add("Fiction");
        categories.add("Adventure fiction");
        categories.add("Magic realism");
        categories.add("Tragedy");
        ArrayList<String> categoryArrayList  = billNumber.getCategories("BooksTesting.txt");

            for (int i=0; i<categoryArrayList.size(); i++){
                assertEquals(categoryArrayList.get(i), categories.get(i));
            }
    }

    // Start of testing method "BillNumber.addBookToStock()" {
    @Test
    void test_addBookToStock_bookAlreadyInStock() {

        try{
            Book book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
            billNumber.addBookToStock(TEST_FILE_PATH, book);

            ArrayList<Book> bookArrayList = billNumber.getStockBooks(TEST_FILE_PATH);
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
            billNumber.addBookToStock(TEST_FILE_PATH, book);

            ArrayList<Book> bookArrayList = billNumber.getStockBooks(TEST_FILE_PATH);
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
                        """, billNumber.getBooksSoldDay(TEST_FILE_PATH)
                );
    }

    @Test
    public void test_getBooksSoldDay(){
           assertEquals(billNumber.getBooksSoldDay(TEST_FILE_PATH), "For Books Sold Today We Have:\n\n" +
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

    // Start of method testing for "BillNumber.getBooksSoldMonth()" {
    @Test
    public void test_getBooksSoldMonth_noDates(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Sold In A Month We Have

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, billNumber.getBooksSoldMonth(TEST_FILE_PATH)
        );
    }
    @Test
    public void test_getBooksSoldMonth_withDates () {

        StringBuilder expected = new StringBuilder("For Books Sold In A Month We Have\n\n");
        for (Book book: startBookArrayList){
            expected.append(book.getSoldDatesQuantitiesMonth());
        }

        assertEquals(expected.toString(), billNumber.getBooksSoldMonth(TEST_FILE_PATH));

    }
    // } end of "BillNumber.getBooksSoldMonth()" testing


    // Start of method testing "BillNumber.getBooksSoldTotal()" {
    @Test
    public void test_getBooksSoldTotal_noDates(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Sold in Total We Have

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, billNumber.getBooksSoldTotal(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksSoldTotal_withDates(){
        StringBuilder expected = new StringBuilder("For Books Bought in Total We Have\n\n");
        for (Book book: startBookArrayList){
            expected.append(book.getBoughtDatesQuantitiesTotal());
        }

        assertEquals(expected.toString(), billNumber.getBooksBoughtTotal(TEST_FILE_PATH));

    }
    // } end of "BillNumber.getBooksSoldTotal()" testing

    // Start of testing method "BillNumber.getBooksBoughtDay()" {
    @Test
    public void test_getBooksBoughtDay_noneBought(){
        setUpWithoutDates();
        assertEquals("""
                        For Books Bought Today We Have

                        We have made no purchases on "In Search of Lost Time"
                        We have made no purchases on "Ulysses"
                        """,
                billNumber.getBooksBoughtDay(TEST_FILE_PATH));
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


       assertEquals(expected, billNumber.getBooksBoughtDay(TEST_FILE_PATH));
    }
    // } end of testing method "BillNumber.getBooksBoughtDay()"

    // Start of method testing for "BillNumber.getBooksBoughtMonth()" {
    @Test
    public void test_getBooksBoughtMonth_noBuys(){
        setUpWithoutDates();
        assertEquals("For Books Bought In A Month We Have\n" +
                "\n" +
                "We have made no purchases on \"In Search of Lost Time\"\n" +
                "We have made no purchases on \"Ulysses\"\n", billNumber.getBooksBoughtMonth(TEST_FILE_PATH));
    }
    @Test
    public void test_getBooksBoughtMonth_withBuys(){
        StringBuilder expected = new StringBuilder("For Books Bought In A Month We Have\n\n");
        for (Book book: startBookArrayList){
            expected.append(book.getBoughtDatesQuantitiesMonth());
        }

        assertEquals(expected.toString(), billNumber.getBooksBoughtMonth(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getBooksBoughtMonth" testing

    // Start of testing method "BillNumber.getIntBooksSoldDay()" {
    @Test
    public void test_getIntBooksSoldDay_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getIntBooksSoldDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldDay(){
        assertEquals(5, billNumber.getIntBooksSoldDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getIntBooksSoldDay()" testing

    // Start of method testing for "BillNumber.getIntSoldMonth()" {
    @Test
    public void test_getIntBooksSoldMonth_noSales(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getIntBooksSoldMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldMonth_withSales(){
        assertEquals(13,billNumber.getIntBooksSoldMonth(TEST_FILE_PATH));
    }
    // end of "BillNumber.getIntSoldMonth()" testing

    // Start of testing method "BillNumber.getIncomeDay()" {
    @Test
    public void test_getIncomeDay_noSoldBooks(){
        setUpWithoutDates();

        assertEquals(0, billNumber.getIncomeDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIncomeDay(){
        assertEquals(167.69, billNumber.getIncomeDay(TEST_FILE_PATH));
    }
    // end of "BillNumber.getIncomeDay()" testing


    // Start of method testing for "BillNumber.getIncomeMonth" {
    @Test
    public void test_getIncomeMonth_noSales(){
        setUpWithoutDates();
        assertEquals(0,billNumber.getIncomeMonth(TEST_FILE_PATH));
    }
    @Test
    public void test_getIncomeMonth_withSales() {
        assertEquals(295.69,billNumber.getIncomeMonth(TEST_FILE_PATH));
    }
    // end of "BillNumber.getIncomeMonth" testing

    // Start of testing method "BillNumber.getTotalBoughtBooksDay()" {
    @Test
    public void test_getTotalBoughtBooksDay_noSoldBooks(){
        setUpWithoutDates();

        assertEquals(0, billNumber.getTotalBoughtBooksDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getTotalBoughtBooksDay(){
        assertEquals(4, billNumber.getTotalBoughtBooksDay(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getTotalBoughtBooksDay()" testing


    // Start of method testing for "BillNumber.getTotalBoughtBooksMonth()" {
    @Test
    public void test_getTotalBoughtBooksMonth_noBuys(){
        setUpWithoutDates();
        assertEquals(0,billNumber.getTotalBoughtBooksMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getTotalBoughtBooksMonth_withBuys(){
        assertEquals(12,billNumber.getTotalBoughtBooksMonth(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getTotalBoughtBooksMonth()" testing
    // Start of testing method "BillNumber.getCostDay()" {
    @Test
    public void test_getCostDay_noSoldBooks(){
        setUpWithoutDates();

        assertEquals(0, billNumber.getCostDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getCostDay(){
        assertEquals(80, billNumber.getCostDay(TEST_FILE_PATH));
    }
    // } end of testing method "BillNumber.getCostDay()"

    // Start of method testing for "BillNumber.getCostMonth()"
    @Test
    public void test_getCostMonth_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getCostMonth(TEST_FILE_PATH));
    }

    @Test
    public void test_getCostMonth(){
        assertEquals(184,billNumber.getCostMonth(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getCostMonth()" testing

    // Start of testing method "BillNumber.isPartOfBooks()" {
    @Test
    public void test_isPartOfBooks_notPart(){
        Book book = new Book("0664687443145","Hamlet","Tragedy","Publishers Group West",12.00,14.99,"William Shakespeare",12);
        assertFalse(billNumber.isPartOfBooks(TEST_FILE_PATH, book.getISBN()));
    }

    @Test
    public void test_isPartOfBooks(){
        Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
        assertTrue(billNumber.isPartOfBooks(TEST_FILE_PATH, book.getISBN()));
    }
    // } end of testing method "BillNumber.isPartOfBooks()"

    // Start of testing method "BillNumber.getAllStock()"{
    @Test
    public void test_getAllStock(){

       ArrayList<Integer> allStock = billNumber.getAllStock(TEST_FILE_PATH);

       for (int i=0; i<allStock.size(); i++){
           assertEquals(startBookArrayList.get(i).getStock(), allStock.get(i));
       }
    }
    // } end of "BillNumber.getAllStock()" testing

    // Start of testing method "BillNumber.getISBNName()" {
    @Test
    public void test_getISBNName(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("0096184570112 - In Search of Lost Time");
        expected.add("0629778828041 - Don Quixote");
        expected.add("4647500268094 - Ulysses");
        expected.add("9515267203718 - Moby Dick");
        expected.add("3655736671389 - One Hundred Years of Solitude");
        expected.add("3115666367951 - The Great Gatsby");

        assertEquals( expected, billNumber.getISBNName(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getISBNName" testing


    // Start of method testing for "BillNumber.removeDuplicatesSoldTitles" {
    @Test
    void testRemoveDuplicatesSoldTitles() {
        try {
            ArrayList<String> titles = new ArrayList<>(Arrays.asList("Book1", "Book2", "Book1", "Book3"));
            ArrayList<Integer> quantities = new ArrayList<>(Arrays.asList(5, 3, 2, 4));

            billNumber.removeDuplicatesSoldTitles(titles, quantities);

            ArrayList<String> expectedTitles = new ArrayList<>(Arrays.asList("Book1", "Book2", "Book3"));
            ArrayList<Integer> expectedQuantities = new ArrayList<>(Arrays.asList(7, 3, 4));

            assertEquals(expectedTitles, titles, "Titles mismatch after removing duplicates");
            assertEquals(expectedQuantities, quantities, "Quantities mismatch after removing duplicates");
        } catch (Exception e) {
            fail("Exception: " + e.getMessage());
        }
    }
    // } end "BillNumber.removeDuplicatesSoldTitles()" testing

    //Start of testing method "Librarian.BookPresent()"
    @Test
    public void testBookPresent_noBook(){
        Book book = new Book("3655736671123","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",7);
        String sampleISBN = book.getISBN();
        ArrayList<Book> storybooks = billNumber.getStockBooks(TEST_FILE_PATH);
        storybooks.add(book);
        storybooks.remove(book);
        boolean isBookPresent = Librarian.BookPresent( TEST_FILE_PATH , sampleISBN);
        assertFalse(isBookPresent);
    }
    @Test
    public void testBookPresent() {
        Book book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",7);
        String sampleISBN = book.getISBN();
        ArrayList<Book> storybooks = billNumber.getStockBooks(TEST_FILE_PATH);
        storybooks.add(book);
        boolean isBookPresent = Librarian.BookPresent( TEST_FILE_PATH , sampleISBN);
        assertTrue(isBookPresent);
        storybooks.remove(book);
    }
    //end of testing method Librarian.BookPresent()


    // Start of method testing for "BillNumber.updateBooks()" {
    @Test
    void testUpdateBooks() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("0096184570112", "In Search of Lost Time", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 5));
        books.add(new Book("4647500268094", "Ulysses", "Fiction", "Baker & Taylor", 15.00, 18.00, "James Joyce", 2));

        try {
            billNumber.updateBooks(TEST_FILE_PATH, books);

            ArrayList<Book> updatedBooks = billNumber.getStockBooks(TEST_FILE_PATH);
            assertEquals(books.size(), updatedBooks.size(), "Number of books in file after update");

            for (int i = 0; i < books.size(); i++) {
                assertEquals(books.get(i).getISBN(), updatedBooks.get(i).getISBN(), "ISBN match for book at index " + i);
                assertEquals(books.get(i).getTitle(), updatedBooks.get(i).getTitle(), "Title match for book at index " + i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // } end of "BillNumber.updateBooks()" testing


    // Start of method testing for "BillNumber.getBookFromCategory()" {
    @Test
    void testGetBookFromCategory() {
        try {
            billNumber.setInitialStock(TEST_FILE_PATH);
        } catch (IOException e) {
            fail("Exception during test setup");
        }

        String categoryToTest = "Modernist";

        ArrayList<Book> booksInCategory = billNumber.getBookFromCategory(TEST_FILE_PATH, categoryToTest);

        assertNotNull(booksInCategory);
        assertFalse(booksInCategory.isEmpty());

        for (Book book : booksInCategory) {
            assertEquals(categoryToTest, book.getCategory());
        }
    }
    // } end of "BillNumber.getBooksFromCategory()" testing


    // Start of method testing for "BillNumber.partOfCategoriesChecker()" {
    @Test
    void testPartOfCategoriesChecker() {
        try {
            billNumber.setInitialStock(TEST_FILE_PATH);
        } catch (IOException e) {
            fail("Exception during test setup");
        }

        ArrayList<String> categories = billNumber.getCategories(TEST_FILE_PATH);

        for (String category : categories) {
            assertTrue(billNumber.partOfCateogriesChecker(categories, category));
        }
        assertFalse(billNumber.partOfCateogriesChecker(categories, "NonExistentCategory"));
    }
    // } end of "BillNumber.partOfCategoriesChecker()" testing


    // Start of method testing for "BillNumber.getBooksSoldYear()" {
    @Test
    public void test_getBooksSoldYear_emptyDates(){

        setUpWithoutDates();
        assertEquals("""
                        For Books Sold In A Year We Have

                        In Search of Lost Time has had no purchases
                        Ulysses has had no purchases
                        """, billNumber.getBooksSoldYear(TEST_FILE_PATH)
        );
    }

    @Test
    public void test_getBooksSoldYear(){

        StringBuilder expected = new StringBuilder("For Books Sold In A Year We Have\n\n");
        for (Book book: startBookArrayList){
            expected.append(book.getSoldDatesQuantitiesYear());
        }

        assertEquals(expected.toString(), billNumber.getBooksSoldYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getBooksSoldYear()" testing


    // Start of method testing for "BillNumber.getBooksBoughtYear()" {
    @Test
    public void test_getBooksBoughtYear_noneBought(){

        setUpWithoutDates();
        assertEquals("""
                        For Books Bought In A Year We Have

                        We have made no purchases on "In Search of Lost Time"
                        We have made no purchases on "Ulysses"
                        """,
                billNumber.getBooksBoughtYear(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksBoughtYear(){

        StringBuilder expected = new StringBuilder("For Books Bought In A Year We Have\n\n");
        for (Book book: startBookArrayList){
            expected.append(book.getBoughtDatesQuantitiesYear());
        }

        assertEquals(expected.toString(), billNumber.getBooksBoughtYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getBooksBoughtYear()" testing


    // Start of method testing for "BillNumber.getIntBooksSoldYear()" {
    @Test
    public void test_getIntBooksSoldYear_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getIntBooksSoldYear(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldYear(){
        assertEquals(13, billNumber.getIntBooksSoldYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getIntBooksSoldYear()" testing


    // Start of method testing for "BillNumber.getIncomeYear()" {
    @Test
    public void test_getIncomeYear_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getIncomeYear(TEST_FILE_PATH));
    }

    @Test
    public void test_getIncomeYear(){
        assertEquals(295.69, billNumber.getIncomeYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getIncomeYear()" testing


    // Start of method testing for "BillNumber.getTotalBoughtBooksYear()" {
    @Test
    public void test_getTotalBoughtBooksYear_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getTotalBoughtBooksYear(TEST_FILE_PATH));
    }

    @Test
    public void test_getTotalBoughtBooksYear(){
        assertEquals(12, billNumber.getTotalBoughtBooksYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getTotalBoughtBooksYear()" testing


    // Start of method testing for "BillNumber.getCostYear()" {
    @Test
    public void test_getCostYear_noSoldBooks(){
        setUpWithoutDates();
        assertEquals(0, billNumber.getCostYear(TEST_FILE_PATH));
    }

    @Test
    public void test_getCostYear(){
        assertEquals(184, billNumber.getCostYear(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getCostYear()" testing


    // Start of method testing for "BillNumber.getBooksBoughtTotal()" {
    @Test
    public void test_getBooksBoughTotal_noneBought(){

        setUpWithoutDates();
        assertEquals("""
                        For Books Bought in Total We Have

                        We have made no purchases on "In Search of Lost Time"
                        We have made no purchases on "Ulysses"
                        """,
                billNumber.getBooksBoughtTotal(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksBoughtTotal(){
        String expected = "For Books Bought in Total We Have\n\n";
        for (Book book: startBookArrayList){
            expected += book.getBoughtDatesQuantitiesTotal();
        }

        assertEquals(expected, billNumber.getBooksBoughtTotal(TEST_FILE_PATH));
    }
    // } end of "BillNumber.getBooksBoughtTotal()" testing


    // Start of method testing for "BillNumber.getAllTitles()" {
    @Test
    void testGetAllTitles() {
        ArrayList<Book> testBooks = new ArrayList<>();

        try {
            billNumber.setInitialStock(TEST_FILE_PATH);
        } catch (IOException e) {
            fail("Exception during setup: " + e.getMessage());
        }

        for (Book book : testBooks) {
            try {
                billNumber.addBookToStock(TEST_FILE_PATH, book);
            } catch (IOException e) {
                fail("Exception during setup: " + e.getMessage());
            }
        }

        ArrayList<String> titles = billNumber.getAllTitles(TEST_FILE_PATH);

        assertEquals(10, titles.size());
        assertTrue(titles.contains("Ulysses"));
        assertTrue(titles.contains("One Hundred Years of Solitude"));
    }
    // } end of "BillNumber.getAllTitles()" testing


    // Start of method testing for "BillNumber.showStock()" {
    @Test
    void testShowStock() {

        String expectedOutput = "Currently in Stock:\n" +
                "Book [ISBN=0096184570112, title=In Search of Lost Time, category=Modernist, supplier=Ingram Content Group, Inc, sellingPrice=73.96, originalPrice=65.0, author=Marcel Proust, stock=6]\n" +
                "Book [ISBN=0629778828041, title=Don Quixote, category=Novel, supplier=BCH Fulfillment & Distribution, sellingPrice=6.59, originalPrice=5.0, author=Miguel de Cervantes, stock=10]\n" +
                "Book [ISBN=4647500268094, title=Ulysses, category=Fiction, supplier=Baker & Taylor, sellingPrice=18.0, originalPrice=15.0, author=James Joyce, stock=7]\n" +
                "Book [ISBN=9515267203718, title=Moby Dick, category=Adventure fiction, supplier=Cardinal Publishers Group, sellingPrice=10.0, originalPrice=7.0, author=Herman Melville, stock=6]\n" +
                "Book [ISBN=3655736671389, title=One Hundred Years of Solitude, category=Magic realism, supplier=Ingram Content Group, Inc, sellingPrice=16.99, originalPrice=13.0, author=Gabriel Garcia Marquez, stock=7]\n" +
                "Book [ISBN=3115666367951, title=The Great Gatsby, category=Tragedy, supplier=Ingram Content Group, Inc, sellingPrice=11.95, originalPrice=10.0, author=F. Scott Fitzgerald, stock=5]\n";

        assertEquals(expectedOutput,billNumber.showStringStock(TEST_FILE_PATH) );
    }
    // } end of "BillNumber.showStock()" testing


    // Start of method testing for "Librarian.checkOutBooks()" {
    @Test
    public void testCheckOutBooks_ValidData() throws IOException {
        Librarian librarian = new Librarian("username", "password");
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        books.add( new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));
        quantities.add(2);
        try {
            librarian.checkOutBooks(TEST_FILE_PATH, books, quantities);
        } catch (IOException e) {
            fail("IOException should not be thrown for valid data");
        }


        String billPath = "Bill"+BillNumber.billNumber+".txt";
        File file = new File(billPath);

        assertTrue(file.exists());

        Path path = Paths.get(billPath);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Title: \"In Search of Lost Time\", Quantities: 2, OriginalPrice 73.96, Price: 147.92");
        expected.add("");
        expected.add("Total price: 147.92 Date: " + new Date());
        expected.add("");

        try {
            try (BufferedReader reader = Files.newBufferedReader(path)){
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null){
                    assertEquals(expected.get(lineNumber++), line);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        Files.delete(path);
        BillNumber.billNumber = 0;
    }
    // } end of "Librarian.checkOutBooks()" testing


    // Start of method testing for "Librarian.getBillFilePath()" {
    @Test
    public void testGetBillFilePath () throws IOException {
        assertEquals("Bill1.txt", Librarian.getBillFilePath() );
        assertEquals("Bill2.txt", Librarian.getBillFilePath() );
        assertEquals("Bill3.txt", Librarian.getBillFilePath() );
        BillNumber.billNumber = 0;
    }
    // } end of "Librarian.getBillFilePath()" testing


    // Start of method testing for "Librarian.enoughStock()" {
    @Test
    public void testEnoughStock_EnoughQuantityAvailable() {
        assertTrue(Librarian.EnoughStock(TEST_FILE_PATH, "0096184570112", 5));
    }

    @Test
    public void testEnoughStock_InsufficientQuantityAvailable() {
        assertFalse(Librarian.EnoughStock(TEST_FILE_PATH, "0096184570112", 20));
    }

    @Test
    public void testEnoughStock_BookNotPresent() {
        assertFalse(Librarian.EnoughStock(TEST_FILE_PATH, "NonExistentISBN", 5));
    }
    // } end of "Librarian.enoughStock()" testing


    // Start of method testing for "Manager.checkStock()" {
    @Test
    public void testSufficientStock() {
        String result = Manager.checkStock(TEST_FILE_PATH);
        assertEquals("Every book has 5 or more items in stock", result);
    }

    @Test
    public void testLowStock() {
        setUpWithoutDates();
        String expectedWarning = "Warning!\n" +
                "Book: In Search of Lost Time, With ISBN code: 0096184570112, Has Stock: 4\n" +
                "Book: Ulysses, With ISBN code: 4647500268094, Has Stock: 3\n";
        assertEquals(expectedWarning, Manager.checkStock(TEST_FILE_PATH) );
    }
    // } end of "Manager.checkStock()" testing

    // Start of method testing for "Manager.getLowStock()" {
    @Test
    public void testGetLowStock_notLow() {
        ArrayList<Book> lowStockBooks = Manager.getLowStock(TEST_FILE_PATH);

        assertTrue(lowStockBooks.isEmpty(), "There should not be books with low stock");
    }

    @Test
    public void test_getLowStock_withLow() {
        setUpWithoutDates();
        ArrayList<Book> lowStockBooks = Manager.getLowStock(TEST_FILE_PATH);
        ArrayList<Book> stockBooks = billNumber.getStockBooks(TEST_FILE_PATH);

        assertEquals(stockBooks.size(), lowStockBooks.size());

        for (int i=0; i<lowStockBooks.size(); i++){
            assertEquals(stockBooks.get(i).getISBN(), lowStockBooks.get(i).getISBN());
        }


    }
    // } end of "Manager.getLowStock()" testing


}




