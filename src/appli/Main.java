package appli;

import appli.carnet.Carnet;
import appli.carnet.Page;
import appli.controleur.*;
import appli.outils.TailleComposant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;
    private static TailleComposant tailleComposant = TailleComposant.getInstance();
    private static Carnet carnet;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //this.carnet = new Carnet();
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("-- Carnet de Voyage --");
        showFirstPage();

        // Add event handler for Enter key
//        primaryStage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                try {
//                    showMenuPage();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Carnet getCarnet() {
        return carnet;
    }

    public static void showFirstPage() throws Exception {
        if(carnet==null){
            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/firstPage.fxml"));
            primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
            primaryStage.show();
        }else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/firstPage.fxml"));
            loader.setControllerFactory(iC -> new ControleurFirstPage(carnet));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
            primaryStage.show();
        }
//        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/firstPage.fxml"));
//        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
//        primaryStage.show();
//        primaryStage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            if (event.getCode() == KeyCode.ENTER) {
//                try {
//                    showMenuPage();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public static void showMenuPage() throws Exception {
        if(carnet!=null){
            showMenu();
        }else {
            carnet = new Carnet();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/menuPage.fxml"));
            loader.setControllerFactory(iC -> new ControleurMenuPage(carnet));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
            primaryStage.show();
        }
    }

    public static void showMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/menu.fxml"));
        loader.setControllerFactory(iC->new ControleurMenu(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    public static void showNewPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/vuePage.fxml"));
        loader.setControllerFactory(iC->new ControleurPage(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    public static void showVisuPage()throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/visuPage.fxml"));
        loader.setControllerFactory(iC->new ControleurVisuPage(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Carnet getCarnetInstance() {
        return carnet;
    }

    public Page getPage(int index) {
        return carnet.getPage(index);
    }
}
