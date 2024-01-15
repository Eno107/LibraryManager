package testFx.Manager;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.BillNumber;
import project.librarymanager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ManagerAddStockTest extends ApplicationTest {

    FxRobot robot = new FxRobot();
    BillNumber billNumber = new BillNumber();

    private void logToMag_Supply_Add_Stock(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");
        robot.clickOn("#supply");
        robot.clickOn("#bttAddStock");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_add_stock_book_categories() {
        logToMag_Supply_Add_Stock();
        GridPane grid = lookup("#categoryGrid").query();
        int expectedNodeCount = billNumber.getCategories("Books.txt").size();
        verifyThat("#categoryGrid", isVisible());
        verifyThat(grid, (GridPane g) -> g.getChildren().size() == expectedNodeCount);
    }

    @Test
    public void test_show_correct_books() {
        logToMag_Supply_Add_Stock();
        robot.clickOn("#bttAddBookToCategory");
        lookup("#bttAddBookToCategory").queryButton().getText();
        GridPane grid = lookup("#categoryStockBooksGrid").query();
        verifyThat("#categoryStockBooksGrid", isVisible());
        String text = lookup("#bttAddBookToCategory").queryButton().getText();

        int expectedNodeCount = billNumber.getBookFromCategory("Books.txt", text).size();
        verifyThat(grid, (GridPane g) -> g.getChildren().size() == expectedNodeCount+1);
    }

    @Test
    public void test_add_stock_book_from_category(){
        logToMag_Supply_Add_Stock();
        robot.clickOn("#bttAddBookToCategory");
        robot.clickOn("#bttStockBook");
        robot.clickOn("#bttQuantityStock").write("2");
        robot.clickOn("#bttAddBookToStock");
        assertEquals("Added",((TextField) lookup("#bttStockBookAdded").query()).getText());
    }

    @Test
    public void test_add_stock_new_book_correct_credentials(){
        logToMag_Supply_Add_Stock();
        robot.clickOn("#bttAddBookToCategory");
        robot.clickOn("#bttAddNewBook");

        TextField bookISBNField = lookup("#bookISBN").query();
        TextField titleField = lookup("#bookTitle").query();
        GridPane gridSupplier = lookup("#bookSupplier").query();
        TextField originalPrice = lookup("#bookOGPrice").query();
        TextField sellingPrice = lookup("#bookSellPrice").query();
        TextField authorField = lookup("#bookAuthor").query();
        TextField quantityField = lookup("#bookQuantity").query();


        robot.clickOn(bookISBNField).write("1234567893628");
        robot.clickOn(titleField).write("hello");
        robot.clickOn(gridSupplier.getChildren().get(1));
        robot.clickOn(originalPrice).write("12");
        robot.clickOn(sellingPrice).write("20");
        robot.clickOn(authorField).write("Someone");
        robot.clickOn(quantityField).write("10");

        robot.clickOn("#bttAddBook");

        assertEquals("Added",((TextField) lookup("#addBookWarning").query()).getText());
    }

    @Test
    public void test_add_stock_new_book_wrong_credentials(){
        logToMag_Supply_Add_Stock();
        robot.clickOn("#bttAddBookToCategory");
        robot.clickOn("#bttAddNewBook");

        TextField bookISBNField = lookup("#bookISBN").query();
        TextField titleField = lookup("#bookTitle").query();
        GridPane gridSupplier = lookup("#bookSupplier").query();
        TextField originalPrice = lookup("#bookOGPrice").query();
        TextField sellingPrice = lookup("#bookSellPrice").query();
        TextField authorField = lookup("#bookAuthor").query();
        TextField quantityField = lookup("#bookQuantity").query();


        robot.clickOn(bookISBNField).write("123");
        robot.clickOn(titleField).write("hello");
        robot.clickOn(gridSupplier.getChildren().get(1));
        robot.clickOn(originalPrice).write("12");
        robot.clickOn(sellingPrice).write("20");
        robot.clickOn(authorField).write("Someone");
        robot.clickOn(quantityField).write("10");

        robot.clickOn("#bttAddBook");

        assertEquals("Failed, Invalid ISBN",((TextField) lookup("#addBookWarning").query()).getText());
    }

    @Test
    public  void test_add_stock_new_book_empty_credentials(){
        logToMag_Supply_Add_Stock();
        robot.clickOn("#bttAddBookToCategory");
        robot.clickOn("#bttAddNewBook");


        TextField titleField = lookup("#bookTitle").query();
        GridPane gridSupplier = lookup("#bookSupplier").query();
        TextField originalPrice = lookup("#bookOGPrice").query();
        TextField sellingPrice = lookup("#bookSellPrice").query();
        TextField authorField = lookup("#bookAuthor").query();
        TextField quantityField = lookup("#bookQuantity").query();


        robot.clickOn(titleField).write("hello");
        robot.clickOn(gridSupplier.getChildren().get(1));
        robot.clickOn(originalPrice).write("12");
        robot.clickOn(sellingPrice).write("20");
        robot.clickOn(authorField).write("Someone");
        robot.clickOn(quantityField).write("10");

        robot.clickOn("#bttAddBook");

        assertEquals("Failed, Empty Fields",((TextField) lookup("#addBookWarning").query()).getText());
    }

}
