package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerOperationsTest {

    @BeforeAll
    public static void setUp() {
        Administrator.InstantiateManagers();
    }


    @Test
    public void test_instantiateManagers() {
        ArrayList<Manager> expectedManagerArrayList = new ArrayList<>();
        Manager mag = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        expectedManagerArrayList.add(mag);
        mag = new Manager("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com");
        expectedManagerArrayList.add(mag);
        mag = new Manager("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        expectedManagerArrayList.add(mag);

        ArrayList<Manager> managerArrayList = Administrator.getManagers();

        assertEquals(expectedManagerArrayList.size(), managerArrayList.size(), "size");
        for (int i = 0; i < managerArrayList.size(); i++) {
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
    public void test_partOfManager_notPart() {
        Manager mag = new Manager("2", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        assertFalse(Administrator.partOfManager(mag), "Manager object with incorrect username is passed");
    }

    @Test
    public void test_partOfManager_isPart() {
        Manager mag = new Manager("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com");
        assertTrue(Administrator.partOfManager(mag), "Manager object with correct username is not passed");
    }
    // } end of "Administrator.partOfManager()" testing


    // Start of method testing for "Administrator.reEnter()" {
    @Test
    public void testReEnterExistingManager() {
        Manager existingManager = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        Manager reEnteredManager = Administrator.reEnter(existingManager);

        assertNotNull(reEnteredManager, "re-entered Manager is null");

        assertEquals(existingManager.getUsername(), reEnteredManager.getUsername(),  "The following attribute is incorrect: username");
        assertEquals(existingManager.getPassword(), reEnteredManager.getPassword(),  "The following attribute is incorrect: password");
        assertEquals(existingManager.getName(), reEnteredManager.getName(),  "The following attribute is incorrect: name");
        assertEquals(existingManager.getSalary(), reEnteredManager.getSalary(),  "The following attribute is incorrect: salary");
        assertEquals(existingManager.getPhone(), reEnteredManager.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(existingManager.getEmail(), reEnteredManager.getEmail(), "The following attribute is incorrect: mail");
    }

    @Test
    public void testReEnterNonExistingManager() {
        Manager nonExistingManager = new Manager("NON_EXISTING_USERNAME", "NonExistentPassword");
        Manager reEnteredManager = Administrator.reEnter(nonExistingManager);
        assertNull(reEnteredManager);
    }
    // } end of "Administrator.reEnter()" testing


    // Start of method testing "Administrator.ManagerChecker()" {
    @Test
    public void test_ManagerChecker_isManager() {
        String username = "Calv1n";
        String password = "PQ532Abba";
        Manager existingManager = new Manager(username, password);

        assertTrue(Administrator.ManagerChecker(existingManager),
                "ManagerChecker failed for an existing Manager");

    }
    @Test
    public void test_ManagerChecker_notManager_wrongUsername() {
        String username = "idkBro";
        String password = "PQ532Abba";
        Manager nonExistingManager = new Manager(username, password);
        assertFalse(Administrator.ManagerChecker(nonExistingManager),
                "ManagerChecker failed for a non-existing Manager with a wrong username and a correct password");
    }

    @Test
    public void test_ManagerChecker_notManager_wrongPassword() {
        String username = "Calv1n";
        String password = "idkBro";
        Manager nonExistingManager = new Manager(username, password);
        assertFalse(Administrator.ManagerChecker(nonExistingManager),
                "ManagerChecker failed for a non-existing Manager with a correct username and a wrong password");
    }
    // } end of "Administrator.ManagerChecker()" testing


    // Start of method testing for "Administrator.deleteManager()" {
    @Test
    public void testDeleteManager() {
        Manager existingManager = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        assertTrue(Administrator.deleteManager(existingManager));

        Administrator.getManagers().clear();
        Administrator.InstantiateManagers();
    }

    @Test
    public void testDeleteNonExistingManager() {
        Manager nonExistingManager = new Manager("test",  "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        assertFalse(Administrator.deleteManager(nonExistingManager),
                 "DeleteManager incorrectly removed a non-existing manager from the list");
    }
    // } end of "Administrator.deleteManager()" testing


    // Start of method testing for "Manager.getAllCategories()" {
    @Test
    public void test_getAllCategories() {
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
        for (int i = 0; i < allCategoriesList.size(); i++) {
            assertEquals(expected.get(i), allCategoriesList.get(i));
        }
    }
    // end of "Manager.getAllCategories()" testing


    // Start of method testing for "Administrator.addManager()" {
    @Test
    public void testAddManager() {
        assertEquals(3, Administrator.getManagers().size());

        Manager newManager = new Manager("NewManager", "Pass", "Manager", 1200, "(333) 333-3333", "manager@test.com");
        Administrator.AddManager(newManager);

        assertEquals(4, Administrator.getManagers().size(), "Managers list is not updated");
        assertTrue(Administrator.getManagers().contains(newManager), "Managers list does not contain 'newManager'");

        Administrator.deleteManager(newManager);
    }
    // } end of testing method for "Administrator.addManager()"


    // Start of method testing for "Administrator.getBackManager()" {
    @Test
    public void testGetBackManager() {

        Manager existingManager = Administrator.getManagers().get(0);
        Manager retrievedManager = Administrator.getBackManager(existingManager);

        assertNotNull(retrievedManager, "Retrieved manager is null");
        assertEquals(existingManager.getUsername(), retrievedManager.getUsername(), "Retrieved manager's attributes are not the same");
        assertEquals(existingManager.getPassword(), retrievedManager.getPassword(), "Retrieved manager's attributes are not the same");
        assertEquals(existingManager.getName(), retrievedManager.getName(), "Retrieved manager's attributes are not the same");

        Manager nonExistingManager = new Manager("NonExisting", "Pass", "NonExisting", 1200, "(333) 333-3333", "nonexisting@test.com");

        Manager retrievedManager2 = Administrator.getBackManager(nonExistingManager);
        assertNull(retrievedManager2, "Non existent manager is not null");
    }
    // } end of "Administrator.getBackManager()" testing


    //Start of method testing for "Administrator.updateManager()" {
    @Test
    public void testUpdateManager() {
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
    // } end of "Administrator.updateManager()" testing
}
