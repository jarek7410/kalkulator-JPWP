module com.example.kalkulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens com.example.kalkulator to javafx.fxml;
    exports com.example.kalkulator;
}