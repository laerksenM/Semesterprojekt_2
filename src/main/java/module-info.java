module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jssc;

    opens org.example to javafx.fxml;
    exports org.example;
}