module com.example.railwaymanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;

    opens com.example.railwaymanagement to javafx.fxml;
    exports com.example.railwaymanagement;
}