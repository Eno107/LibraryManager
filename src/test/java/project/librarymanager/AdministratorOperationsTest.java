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

}
