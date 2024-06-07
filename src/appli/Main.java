package appli;

import appli.carnet.Carnet;
import appli.carnet.Page;
import appli.controleur.*;
import appli.outils.TailleComposant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe Main
 * Classe principale de l'application
 */
public class Main extends Application {

    private static Stage primaryStage;
    private static TailleComposant tailleComposant = TailleComposant.getInstance();
    private static Carnet carnet;

    /**
     * Méthode pour démarrer l'application
     * @param primaryStage Stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        //this.carnet = new Carnet();
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("-- Carnet de Voyage --");
        primaryStage.setResizable(false);
        showFirstPage();
    }

    /**
     * Méthode pour récupérer le carnet
     * @return Carnet
     */
    public static Carnet getCarnet() {
        return carnet;
    }

    /**
     * Méthode pour afficher la première page
     * @throws Exception
     */
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
    }

    /**
     * Méthode pour afficher la page du menu (sans le carnet)
     * @throws Exception
     */
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

    /**
     * Méthode pour afficher la page du menu (avec le carnet)
     * @throws Exception
     */
    public static void showMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/menu.fxml"));
        loader.setControllerFactory(iC->new ControleurMenu(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    /**
     * Méthode pour afficher la page de saisie
     * @throws Exception
     */
    public static void showNewPage() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/vuePage.fxml"));
        loader.setControllerFactory(iC->new ControleurPage(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    /**
     * Méthode pour afficher la page de visualisation
     * @throws Exception
     */
    public static void showVisuPage()throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/visuPage.fxml"));
        loader.setControllerFactory(iC->new ControleurVisuPage(carnet));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root, tailleComposant.windWidth(), tailleComposant.windHeight()));
        primaryStage.show();
    }

    /**
     * Méthode Main
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Méthode pour récupérer l'instance du carnet
     * @return Carnet
     */
    public Carnet getCarnetInstance() {
        return carnet;
    }

    /**
     * Méthode pour récupérer une page du carnet
     * @param index int
     * @return Page
     */
    public Page getPage(int index) {
        return carnet.getPage(index);
    }
}
