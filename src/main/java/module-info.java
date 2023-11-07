module project.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens project.librarymanager to javafx.fxml;
    exports project.librarymanager;
}