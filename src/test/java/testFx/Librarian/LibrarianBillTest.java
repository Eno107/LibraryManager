package testFx.Librarian;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.BillNumber;
import project.librarymanager.MainFx;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class LibrarianBillTest extends ApplicationTest {

    FxRobot robot = new FxRobot();

    private void logToLib(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("1");
        robot.clickOn("#submit");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @Test
    public void testCreateBillEmptyCredentials() throws InterruptedException {
        this.logToLib();
        robot.clickOn("#bttBill");
        assertEquals("Failed, No Books to add",((TextField) lookup("#warningsLibrarian").query()).getText());
    }

    @Test
    public void test_incorrect_quantity() throws InterruptedException {
        this.logToLib();
        robot.clickOn("#comboBoxLibrarian")
                .type(KeyCode.DOWN) // Navigate down to the desired value
                .type(KeyCode.ENTER); // Select the value
        robot.clickOn("#quantity").write("50");
        robot.clickOn("#addBooks");
        assertEquals("Failed,not enough stock",((TextField) lookup("#warningsLibrarian").query()).getText());
    }

    @Test
    public void test_create_order() throws InterruptedException, IOException {
        this.logToLib();
        robot.clickOn("#comboBoxLibrarian")
                .type(KeyCode.DOWN)
                .type(KeyCode.ENTER);
        robot.clickOn("#quantity").write("1");
        robot.clickOn("#addBooks");

        robot.clickOn("#comboBoxLibrarian")
                .type(KeyCode.DOWN)
                .type(KeyCode.DOWN)
                .type(KeyCode.ENTER);
        robot.clickOn("#quantity").write("1");
        robot.clickOn("#addBooks");

        robot.clickOn("#bttBill");

        String billPath = "Bill" + BillNumber.billNumber + ".txt";
        File file = new File(billPath);

        assertTrue(file.exists());

        Path path = Paths.get(billPath);
        Files.delete(path);
        BillNumber.billNumber = 0;

    }




}
