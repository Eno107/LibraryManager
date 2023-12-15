package project.librarymanager;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
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

    @Test
    public void testGetQuantitiesPurchased_NoPurchases() {
        Book book = new Book("1234567890");
        int result = book.getQuantitiesPurchased();
        assertEquals(0, result);
    }

    @Test
    public void testGetQuantitiesPurchased_SinglePurchase() {
        Book book = new Book("123");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(5);

        // One purchase recorded, so the result should be 5
        int result = book.getQuantitiesPurchased();
        assertEquals(5, result);
    }
//
    @Test
    public void testGetQuantitiesPurchased_MultiplePurchases() {
        Book book = new Book("123456");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(5);

        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date fiveDaysAgo = calendar.getTime();
        book.addPurchasedDate(fiveDaysAgo);
        book.addQuantitiesPurchased(3);

        // Multiple purchases recorded, so the result should be the sum (5 + 3 = 8)
        int result = book.getQuantitiesPurchased();
        assertEquals(8, result);
    }
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
        String expected = book.getTitle() +" has had no purchases\n";
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
        String expected = "For \"" + book.getTitle()  +"\" We have sold in a year:\n"
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
        String expected = "For \"" + book.getTitle()  +"\" We have sold in a year:\n"
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
        assertEquals("For \"" + book.getTitle() +"\" We have sold in a year:\n", result);
    }

    @Test
    public void testGetBoughtDatesQuantitiesMonth_NoPurchases() {
        Book book = new Book("1234");
        String result = book.getBoughtDatesQuantitiesMonth();
        String expectedMessage = "We have made no purchases on \"" + book.getTitle()+"\"\n" ;

        assertEquals(expectedMessage, result);
    }
    @Test
    public void testGetBoughtDatesQuantitiesMonth_SinglePurchaseThisMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(5);

        String result = book.getBoughtDatesQuantitiesMonth();
        String expected = "For \"" + book.getTitle() + "\" We have bought in a month:\n"
                + "5 at " + currentDate + "\n";
        assertEquals(expected, result);
    }

    @Test
    public void testGetBoughtDatesQuantitiesMonth_SinglePurchaseDifferentMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // Subtract 1 month to go back in time
        Date lastMonthDate = calendar.getTime();
        book.addPurchasedDate(lastMonthDate);
        book.addQuantitiesPurchased(3);

        String result = book.getBoughtDatesQuantitiesMonth();
        assertEquals("For \"" + book.getTitle() +"\" We have bought in a month:\n", result);
    }

    @Test
    public void testGetBoughtDatesQuantitiesMonth_MultiplePurchasesThisMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(5);

        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date fiveDaysAgo = calendar.getTime();
        book.addPurchasedDate(fiveDaysAgo);
        book.addQuantitiesPurchased(3);

        String result = book.getBoughtDatesQuantitiesMonth();
        String expected = "For \"" + book.getTitle() + "\" We have bought in a month:\n"
                + "5 at " + currentDate + "\n"
                + "3 at " + fiveDaysAgo + "\n";
        assertEquals(expected, result);
    }
    @Test
    public void testGetTotalBooksSoldMonth_NoSales() {
        Book book = new Book("1234");
        int result = book.getTotalBooksSoldMonth();
        assertEquals(0, result);
    }

    @Test
    public void testGetTotalBooksSoldMonth_SingleSaleThisMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(5);

        int result = book.getTotalBooksSoldMonth();
        int expected = 0;
        assertEquals(expected, result);
    }

    @Test
    public void testGetTotalBooksSoldMonth_SingleSaleDifferentMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // Subtract 1 month to go back in time
        Date lastMonthDate = calendar.getTime();
        book.addPurchasedDate(lastMonthDate);
        book.addQuantitiesPurchased(3);
        int result = book.getTotalBooksSoldMonth();
        assertEquals(0, result);
    }

    @Test
    public void testGetTotalBooksSoldMonth_MultipleSalesThisMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        book.addPurchasedDate(currentDate);
        book.addQuantitiesPurchased(5);

        calendar.add(Calendar.DAY_OF_MONTH, -5);
        Date fiveDaysAgo = calendar.getTime();
        book.addPurchasedDate(fiveDaysAgo);
        book.addQuantitiesPurchased(3);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String expectedDate = dateFormat.format(currentDate);
        int expected = calculateExpectedResult(book, expectedDate);
        int result = book.getTotalBooksSoldMonth();
        assertEquals(expected, result);
    }

    //helper method for ^^
    private int calculateExpectedResult(Book book, String expectedDate) {
        int expected = 0;

        for (int i = 0; i < book.getDates().size(); i++) {
            Date saleDate = book.getDates().get(i);
            String saleMonth = new SimpleDateFormat("yyyy-MM").format(saleDate);

            if (saleMonth.equals(expectedDate)) {
                expected += book.getPurchasedAmount();
            }
        }
        return expected;
    }

    @Test
    public void testGetNumberDatesYear_NoPurchases() {
        Book book = new Book("1234");
        int result = book.getNumberDatesYear(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, result);
    }
     @Test
    public void testGetNumberDatesYear_SinglePurchaseThisYear() {
         Book book = new Book("ISBN-5678");
         ArrayList<Date> dates = new ArrayList<>();
         dates.add(new Date(122, 0, 1));
         //dates.add(new Date(122, 1, 15));
         ArrayList<Integer> purchasedAmount = new ArrayList<>();
         purchasedAmount.add(2);
        // purchasedAmount.add(3);
         int result = book.getNumberDatesYear(dates, purchasedAmount);
         assertEquals(0, result);
    }

    @Test
    public void testGetNumberDatesYear_multiplePurchases() {
        Book book = new Book("ISBN-5678");
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(122, 0, 1));
        dates.add(new Date(122, 1, 15));
        ArrayList<Integer> purchasedAmount = new ArrayList<>();
        purchasedAmount.add(2);
        purchasedAmount.add(3);
        int result = book.getNumberDatesYear(dates, purchasedAmount);
        assertEquals(0, result);
    }
        @Test
    public void testGetNumberDatesYear_SinglePurchaseLastYear() {
            Book book = new Book("ISBN-5678");
            ArrayList<Date> dates = new ArrayList<>();
            dates.add(new Date(121, 0, 1));

            ArrayList<Integer> purchasedAmount = new ArrayList<>();
            purchasedAmount.add(2);
            // purchasedAmount.add(3);
            int result = book.getNumberDatesYear(dates, purchasedAmount);
            assertEquals(0, result);
    }

    @Test
    public void testGetTotalBooksBoughtMonth_SinglePurchaseThisMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(5);

        // One purchase recorded for this month, so the result should be 5
        int result = book.getTotalBooksBoughtMonth();
        assertEquals(5, result);
    }

    @Test
    public void testGetTotalBooksBoughtMonth_NoPurchases() {
        Book book = new Book("1234");
        int result = book.getTotalBooksBoughtMonth();
        assertEquals(0, result);
    }

    @Test
    public void testGetTotalBooksBoughtMonth_PurchasesInDifferentMonth() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // Subtract one month
        Date lastMonth = calendar.getTime();
        book.addPurchasedDate(lastMonth);
        book.addQuantitiesPurchased(3);

        // No purchases recorded for this month, so the result should be 0
        int result = book.getTotalBooksBoughtMonth();
        assertEquals(0, result);
    }

    @Test
    public void testGetTotalBooksBought_SinglePurchase() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(5);

        int result = book.getTotalBooksBought();
        assertEquals(5, result);
    }

    @Test
    public void testGetTotalBooksBought_MultiplePurchases() {
        Book book = new Book("1234");
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(3);
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(2);

        // Total purchases recorded, so the result should be the sum of quantities
        int result = book.getTotalBooksBought();
        assertEquals(5, result);
    }

    @Test
    public void testGetTotalBooksBought_NoPurchases() {
        Book book = new Book("1234");
        int result = book.getTotalBooksBought();
        assertEquals(0, result);
    }

}
