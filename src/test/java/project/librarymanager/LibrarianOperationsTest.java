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

}
