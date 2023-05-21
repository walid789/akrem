module com.example.akrem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.akrem to javafx.fxml;
    exports com.example.akrem;
}