module cmpt213.asn5.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.jsobject;
    requires json.simple;


    opens cmpt213.asn5 to javafx.fxml;
    exports cmpt213.asn5;
}