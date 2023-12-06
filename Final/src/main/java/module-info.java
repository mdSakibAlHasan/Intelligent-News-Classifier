module com.example.newspaperclassification {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.newspaperclassification to javafx.fxml;
    exports com.example.newspaperclassification;
}