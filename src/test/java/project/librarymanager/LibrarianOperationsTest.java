package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
//
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianOperationsTest {
    @BeforeAll
    public static void setUp(){
        Manager.InstantiateLibrarians();
    }


    //Start of testing method Librarian.MoneyMadeInDay()
    @Test
    public void testMoneyMadeInDay() {
        Librarian lib = new Librarian("1","1","TestLibrarian",500,"(912) 632-6353","TestEmail@librarian.com");
        ArrayList<Date> DatesSold = new ArrayList<>();
        ArrayList<Double> mockMoneyMadeDates = new ArrayList<>();
        DatesSold.add(new Date());
        mockMoneyMadeDates.add(50.0);
        lib.datesSold.addAll(DatesSold);
        lib.moneyMadeDates.addAll(mockMoneyMadeDates);
        double moneyMadeInDay = lib.moneyMadeInDay();
        assertEquals(50.0, moneyMadeInDay);

    }
//end of testing method MoneyMadeDay()

    //Start testing method "Librarian.CheckPassword"
    @Test
    public void testCheckPasswordValid() {
        Librarian lib = new Librarian("@Leo","TyFzN8we","Leo",500,"(912) 152-7493","leo@librarian.com");
        String pass = lib.getPassword();
      assertTrue(Librarian.checkPassword(pass));
    }
    @Test
    public void testCheckPasswordInvalidShort() {
        assertFalse( Librarian.checkPassword("Short"));
    }
//end testing method "Librarian.CheckPassword"

    //Start of testing method "CheckSalaryValid()"
    @Test
    public void testCheckSalaryValid() {
        assertTrue(Librarian.checkSalary("50000.50"));
    }

    @Test
    public void testCheckSalarychanged() {
        Librarian lib = new Librarian("@Leo","TyFzN8we","Leo",500,"(912) 152-7493","leo@librarian.com");
        lib.setSalary(770);
        String salary= Double.toString(lib.getSalary());
        assertTrue(Librarian.checkSalary(salary));
    }
//end testing method "Librarian.CheckSalaryChanged"


    // Start of testing method for "Librarian.MoneyMadeInMonth()"{
    @Test
    public void testMoneyMadeInMonth_NoSales() {
        Librarian librarian = new Librarian("username", "password");
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_SalesInCurrentMonth() {
        Librarian librarian = new Librarian("username", "password");
        Date today = new Date();
        librarian.datesSold.add(today);
        librarian.moneyMadeDates.add(50.0);
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertNotEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_SalesOutsideCurrentMonth() {
        Librarian librarian = new Librarian("username", "password");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date previousMonth = calendar.getTime();
        librarian.datesSold.add(previousMonth);
        librarian.moneyMadeDates.add(100.0);
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_SalesCurrentMonthBoundary() {
        Librarian librarian = new Librarian("username", "password");
        Calendar calendar = Calendar.getInstance();
        Date firstDayOfMonth = new Date(calendar.get(Calendar.YEAR) - 1900, calendar.get(Calendar.MONTH), 1);
        librarian.datesSold.add(firstDayOfMonth);
        librarian.moneyMadeDates.add(150.0);
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertNotEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_SalesDifferentYear() {
        Librarian librarian = new Librarian("username", "password");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        Date previousYear = calendar.getTime();
        librarian.datesSold.add(previousYear);
        librarian.moneyMadeDates.add(200.0);
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_NullDates() {
        Librarian librarian = new Librarian("username", "password");
        librarian.datesSold.clear();
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_MultipleSalesInCurrentMonth() {
        Librarian librarian = new Librarian("username", "password");
        Date today = new Date();
        librarian.datesSold.add(today);
        librarian.moneyMadeDates.add(100.0);

        librarian.datesSold.add(today);
        librarian.moneyMadeDates.add(50.0);

        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertNotEquals(0, moneyMadeInMonth);
        assertEquals(150.0, moneyMadeInMonth);
    }
    // } end of testing methods for "Librarian.MoneyMadeInMonth()"

    // Start of testing method for "Librarian.checkPhone()"{
    @Test
    public void testCheckPhone_ValidPhone() {
        assertTrue(Librarian.checkPhone("(912) 123-4567"));
    }

    @Test
    public void testCheckPhone_InvalidPhone_LengthMismatch() {
        assertFalse(Librarian.checkPhone("(912) 123-456"));
    }

    @Test
    public void testCheckPhone_InvalidPhone_InvalidFormat() {
        assertFalse(Librarian.checkPhone("(912) 12-34567"));
    }

    @Test
    public void testCheckPhone_InvalidPhone_Null() {
        assertThrows(NullPointerException.class, () -> {
            Librarian.checkPhone(null);
        });
    }

    @Test
    public void testCheckPhone_InvalidPhone_Empty() {
        assertFalse(Librarian.checkPhone(""));
    }

    @Test
    public void testCheckPhone_InvalidPhone_NonNumeric() {
        assertFalse(Librarian.checkPhone("(912) ABC-DEFG"));
    }
    // } end of testing method for "Librarian.checkPhone()"

    // Start of testing method for "Librarian.checkName()"{
    @Test
    public void testCheckName_ValidName() {
        assertTrue(Librarian.checkName("John"));
    }

    @Test
    public void testCheckName_InvalidName_Null() {
        assertThrows(NullPointerException.class, () -> {
            Librarian.checkName(null);
        });
    }

    @Test
    public void testCheckName_InvalidName_EmptyString() {
        assertFalse(Librarian.checkName(""));
    }

    @Test
    public void testCheckName_InvalidName_ContainsSpecialCharacters() {
        assertFalse(Librarian.checkName("John@Doe"));
    }

    @Test
    public void testCheckName_InvalidName_ContainsNumbers() {
        assertFalse(Librarian.checkName("John123"));
    }
    // } end of testing method for "Librarian.MoneyMadeInMonth()"
}

