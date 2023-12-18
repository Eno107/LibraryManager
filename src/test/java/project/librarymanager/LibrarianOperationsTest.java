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


    // Start of method testing "Manager.partOfLibrarian() {
    @Test
    public void test_partOfLibrarian_notPart() {
        Librarian lib = new Librarian("WRONG_USERNAME","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        assertFalse(Manager.partOfLibrarian(lib), "Librarian object with incorrect username is passed");
    }

    @Test
    public void test_partOfLibrarian_isPart() {
        Librarian lib = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        assertTrue(Manager.partOfLibrarian(lib), "Librarian object with correct username is not passed");
    }
    // } end of "Manager.partOfLibrarian()" testing


    // Start of method testing "Manager.getBackLibrarian()" {
    @Test
    public void test_getBackLibrarian_isPart() {
        Librarian lib = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        Librarian gottenBackLibrarian = Manager.getBackLibrarian(lib);

        assertNotNull(gottenBackLibrarian, "re-entered librarian is null");

        assertEquals(lib.getUsername(), gottenBackLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(lib.getPassword(), gottenBackLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(lib.getName(), gottenBackLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(lib.getSalary(), gottenBackLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(lib.getPhone(), gottenBackLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(lib.getEmail(), gottenBackLibrarian.getEmail(), "The following attribute is incorrect: email");
    }

    @Test
    public void test_getBackLibrarian_notPart(){
        Librarian lib = new Librarian("WRONG_USERNAME","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        Librarian gottenBackLibrarian = Manager.getBackLibrarian(lib);

        assertNull(gottenBackLibrarian, "re-entered librarian is not null");
    }
    // } end of "Manager.getBackLibrarian()" testing


    // Start of method testing for "Manager.LibrarianChecker()" {
    @Test
    public void testLibrarianChecker_isLibrarian() {
        Librarian librarian = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        assertTrue(Manager.LibrarianChecker(librarian), "LibrarianChecker failed for an existing Librarian");
    }

    @Test
    public void test_LibrarianChecker_notLibrarian_wrongUsername() {
        Librarian librarian = new Librarian("NonExistingUser", "SSU6aldo", "NonExistingName", 0, "0000000000", "nonexisting@librarian.com");
        assertFalse(Manager.LibrarianChecker(librarian),
                "LibrarianChecker failed for a non-existing Librarian with a wrong username and a correct password");
    }

    @Test
    public void test_LibrarianChecker_notLibrarian_wrongPass() {
        Librarian librarian = new Librarian("Alfie123", "idkBro");
        assertFalse(Manager.LibrarianChecker(librarian),
                "LibrarianChecker failed for a non-existing Manager with a correct username and a wrong password");

    }
    // } end of "Manager.LibrarianChecker()" testing


    // Start of method testing for "Manager.deleteLibrarian()" {
    @Test
    public void test_deleteLibrarian_validLibrarian() {

        Librarian librarianToDelete = new Librarian("TO_BE_DELETED", "TO_BE_DELETED");
        Manager.AddLibrarian(librarianToDelete);

        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete);

        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize - 1, finalSize, "Size problem after deletion");
        assertNull(Manager.getBackLibrarian(librarianToDelete),
                "'librarianToDelete' is still part of librarian array");
    }

    @Test
    public void test_deleteLibrarian_nonExistingLibrarian() {

        Librarian librarianToDelete = new Librarian("TO_BE_DELETED", "TO_BE_DELETED");

        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete)
        ;
        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize, finalSize);
        assertNull(Manager.getBackLibrarian(librarianToDelete),
                "'librarianToDelete' is part of librarian array");
    }
    // } end of "Manager.deleteLibrarian()" testing


    // Start of method testing for "Manager.reEnter()" {
    @Test
    public void test_reEnter_ExistingLibrarian() {
        Librarian existingLibrarian = new Librarian("@Leo", "TyFzN8we", "Leo", 500, "(912) 152-7493", "leo@librarian.com");
        Librarian reEnteredLibrarian = Manager.reEnter(existingLibrarian);

        assertNotNull(reEnteredLibrarian, "re-entered Librarian is null");

        assertEquals(existingLibrarian.getUsername(), reEnteredLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(existingLibrarian.getPassword(), reEnteredLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(existingLibrarian.getName(), reEnteredLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(existingLibrarian.getSalary(), reEnteredLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(existingLibrarian.getPhone(), reEnteredLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(existingLibrarian.getEmail(), reEnteredLibrarian.getEmail(), "The following attribute is incorrect: email");
    }

    @Test
    public void test_reEnter_nonExistingLibrarian() {
        Librarian nonExistingLibrarian = new Librarian("NonExistingUser", "NonExistingPassword");
        Librarian reEnteredLibrarian = Manager.reEnter(nonExistingLibrarian);

        assertNull(reEnteredLibrarian, "re-entered Librarian is not null");
    }
    // } end of "Manager.reEnter()" testing


    // Start of method testing for "Manager.updateLibrarians()" {
    @Test
    public void testUpdateExistingLibrarian() {
        Librarian existingLibrarian = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        existingLibrarian.setEmail("new_email@librarian.com");
        existingLibrarian.setSalary(600);
        Manager.updateLibrarians(existingLibrarian);
        Librarian updatedLibrarian = Manager.getBackLibrarian(existingLibrarian);

        assertNotNull(updatedLibrarian,"updated Librarian object is null");

        assertEquals(existingLibrarian.getUsername(), updatedLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(existingLibrarian.getPassword(), updatedLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(existingLibrarian.getName(), updatedLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(existingLibrarian.getSalary(), updatedLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(existingLibrarian.getPhone(), updatedLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(existingLibrarian.getEmail(), updatedLibrarian.getEmail(), "The following attribute is incorrect: email");

        Manager.getLibrarians().clear();
        Manager.InstantiateLibrarians();
    }

    @Test
    public void testUpdateNonExistingLibrarian() {
        Librarian nonExistingLibrarian = new Librarian("NonExistingUser", "NonExistingPassword");
        nonExistingLibrarian.setEmail("new_email@librarian.com");

        ArrayList<Librarian> preUpdateList = Manager.getLibrarians();
        Manager.updateLibrarians(nonExistingLibrarian);
        ArrayList<Librarian> postUpdateList = Manager.getLibrarians();

        Librarian retrievedLibrarian = Manager.getBackLibrarian(nonExistingLibrarian);
        assertNull(retrievedLibrarian, "retrieved Librarian object is not null");

        assertEquals(preUpdateList.size(), postUpdateList.size(),
                "Size problem after update");

        for (int i=0; i<preUpdateList.size(); i++){
            assertEquals(preUpdateList.get(i).getUsername(), postUpdateList.get(i).getUsername(), "The following attribute is changed: username");
            assertEquals(preUpdateList.get(i).getPassword(), postUpdateList.get(i).getPassword(), "The following attribute is changed: password");
            assertEquals(preUpdateList.get(i).getName(), postUpdateList.get(i).getName(), "The following attribute is changed: name");
            assertEquals(preUpdateList.get(i).getSalary(), postUpdateList.get(i).getSalary(), "The following attribute is changed: salary");
            assertEquals(preUpdateList.get(i).getPhone(), postUpdateList.get(i).getPhone(), "The following attribute is changed: phone");
            assertEquals(preUpdateList.get(i).getEmail(), postUpdateList.get(i).getEmail(), "The following attribute is changed: email");
        }
    }
    // } end of "Manager.updateLibrarians()" testing
}

