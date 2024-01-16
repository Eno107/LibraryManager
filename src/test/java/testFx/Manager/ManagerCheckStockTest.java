package testFx.Manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;
import project.librarymanager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ManagerCheckStockTest extends ApplicationTest {

    FxRobot robot = new FxRobot();
    BillNumber billNumber = new BillNumber();

    private void logToMag_Supply_Show_Stock() {
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");
        robot.clickOn("#bttCheckStock");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_check_table() {
        logToMag_Supply_Show_Stock();
        verifyThat("#tableBooks", isVisible());
    }

    @Test
    public void test_check_table_content() {
        logToMag_Supply_Show_Stock();

        TableView<Book> table = lookup("#tableBooks").query();

        BillNumber billNumber = new BillNumber();
        ObservableList<Book> expectedData = FXCollections.observableArrayList(billNumber.getStockBooks("Books.txt"));

        assertEquals(expectedData.size(), table.getItems().size());

        for (int row = 0; row < expectedData.size(); row++) {
            for (int col = 0; col < table.getColumns().size(); col++) {
                TableColumn<Book, ?> column = table.getColumns().get(col);
                Object expectedValue = getCellValue(expectedData.get(row), column);
                assertEquals(expectedValue, column.getCellObservableValue(row).getValue());
            }
        }
    }

    private Object getCellValue(Book book, TableColumn<Book, ?> column) {
        String columnName = column.getText(); // Get the column name to determine which property to access

        return switch (columnName) {
            case "ISBN" -> book.getISBN();
            case "Title" -> book.getTitle();
            case "Category" -> book.getCategory();
            case "Author" -> book.getAuthor();
            case "Original Price" -> book.getOriginalPrice();
            case "Selling Price" -> book.getSellingPrice();
            case "Supplier" -> book.getSupplier();
            case "Stock" -> book.getStock();
            default -> throw new IllegalArgumentException("Unknown column name:" + columnName);
        };
    }


}
