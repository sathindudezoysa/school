module com.example.abs_version_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;

    requires org.xerial.sqlitejdbc;

    opens com.example.abs_version_2 to javafx.fxml;
    exports com.example.abs_version_2;
}