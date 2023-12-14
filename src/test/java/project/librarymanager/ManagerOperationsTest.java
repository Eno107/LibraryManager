package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerOperationsTest {

    @BeforeAll
    public static void setUp(){
        Administrator.InstantiateManagers();
    }

    @Test
    public void test_instantiateManagers(){
        ArrayList<Manager> expectedManagerArrayList = new ArrayList<>();
        Manager mag = new Manager("Calv1n","PQ532Abba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
        expectedManagerArrayList.add(mag);
        mag = new Manager("Lui54","y@.3FYrn","Lui",900,"(912) 218-2594","lu@manager.com") ;
        expectedManagerArrayList.add(mag);
        mag = new Manager("1","2","TestManager",900,"(912) 623-5353","TestEmail@librarian.com");
        expectedManagerArrayList.add(mag);

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

    // Start of method testing "Administrator.partOfManager()" {
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
    // } end of "Administrator.partOfManager()" testing


    // Start of method testing "Manager.getAllCategories()" {
    @Test
    public void test_getAllCategories(){
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Modernist");
        expected.add("Fiction");
        expected.add("Novel");
        expected.add("Magic Realism");
        expected.add("Tragedy");
        expected.add("Adventure Fiction");
        expected.add("Historical Novel");
        expected.add("Epic");
        expected.add("War");
        expected.add("Autobiography and memoir");
        expected.add("Biography");
        expected.add("Non-fiction novel");
        expected.add("Self-help");
        expected.add("Short stories");
        expected.add("Horror");
        expected.add("Mystery");
        expected.add("Romance");
        expected.add("Thriller");

        ArrayList<String> allCategoriesList = Manager.getAllCategories();
        for (int i=0; i<allCategoriesList.size(); i++){
            assertEquals(expected.get(i), allCategoriesList.get(i));
        }
    }
    // end of "Manager.getAllCategories()" testing

    //Start of testing method for "Manager.LibrarianChecker()"{
    @Test
    public void testLibrarianChecker_ValidCredentials() {
        Manager.InstantiateLibrarians();
        Librarian librarian = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        assertTrue(Manager.LibrarianChecker(librarian));
    }

    @Test
    public void testLibrarianChecker_InvalidLibrarian() {
        Librarian librarian = new Librarian("NonExistingUser", "NonExistingPassword", "NonExistingName", 0, "0000000000", "nonexisting@librarian.com");
        assertFalse(Manager.LibrarianChecker(librarian));
    }

    @Test
    public void testLibrarianChecker_NullLibrarian() {
        Librarian librarian = null;
        assertFalse(Manager.LibrarianChecker(librarian));
    }
    // } end of testing methods for "Manager.LibrarianChecker()"

    //Start of testing method for "Manager.deleteLibrarian()"{
    @Test
    public void testDeleteLibrarian_ValidLibrarian() {
        Librarian librarianToDelete = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        Manager.AddLibrarian(librarianToDelete);
        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete);
        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize - 1, finalSize);
    }

    @Test
    public void testDeleteLibrarian_NonExistingLibrarian() {
        Librarian librarianToDelete = new Librarian("NonExistingUser", "NonExistingPassword", "NonExistingName", 0, "0000000000", "nonexisting@librarian.com");
        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete);
        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize, finalSize);
    }
    // } end of testing method for "Manager.deleteLibrarian()"
}
