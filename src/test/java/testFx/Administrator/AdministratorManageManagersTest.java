package testFx.Administrator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import project.librarymanager.Administrator;
import project.librarymanager.MainFx;
import project.librarymanager.Manager;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class AdministratorManageManagersTest extends ApplicationTest {

    FxRobot robot = new FxRobot();
    private void logToAdm_Manage_Managers(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("3");
        robot.clickOn("#submit");
        robot.clickOn("#bttManageManagers");

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
    public void test_administrator_manager_list() {
        logToAdm_Manage_Managers();
        verifyThat("#administratorManagersGrid", isVisible());

    }

    @Test
    public void test_administrator_manager_list_count(){
        logToAdm_Manage_Managers();
        GridPane grid = lookup("#administratorManagersGrid").query();
        int expectedNodeCount = Administrator.getManagers().size();
        verifyThat(grid, (GridPane g) -> g.getChildren().size() == expectedNodeCount+1);
    }

    @Test
    public void test_administrator_manager_list_add_manager_wrong_credentials() {
        logToAdm_Manage_Managers();
        robot.clickOn("#bttAddNewManager");

        TextField name = lookup("#managerName").query();
        TextField password = lookup("#managerPassword").query();
        TextField username = lookup("#managerUsername").query();
        TextField salary = lookup("#managerSalary").query();
        TextField phone = lookup("#managerPhone").query();
        TextField email = lookup("#managerEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(password).write("wrong");
        robot.clickOn(salary).write("900");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddManager");

        assertEquals("Invalid password",((TextField) lookup("#addMagWarning").query()).getText());
    }


    @Test
    public void test_administrator_manager_list_add_manager_empty_credentials() {
        logToAdm_Manage_Managers();
        robot.clickOn("#bttAddNewManager");

        TextField name = lookup("#managerName").query();
        TextField password = lookup("#managerPassword").query();
        TextField username = lookup("#managerUsername").query();
        TextField salary = lookup("#managerSalary").query();
        TextField phone = lookup("#managerPhone").query();
        TextField email = lookup("#managerEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(salary).write("900");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddManager");

        assertEquals("Failed, Empty Fields!",((TextField) lookup("#addMagWarning").query()).getText());
    }

    @Test
    public void test_administrator_manager_list_add_manager_correct_credentials() {
        logToAdm_Manage_Managers();
        robot.clickOn("#bttAddNewManager");

        TextField name = lookup("#managerName").query();
        TextField password = lookup("#managerPassword").query();
        TextField username = lookup("#managerUsername").query();
        TextField salary = lookup("#managerSalary").query();
        TextField phone = lookup("#managerPhone").query();
        TextField email = lookup("#managerEmail").query();

        robot.clickOn(name).write("Jonian");
        robot.clickOn(username).write("Joni");
        robot.clickOn(password).write("12345678");
        robot.clickOn(salary).write("900");
        robot.clickOn(phone).write("(912) 698-6593");
        robot.clickOn(email).write("joni@gmail.com");

        robot.clickOn("#bttAddManager");

        assertEquals("Success!",((TextField) lookup("#addMagWarning").query()).getText());
    }
}
