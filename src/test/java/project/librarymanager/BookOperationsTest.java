package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookOperationsTest {

    static Book bookWithoutDates;
    static Book bookWithDates;

    static Date today = new Date();
    static ArrayList<Date> soldDates = new ArrayList<>();
    static ArrayList<Integer> soldQuantities = new ArrayList<>();
    static ArrayList<Date> purchasedDates = new ArrayList<>();
    static ArrayList<Integer> purchasedQuantities = new ArrayList<>();

    @BeforeAll
    public static void setUp() {

        bookWithoutDates = new Book("0096184570112", "Book_Without_Dates", "Modernist", "Ingram Content Group, Inc", 65.00, 73.96, "Marcel Proust", 5);

        bookWithDates = new Book("4647500268094", "Book_With_Dates", "Fiction", "Baker & Taylor", 15.00, 18.00, "James Joyce", 2);
        bookWithDates.addDate(today);
        bookWithDates.addQuantity(2);
        bookWithDates.addPurchasedDate(today);
        bookWithDates.addQuantitiesPurchased(1);

        soldDates.add(today);
        soldQuantities.add(2);
        purchasedDates.add(today);
        purchasedQuantities.add(1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(6);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(6);

        soldDates.add(calendar.getTime());
        soldQuantities.add(6);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(6);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(2);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(6);

        soldDates.add(calendar.getTime());
        soldQuantities.add(2);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(6);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(4);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(3);

        soldDates.add(calendar.getTime());
        soldQuantities.add(4);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(3);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(7);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(4);

        soldDates.add(calendar.getTime());
        soldQuantities.add(7);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(4);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(5);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(1);

        soldDates.add(calendar.getTime());
        soldQuantities.add(5);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(1);
    }


    // Start of method testing for "Book.getSoldDatesQuantitiesYear()" {
    @Test
    public void test_getSoldDatesQuantitiesYear_noSales() {
        String result = bookWithoutDates.getSoldDatesQuantitiesYear();
        assertEquals(bookWithoutDates.getTitle() + " has had no purchases\n", result);
    }

    @Test
    public void test_getSoldDatesQuantitiesYear_withSales() {
        String result = bookWithDates.getSoldDatesQuantitiesYear();
        StringBuilder expected = new StringBuilder("For \"" + bookWithDates.getTitle() + "\" We have sold in a year:\n");
        for (int i = 0; i < soldDates.size() - 1; i++) {
            if (i == 3)
                continue;
            expected.append(soldQuantities.get(i)).append(" at ").append(soldDates.get(i)).append("\n");
        }

        assertEquals(expected.toString(), result);
    }
    // } end of "Book.getSoldDatesQuantitiesYear()" testing


    // Start of method testing for "Book.getQuantitiesPurchased()" {
    @Test
    public void test_getQuantitiesPurchased_noPurchases() {
        int result = bookWithoutDates.getQuantitiesPurchased();
        assertEquals(0, result);
    }

    @Test
    public void test_getQuantitiesPurchased_withPurchases() {
        int result = bookWithDates.getQuantitiesPurchased();
        assertEquals(21, result);
    }
    // } end of "Book.getQuantitiesPurchased()" testing

    @Test
    public void testGetStringDateTotal_NoSales() {
        Book book = new Book("1234");
        String result = book.getSoldDatesQuantitiesTotal();
        assertEquals(book.getTitle() + " has had no purchases\n", result);
    }

    @Test
    public void testGetStringDateTotal_SingleSaleToday() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantity(5);
        String result = book.getSoldDatesQuantitiesTotal();
        String expected = book.getTitle() + " has had no purchases\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGetStringDateTotal_MultipleSalesDifferentDays() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantity(5);

        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date fiveDaysAgo = calendar.getTime();
        book.addPurchasedDate(fiveDaysAgo);
        book.addQuantity(3);

        String result = book.getSoldDatesQuantitiesTotal();
        String expected = book.getTitle() + " has had no purchases\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGetStringDateTotal_MultipleSalesSameDay() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantity(5);
        book.addQuantity(3);

        // Two sales recorded on the same day, so the result should include both sales
        String result = book.getSoldDatesQuantitiesTotal();
        String expected = book.getTitle() + " has had no purchases\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGetSoldDatesQuantitiesYear_NoSales() {
        Book book = new Book("1234");
        String result = book.getSoldDatesQuantitiesYear();
        assertEquals(book.getTitle() + " has had no purchases\n", result);
    }

    @Test
    public void testGetSoldDatesQuantitiesYear_SingleSaleThisYear() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addDate(currentDate);
        book.addQuantity(5);

        String result = book.getSoldDatesQuantitiesYear();
        String expected = "For \"" + book.getTitle() + "\" We have sold in a year:\n"
                + "5 at " + currentDate + "\n";
        assertEquals(expected, result);
    }

    //
    @Test
    public void testGetSoldDatesQuantitiesYear_MultipleSalesThisYear() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addDate(currentDate);
        book.addQuantity(5);

        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date fiveDaysAgo = calendar.getTime();
        book.addDate(fiveDaysAgo);
        book.addQuantity(3);

        // Two sales recorded for the current year, so the result should include both sales
        String result = book.getSoldDatesQuantitiesYear();
        String expected = "For \"" + book.getTitle() + "\" We have sold in a year:\n"
                + "5 at " + currentDate + "\n"
                + "3 at " + fiveDaysAgo + "\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGetSoldDatesQuantitiesYear_SalesInDifferentYear() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1); // Subtract 1 year to go back in time
        Date lastYearDate = calendar.getTime();
        book.addDate(lastYearDate);
        book.addQuantity(3);

        // No sales recorded for the current year, so the result should indicate no sales
        String result = book.getSoldDatesQuantitiesYear();
        assertEquals("For \"" + book.getTitle() + "\" We have sold in a year:\n", result);
    }

    // Start of method testing for "Book.getSoldBoughtQuantitiesMonth()" {
    @Test
    public void test_getBoughtDatesQuantitiesMonth_noPurchases() {
        String result = bookWithoutDates.getBoughtDatesQuantitiesMonth();
        String expectedMessage = "We have made no purchases on \"" + bookWithoutDates.getTitle() + "\"\n";

        assertEquals(expectedMessage, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesMonth_withPurchases() {
        String result = bookWithDates.getBoughtDatesQuantitiesMonth();
        String expected = "For \"" + bookWithDates.getTitle() + "\" We have bought in a month:\n" +
                purchasedQuantities.get(0) + " at " + purchasedDates.get(0) + "\n" +
                purchasedQuantities.get(1) + " at " + purchasedDates.get(1) + "\n" +
                purchasedQuantities.get(2) + " at " + purchasedDates.get(2) + "\n";

        assertEquals(expected, result);
    }

    // } end of "Book.getSoldBoughtQuantitiesMonth()" testing
    // Start of method testing for "Book.getTotalBooksSoldMonth()" {
    @Test
    public void test_getTotalBooksSoldMonth_noSales() {
        int result = bookWithoutDates.getTotalBooksSoldMonth();
        assertEquals(0, result);
    }

    @Test
    public void test_getTotalBooksSoldMonth_withSales() {
        int result = bookWithDates.getTotalBooksSoldMonth();
        int expected = 10;
        assertEquals(expected, result);
    }
    // } end of "Books.getTotalBooksSoldMonth()" testing

    // Start of method testing for "Book.getNumberDatesYear()" {
    @Test
    public void test_getNumberDatesYear_NoPurchases() {
        int result = bookWithoutDates.getNumberDatesYear(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, result);
    }

    @Test
    public void test_getNumberDatesYear_withPurchases() {
        int result = bookWithDates.getNumberDatesYear(purchasedDates, purchasedQuantities);
        assertEquals(17, result);
    }
    // } end of "Book.getNumberDatesYear()" testing


    // Start of method testing for "Book.getTotalBooksBoughtMonth()" {
    @Test
    public void test_getTotalBooksBoughtMonth_withPurchases() {
        int result = bookWithDates.getTotalBooksBoughtMonth();
        assertEquals(13, result);
    }

    @Test
    public void test_getTotalBooksBoughtMonth_noPurchases() {
        int result = bookWithoutDates.getTotalBooksBoughtMonth();
        assertEquals(0, result);
    }
    // } end of "Book.getTotalBooksBoughtMonth()" testing


    // Start of method testing for "Book.getTotalBooksBought()" {
    @Test
    public void test_getTotalBooksBought_withPurchases() {
        int result = bookWithDates.getTotalBooksBought();
        assertEquals(21, result);
    }

    @Test
    public void test_getTotalBooksBought_noPurchases() {
        int result = bookWithoutDates.getTotalBooksBought();
        assertEquals(0, result);
    }
    // } end of "Book.getTotalBooksBought()" testing


    // Start of method testing for "Book.getSoldDatesQuantitiesTotal()" {
    @Test
    public void test_getSoldDatesQuantitiesTotal_noDates() {
        String expectedResult = "Book_Without_Dates has had no purchases\n";
        String result = bookWithoutDates.getSoldDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getSoldDatesQuantitiesTotal_withDates() {
        StringBuilder expectedTitle = new StringBuilder("For \"Book_With_Dates\" We have sold:\n");
        for (int i = 0; i < soldDates.size(); i++) {
            expectedTitle.append(soldQuantities.get(i)).append(" at ").append(soldDates.get(i)).append("\n");
        }

        assertEquals(expectedTitle.toString(), bookWithDates.getSoldDatesQuantitiesTotal());
    }
    // } end of "Book.getSoldDatesQuantitiesTotal()" testing


    // Start of method testing for "Book.getSoldDatesQuantitiesMonth()" {
    @Test
    public void test_getSoldDatesQuantitiesMonth_noDates() {
        String expectedResult = "Book_Without_Dates has had no purchases\n";
        String result = bookWithoutDates.getSoldDatesQuantitiesMonth();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getSoldDatesQuantitiesMonth_withDates() {

        String expectedTitle = "For \"Book_With_Dates\" We have sold in a month:\n";
        String expectedResult = expectedTitle +
                soldQuantities.get(0) + " at " + soldDates.get(0) + "\n" +
                soldQuantities.get(1) + " at " + soldDates.get(1) + "\n" +
                soldQuantities.get(2) + " at " + soldDates.get(2) + "\n";

        String result = bookWithDates.getSoldDatesQuantitiesMonth();

        assertEquals(expectedResult, result);
    }
    // } end of "Book.getSoldDatesQuantitiesMonth()" testing


    // Start of method testing for "Book.getBoughtDatesQuantitiesTotal()" {
    @Test
    public void test_getBoughtDatesQuantitiesTotal_noDates() {
        String expectedResult = "We have made no purchases on \"Book_Without_Dates\"\n";
        String result = bookWithoutDates.getBoughtDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesTotal_withDates() {
        StringBuilder expectedTitle = new StringBuilder("For \"Book_With_Dates\" We have bought in a day:\n");
        for (int i = 0; i < purchasedQuantities.size(); i++) {
            expectedTitle.append(purchasedQuantities.get(i)).append(" at ").append(purchasedDates.get(i)).append("\n");
        }
        assertEquals(expectedTitle.toString(), bookWithDates.getBoughtDatesQuantitiesTotal());
    }
    // } end of "Book.getBoughtDatesQuantitiesTotal()" testing


    // Start of testing method "Book.getBoughtDatesQuantitiesYear()" {
    @Test
    public void test_getBoughtDatesQuantitiesYear_noDates() {
        String expectedResult = "We have made no purchases on \"Book_Without_Dates\"\n";
        String result = bookWithoutDates.getBoughtDatesQuantitiesYear();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesYear_withDates() {
        String expectedTitle = "For \"Book_With_Dates\" We have bought in a year:\n";
        StringBuilder expectedResult = new StringBuilder(expectedTitle);
        for (int i = 0; i < purchasedQuantities.size() - 1; i++) {
            if (i == 3)
                continue;
            expectedResult.append(purchasedQuantities.get(i)).append(" at ").append(purchasedDates.get(i)).append("\n");
        }
        String result = bookWithDates.getBoughtDatesQuantitiesYear();

        assertEquals(expectedResult.toString(), result);
    }
    // } end of "Book.getBoughtDatesQuantitiesYear()" testing


    // Start of method testing for "Book.getNumberDatesMonth()" {
    @Test
    public void test_getNumberDatesMonth_noDates() {
        int result = bookWithoutDates.getNumberDatesMonth(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, result);
    }

    @Test
    public void test_getNumberDatesMonth_withDates() {
        int result = bookWithoutDates.getNumberDatesMonth(soldDates, soldQuantities);
        assertEquals(10, result);
    }
    // } end of "Book.getNumberDatesMonth()" testing


    // Start of method testing for "Book.getTotalBooksBoughtDay()" {
    @Test
    public void test_getTotalBooksBoughtDay_noDates() {
        assertEquals(0, bookWithoutDates.getTotalBooksBoughtDay());
    }

    @Test
    public void test_getTotalBooksBoughtDay_withDates() {
        assertEquals(1, bookWithDates.getTotalBooksBoughtDay());
    }
    // } end of "Book.getTotalBooksBoughtDay()" testing


    // Start of method testing for "Book.getTotalBooksBoughtYear()" {
    @Test
    public void test_getTotalBooksBoughtYear() {
        int expected = 0;
        for (int i = 0; i < purchasedDates.size(); i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(purchasedDates.get(i));
            if (cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                expected += purchasedQuantities.get(i);
            }
        }

        int actual = bookWithDates.getTotalBooksBoughtYear();

        assertEquals(expected, actual);
    }
    // } end of "Book.getTotalBooksBoughtYear()" testing

}
