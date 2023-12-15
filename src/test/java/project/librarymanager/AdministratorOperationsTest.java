package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorOperationsTest {

    @BeforeAll
    public static void setUp(){
        Administrator.InstantiateAdmins();
    }

    // Start of method testing "Administrator.checker()" {
    @Test
    public void test_checker_isAdmin(){
        String password = "&zsX6QVZ";
        String username = "J0sh";
        assertTrue(Administrator.checker(username, password));
    }

    @Test
    public void test_checker_notAdmin_wrongPass(){
        String password = "failedPass";
        String username = "J0sh";
        assertFalse(Administrator.checker(username, password));
    }

    @Test
    public void test_checker_notAdmin_wrongUsername(){
        String username = "failedUsername";
        String password = "&zsX6QVZ";
        assertFalse(Administrator.checker(username, password));
    }
    // } end of "Administrator.checker()" testing

    //Start of testing method for "Administrator.getSalaries()"{
    @Test
    public void testGetSalaries() {
        Administrator.getManagers().clear();
        Administrator.getAdmins().clear();

        Administrator admin1 = new Administrator("J0sh","&zsX6QVZ","Josh",1500,"(912) 561-2328","josh@administrator.com") ;
        Manager manager1 = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        Manager manager2 = new Manager("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com");
        Manager manager3 = new Manager("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        Librarian librarian1 = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        Librarian librarian2 = new Librarian("Julie?!","NDt8f6xL","Julie",500,"(912) 742-7832","julie@librarian.com");

        Administrator.getAdmins().add(admin1);
        Administrator.getManagers().add(manager1);
        Administrator.getManagers().add(manager2);
        Administrator.getManagers().add(manager3);
        Manager.AddLibrarian(librarian1);
        Manager.AddLibrarian(librarian2);

        double expectedSalaries = 1500 + (900 * 3) + (500 * 2);
        double actualSalaries = Administrator.getSalaries();
        assertEquals(expectedSalaries, actualSalaries);
    }
    // } end of testing method for "Administrator.getSalaries()"

    //Start of testing method for "Administrator.getAdmins()"{
    @Test
    public void testGetAdmins() {
        Administrator.getManagers().clear();
        Administrator.getAdmins().clear();

        Administrator.InstantiateAdmins();
        Administrator.InstantiateManagers();

        assertEquals(2, Administrator.getAdmins().size());

        Administrator admin1 = new Administrator("NewAdmin1", "Pass1", "Admin1", 1500, "(111) 111-1111", "admin1@administrator.com");
        Administrator admin2 = new Administrator("NewAdmin2", "Pass2", "Admin2", 1500, "(222) 222-2222", "admin2@administrator.com");

        Administrator.getAdmins().add(admin1);
        Administrator.getAdmins().add(admin2);

        assertEquals(4, Administrator.getAdmins().size());
        assertTrue(Administrator.getAdmins().contains(admin1));
        assertTrue(Administrator.getAdmins().contains(admin2));
    }
    // } end of testing method for "Administrator.getAdmins()"

    //Start of testing method for "Administrator.addManager()"{
    @Test
    public void testAddManager() {
        Administrator.getAdmins().clear();
        Administrator.getManagers().clear();

        Administrator.InstantiateManagers();
        Administrator.InstantiateAdmins();

        assertEquals(3, Administrator.getManagers().size());

        Manager newManager = new Manager("NewManager", "Pass", "Manager", 1200, "(333) 333-3333", "manager@test.com");
        Administrator.AddManager(newManager);

        assertEquals(4, Administrator.getManagers().size());
        assertTrue(Administrator.getManagers().contains(newManager));
    }
    // } end of testing method for "Administrator.addManager()"

    //Start of testing method for "Administrator.getBackManager()"{
    @Test
    public void testGetBackManager() {
        Administrator.getAdmins().clear();
        Manager.getLibrarians().clear();

        Administrator.InstantiateManagers();
        Administrator.InstantiateAdmins();
        Manager.InstantiateLibrarians();

        Manager existingManager = Administrator.getManagers().get(0);
        Manager retrievedManager = Administrator.getBackManager(existingManager);

        assertNotNull(retrievedManager);
        assertEquals(existingManager.getUsername(), retrievedManager.getUsername());
        assertEquals(existingManager.getPassword(), retrievedManager.getPassword());
        assertEquals(existingManager.getName(), retrievedManager.getName());

        Manager nonExistingManager = new Manager("NonExisting", "Pass", "NonExisting", 1200, "(333) 333-3333", "nonexisting@test.com");

        Manager retrievedManager2 = Administrator.getBackManager(nonExistingManager);
        assertNull(retrievedManager2);
    }
    // } end of testing method for "Administrator.getBackManager()"

    //Start of testing method for "Administrator.updateManager()"{
    @Test
    public void testUpdateManager() {
        Administrator.getAdmins().clear();

        Administrator.InstantiateManagers();
        Administrator.InstantiateAdmins();

        Manager existingManager = Administrator.getManagers().get(0);

        existingManager.setName("UpdatedName");
        existingManager.setSalary(1500);

        Administrator.updateManagers(existingManager);

        Manager updatedManager = Administrator.getManagers().get(0);
        assertEquals("UpdatedName", updatedManager.getName());

        Manager nonExistingManager = new Manager("NonExisting", "Pass", "NonExisting", 1200, "(333) 333-3333", "nonexisting@test.com");
        Administrator.updateManagers(nonExistingManager);

        Manager updatedManager2 = Administrator.getBackManager(nonExistingManager);
        assertNull(updatedManager2);
    }
    // } end of testing method for "Administrator.updateManager()"

//Start of testing method Administrator.partOfAdminList()
    @Test
    public void testAdminPartOfList() {
        Administrator.InstantiateAdmins();
        ArrayList<Administrator> admins = Administrator.getAdmins();
        for (Administrator admin : admins) {
            assertTrue(partOfAdminList(admin), "Admin not found in the administrators list.");
        }
    }

    private boolean partOfAdminList(Administrator admin) {
        ArrayList<Administrator> admins = Administrator.getAdmins();
        for (Administrator existingAdmin : admins) {
            if (existingAdmin.getUsername().equals(admin.getUsername())) {
                return true;
            }
        }
        return false;
    }
// end of testing method Administrator.partOfList()
   //Start of testing method Administrator.checkEmail();
    @Test
    public void testValidEmail() {
        Administrator.InstantiateAdmins();
        ArrayList<Administrator> admins = Administrator.getAdmins();
        for (Administrator admin : admins) {
            assertTrue(Administrator.checkEmail(admin.getEmail()), "Invalid email for an admin.");
        }
    }
// end of testing method Administrator.checkEmail();
//Start of testing method Administrator.checkSalary()
    @Test
    public void testTotalSalariesCalculation() {
        Administrator.InstantiateAdmins();
        double totalSalaries = calculateTotalSalaries();
        assertEquals(8000, totalSalaries);
    }

    private double calculateTotalSalaries() {
        double totalSalaries = 0;

        ArrayList<Librarian> librarians = Manager.getLibrarians();
        for (Librarian librarian : librarians) {
            totalSalaries += librarian.getSalary();
        }

        ArrayList<Administrator> admins = Administrator.getAdmins();
        for (Administrator admin : admins) {
            totalSalaries += admin.getSalary();
        }
        return totalSalaries;
    }
//end of testing method Administrator.checkSalary();
// Start of testing method Administrator.reEnter()
    @Test
    public void testReEnterExistingManager() {
        Manager existingManager = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        Administrator.AddManager(existingManager);

        Manager reEnteredManager = Administrator.reEnter(existingManager);
      assertEquals(existingManager, reEnteredManager);
    }

    @Test //no manager at all
    public void testReEnterNonExistingManager() {
        Manager nonExistingManager = new Manager("NON_EXISTING_USERNAME", "NonExistentPassword");
        Manager reEnteredManager = Administrator.reEnter(nonExistingManager);
       assertNull(reEnteredManager);
    }
    //end of testing method Administrator.reEnter();

//Start of testing method Administrator.ManagerChecker()
    @Test //no issues
    public void testManagerCheckerWithExistingManager() {
        Manager existingManager = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        Administrator.AddManager(existingManager);
        assertTrue(Administrator.ManagerChecker(existingManager),
                "ManagerChecker failed for an existing manager with correct username and password");
    }

    @Test //NO PASS
    public void testManagerCheckerWithNonExistingManager() {
        Manager nonExistingManager = new Manager( "idkbro", "NON_EXISTING_PASSWORD");
        assertFalse(Administrator.ManagerChecker(nonExistingManager));
    }

    @Test
    public void testManagerCheckerWithIncorrectPassword() {
        Manager existingManager = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        Administrator.AddManager(existingManager);
        Manager mag = new Manager("Calv1n","i23","Calvin",900,"(912) 561-2628","calvl@manager.com") ;

        assertFalse(Boolean.parseBoolean(existingManager.getPassword()), mag.getPassword());
    }
//end of testing method Administrator.Manager checker()

//Start of testing method Administrator.deleteManager()
    @Test // normal scenario
    public void testDeleteManager() {
        Manager existingManager = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        Administrator.deleteManager(existingManager);
        assertFalse(Administrator.partOfManager(existingManager));
    }

    @Test //attempting to delete something that doesn't exist
    public void testDeleteNonExistingManager() {
        Manager existingManager = new Manager("test","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;

        Administrator.deleteManager(existingManager);

        assertFalse(Administrator.partOfManager(existingManager),
                "DeleteManager incorrectly removed a non-existing manager from the list");
    }
// end of testing method Administrator.deleteManager()

}
