package project.librarymanager;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookOperationsTest {

    // Start of testing method "Book.getSoldDatesQuantitiesTotal()" {
    @Test
    public void testGetSoldDatesQuantitiesTotalEmptyDates() {
        // Test for an empty list of dates
        Book book = new Book("1234567890", "Test Book", "Fiction", "Supplier", 20.0, 25.0, "Author", 10);
        String expectedResult = "Test Book has had no purchases\n";
        String result = book.getSoldDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSoldDatesQuantitiesTotalNonEmptyDates() {
        Book book = new Book("0987654321", "Another Book", "Non-Fiction", "Supplier2", 15.0, 18.0, "Author2", 5);
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date());
        book.setDates(dates);
        book.addQuantity(3);

        String expectedTitle = "For \"Another Book\" We have sold:\n";
        String expectedResult = expectedTitle + "3 at " + dates.get(0) + "\n";
        String result = book.getSoldDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }
    // } end of testing method "Book.getSoldDatesQuantitiesTotal()"

    // Start of testing method "Book.getSoldDatesQuantitiesMonth()" {
    @Test
    public void testGetSoldDatesQuantitiesMonthEmptyDates() {
        Book book = new Book("1234567890", "Test Book", "Fiction", "Supplier", 20.0, 25.0, "Author", 10);
        String expectedResult = "Test Book has had no purchases\n";
        String result = book.getSoldDatesQuantitiesMonth();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetSoldDatesQuantitiesMonthNonEmptyDates() {
        Book book = new Book("0987654321", "Another Book", "Non-Fiction", "Supplier2", 15.0, 18.0, "Author2", 5);

        ArrayList<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = cal.getTime();
        dates.add(firstDayOfMonth);
        book.setDates(dates);
        book.addQuantity(3);

        String expectedTitle = "For \"Another Book\" We have sold in a month:\n";
        String expectedResult = expectedTitle + "3 at " + dates.get(0) + "\n";
        String result = book.getSoldDatesQuantitiesMonth();
        assertEquals(expectedResult, result);
    }
    // } end of testing method "Book.getSoldDatesQuantitiesMonth()"


    // Start of testing method "Book.getBoughtDatesQuantitiesTotal()" {
    @Test
    public void testGetBoughtDatesQuantitiesTotalEmptyPurchasedDates() {
        Book book = new Book("1234567890", "Test Book", "Fiction", "Supplier", 20.0, 25.0, "Author", 10);
        String expectedResult = "We have made no purchases on \"Test Book\"\n";
        String result = book.getBoughtDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetBoughtDatesQuantitiesTotalNonEmptyPurchasedDates() {
        Book book = new Book("0987654321", "Another Book", "Non-Fiction", "Supplier2", 15.0, 18.0, "Author2", 5);

        ArrayList<Date> purchasedDates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        purchasedDates.add(currentDate);
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(3);

        String expectedTitle = "For \"Another Book\" We have bought in a day:\n";
        String expectedResult = expectedTitle + "3 at " + purchasedDates.get(0) + "\n";
        String result = book.getBoughtDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }
    // } end of testing method "Book.getBoughtDatesQuantitiesTotal()"

    // Start of testing method "Book.getBoughtDatesQuantitiesYear()" {
    @Test
    public void testGetBoughtDatesQuantitiesYearEmptyPurchasedDates() {
        Book book = new Book("1234567890", "Test Book", "Fiction", "Supplier", 20.0, 25.0, "Author", 10);
        String expectedResult = "We have made no purchases on \"Test Book\"\n";
        String result = book.getBoughtDatesQuantitiesYear();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetBoughtDatesQuantitiesYearNonEmptyPurchasedDates() {
        Book book = new Book("0987654321", "Another Book", "Non-Fiction", "Supplier2", 15.0, 18.0, "Author2", 5);

        ArrayList<Date> purchasedDates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        Date currentDate = cal.getTime();
        purchasedDates.add(currentDate);
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(3);

        String expectedTitle = "For \"Another Book\" We have bought in a year:\n";
        String expectedResult = expectedTitle + "3 at " + purchasedDates.get(0) + "\n";
        String result = book.getBoughtDatesQuantitiesYear();
        assertEquals(expectedResult, result);
    }
    // } end of testing method "Book.getBoughtDatesQuantitiesYear()"

    // Start of testing method "Book.getNumberDatesMonth()" {
    @Test
    public void testGetNumberDatesMonth_WithEmptyDatesAndPurchasedAmount() {
        Book book = new Book("ISBN-1234");
        int result = book.getNumberDatesMonth(new ArrayList<>(), new ArrayList<>());

        assertEquals(0, result);
    }

    @Test
    public void testGetNumberDatesMonth_WithNonMatchingMonth() {
        Book book = new Book("ISBN-5678");
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(122, 0, 1));
        dates.add(new Date(122, 1, 15));
        ArrayList<Integer> purchasedAmount = new ArrayList<>();
        purchasedAmount.add(2);
        purchasedAmount.add(3);

        int result = book.getNumberDatesMonth(dates, purchasedAmount);

        assertEquals(0, result);
    }

    @Test
    public void testGetNumberDatesMonth_WithMatchingMonth() {
        Book book = new Book("ISBN-91011");
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date());
        ArrayList<Integer> purchasedAmount = new ArrayList<>();
        purchasedAmount.add(5);

        int result = book.getNumberDatesMonth(dates, purchasedAmount);

        assertEquals(5, result);
    }
    // } end of testing method "Book.getNumberDatesMonth()"

    // Start of testing method "Book.getTotalBooksBoughtDay()" {
    @Test
    public void testGetTotalBooksBoughtDay() {

        Book book = new Book("ISBN123");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date startOfDay = calendar.getTime();

        book.addPurchasedDate(startOfDay);
        book.addQuantitiesPurchased(5);

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date previousDay = calendar.getTime();
        book.addPurchasedDate(previousDay);
        book.addQuantitiesPurchased(3);

        assertEquals(5, book.getTotalBooksBoughtDay());
    }
    // } end of testing method "Book.getTotalBooksBoughtDay()"

    // Start of testing method "Book.getTotalBooksBoughtYear()" {
    @Test
    public void testGetTotalBooksBoughtYear() {
        Book book = new Book("1234567890");

        ArrayList<Date> purchasedDates = new ArrayList<>();
        ArrayList<Integer> quantitiesPurchased = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.JANUARY, 1);

        purchasedDates.add(calendar.getTime());
        quantitiesPurchased.add(10);

        for (int i = 0; i < purchasedDates.size(); i++) {
            book.addPurchasedDate(purchasedDates.get(i));
            book.addQuantitiesPurchased(quantitiesPurchased.get(i));
        }

        int expected = 0;
        for (int i = 0; i < purchasedDates.size(); i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(purchasedDates.get(i));
            if (cal.get(Calendar.YEAR) == 2023) {
                expected += quantitiesPurchased.get(i);
            }
        }

        int actual = book.getTotalBooksBoughtYear();

        assertEquals(expected, actual);
    }
    // } end of testing method "Book.getTotalBooksBoughtYear()"
}
