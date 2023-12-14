package project.librarymanager;

import org.junit.jupiter.api.BeforeAll;

public class LibrarianOperationsTest {

    @BeforeAll
    public static void setUp(){
        Manager.InstantiateLibrarians();
    }
}
