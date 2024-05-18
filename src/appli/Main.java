package appli;

import appli.outils.TailleComposant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage primaryStage;
    private static TailleComposant tailleComposant = TailleComposant.getInstance();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("-- Carnet de Voyage --");
        showFirstPage();
    }

    public static void showFirstPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/firstPage.fxml"));
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    public static void showMenuPage() throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/menuPage.fxml"));
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
