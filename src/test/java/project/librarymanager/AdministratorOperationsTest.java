package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class AdministratorOperationsTest {

    @BeforeAll
    public static void setUp(){
        Administrator.InstantiateAdmins();
    }



    // Start of method testing "Administrator.InstantiateAdmins()" {
    @Test
    public void test_instantiateAdmins(){
        ArrayList<Administrator> expectedAdministratorArrayList = new ArrayList<>();
        expectedAdministratorArrayList.add(new Administrator("J0sh","&zsX6QVZ","Josh",1500,"(912) 561-2328","josh@administrator.com") );
        expectedAdministratorArrayList.add(new Administrator("1","3","TestAdmin",1500,"(912) 626-5353","TestEmail@admin.com"));

        ArrayList<Administrator> administratorArrayList = Administrator.getAdmins();

        assertEquals(expectedAdministratorArrayList.size(), administratorArrayList.size(), "Instantiation of admin list has an incorrect size");

        for (int i=0; i<administratorArrayList.size(); i++){
            assertEquals(expectedAdministratorArrayList.get(i).getUsername(), administratorArrayList.get(i).getUsername());
            assertEquals(expectedAdministratorArrayList.get(i).getPassword(), administratorArrayList.get(i).getPassword());
            assertEquals(expectedAdministratorArrayList.get(i).getName(), administratorArrayList.get(i).getName());
            assertEquals(expectedAdministratorArrayList.get(i).getSalary(), administratorArrayList.get(i).getSalary());
            assertEquals(expectedAdministratorArrayList.get(i).getPhone(), administratorArrayList.get(i).getPhone());
            assertEquals(expectedAdministratorArrayList.get(i).getEmail(), administratorArrayList.get(i).getEmail());
        }
    }
    // end of "Administrator.InstantiateAdmins()" testing


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


    // Start of method testing for "Administrator.getSalaries()" {
    @Test
    public void test_getSalaries() {

        Administrator.getManagers().clear();
        Administrator.getLibrarians().clear();
        Administrator.InstantiateManagers();
        Administrator.InstantiateLibrarians();


        double managersSalary = 900*3;
        double librarianSalary = 500*5;
        double administratorSalary = 1500*2;
        double expectedSalaries = managersSalary + librarianSalary + administratorSalary;

        assertEquals(expectedSalaries, Administrator.getSalaries());
    }
    // } end of "Administrator.getSalaries()" testing


    // Start of method testing for "Administrator.getAdmins()" {
    @Test
    public void test_getAdmins() {
        assertEquals(2, Administrator.getAdmins().size(), "Incorrect size");
    }
    // } end of "Administrator.getAdmins()" testing

}
