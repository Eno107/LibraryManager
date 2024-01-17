package testFx.Administrator;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


public class AdministratorManageLibrariansTest extends ApplicationTest {

    FxRobot robot = new FxRobot();
    private void logToAdm_Manage_Librarians(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("3");
        robot.clickOn("#submit");
        robot.clickOn("#bttManageLibrarians");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.setWidth(465);
        primaryStage.setHeight(465);
        primaryStage.show();
    }

    @Test
    public void test_administrator_librarian_list_add_librarian_wrong_credentials() {
        logToAdm_Manage_Librarians();
        robot.clickOn("#bttAddNewLibrarian");

        TextField name = lookup("#librarianName").query();
        TextField password = lookup("#librarianPassword").query();
        TextField username = lookup("#librarianUsername").query();
        TextField salary = lookup("#librarianSalary").query();
        TextField phone = lookup("#librarianPhone").query();
        TextField email = lookup("#librarianEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(password).write("wrong");
        robot.clickOn(salary).write("500");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddLibrarian");

        assertEquals("Invalid password",((TextField) lookup("#addLibWarning").query()).getText());
    }


    @Test
    public void test_administrator_librarian_list_add_librarian_empty_credentials() {
        logToAdm_Manage_Librarians();
        robot.clickOn("#bttAddNewLibrarian");

        TextField name = lookup("#librarianName").query();
        TextField username = lookup("#librarianUsername").query();
        TextField salary = lookup("#librarianSalary").query();
        TextField phone = lookup("#librarianPhone").query();
        TextField email = lookup("#librarianEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(salary).write("900");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddLibrarian");

        assertEquals("Failed, Empty Fields!",((TextField) lookup("#addLibWarning").query()).getText());
    }

    @Test
    public void test_administrator_librarian_list_add_librarian_correct_credentials() {
        logToAdm_Manage_Librarians();
        robot.clickOn("#bttAddNewLibrarian");

        TextField name = lookup("#librarianName").query();
        TextField password = lookup("#librarianPassword").query();
        TextField username = lookup("#librarianUsername").query();
        TextField salary = lookup("#librarianSalary").query();
        TextField phone = lookup("#librarianPhone").query();
        TextField email = lookup("#librarianEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(password).write("12345678");
        robot.clickOn(salary).write("900");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddLibrarian");

        assertEquals("Success!",((TextField) lookup("#addLibWarning").query()).getText());
    }
}
