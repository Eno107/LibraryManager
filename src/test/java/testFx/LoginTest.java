package testFx;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class LoginTest extends ApplicationTest {

    FxRobot robot = new FxRobot();

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_manager_correct_credentials() {
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");

        verifyThat("#managerTable", isVisible());
        assertEquals("Welcome TestManager",((Text) lookup("#textHeaderManager").query()).getText());
    }

    @Test
    public void test_librarian_correct_credentials() {
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("1");
        robot.clickOn("#submit");

        verifyThat("#comboBoxLibrarian", isVisible());
        assertEquals("Welcome TestLibrarian",((Text) lookup("#testHeaderLibrarian").query()).getText());
    }

    @Test
    public void test_administrator_correct_credentials(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("3");
        robot.clickOn("#submit");

        verifyThat("#bttManageLibrarians", isVisible());
    }

    @Test
    public void test_wrong_credentials() {
        robot.clickOn("#mainPageUsername").write("2");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");

        verifyThat("#mainPageUsername", isVisible());
        assertEquals("Wrong Information",((TextField) lookup("#mainLoginWarning").query()).getText());
    }

    @Test
    public void test_empty_credentials() {
        robot.clickOn("#mainPageUsername").write("");
        robot.clickOn("#password").write("");
        robot.clickOn("#submit");

        verifyThat("#mainPageUsername", isVisible());
        assertEquals("Empty Fields",((TextField) lookup("#mainLoginWarning").query()).getText());
    }
}
