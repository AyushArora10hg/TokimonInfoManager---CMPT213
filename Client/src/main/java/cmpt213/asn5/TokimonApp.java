package cmpt213.asn5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TokimonApp extends Application {
    @Override
    public void start(Stage stage) {

        ClientUI ui = new ClientUI();

        Scene scene = new Scene(ui.getUI(), 1000,600);

        stage.setScene(scene);

        stage.show();

    }

}
