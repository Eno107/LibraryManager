package testFx.Manager;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ManagerAddCategoryTest extends ApplicationTest {

    FxRobot robot = new FxRobot();
    private void logToMag_Supply_Category(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");
        robot.clickOn("#supply");
        robot.clickOn("#bttNewCategory");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_manager_category_list() {
        logToMag_Supply_Category();
        verifyThat("#categoryChoiceBox", isVisible());

    }

    @Test
    public void test_manager_add_same_category() {
        logToMag_Supply_Category();
        robot.clickOn("#categoryChoiceBox")
                .type(KeyCode.UP)
                .type(KeyCode.UP)
                .type(KeyCode.UP)
                .type(KeyCode.ENTER);
        robot.clickOn("#bttAddCategory");
        assertEquals("Failed, Not New",((TextField) lookup("#addCategoryWarning").query()).getText());
    }


    @Test
    public void test_manager_add_new_category() {
        logToMag_Supply_Category();
        robot.clickOn("#categoryChoiceBox")
                .type(KeyCode.DOWN)
                .type(KeyCode.ENTER);
        robot.clickOn("#bttAddCategory");
        assertEquals("Added!",((TextField) lookup("#addCategoryWarning").query()).getText());
    }

}
