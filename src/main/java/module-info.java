module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens org.example to javafx.fxml;
    exports org.example;
    exports database;
    opens database to javafx.fxml;
}