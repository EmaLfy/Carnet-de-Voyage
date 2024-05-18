package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {
        // Charge le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/firstPage.fxml"));

        // Configure la scène
        primaryStage.setTitle("My JavaFX Application");
        primaryStage.setScene(new Scene(root, 800, 600));

        // Affiche la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
