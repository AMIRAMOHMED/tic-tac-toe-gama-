module com.example.tictactoegama {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens com.example.tictactoegama to javafx.fxml;
    exports com.example.tictactoegama;
    exports com.example.tictactoegama.controller;
    opens com.example.tictactoegama.controller to javafx.fxml;
    exports com.example.tictactoegama.interfaces;
    opens com.example.tictactoegama.interfaces to javafx.fxml;
    exports com.example.tictactoegama.logic;
    opens com.example.tictactoegama.logic to javafx.fxml;
    exports com.example.tictactoegama.models;
    opens com.example.tictactoegama.models to javafx.fxml;
    exports com.example.tictactoegama.views;
    opens com.example.tictactoegama.views to javafx.fxml;
}