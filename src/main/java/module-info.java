module com.example.tictactoegama {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.tictactoegama to javafx.fxml;
    exports com.example.tictactoegama;
}