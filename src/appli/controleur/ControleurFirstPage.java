package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import java.io.IOException;

/**
 * Classe ControleurFirstPage
 * Contrôleur pour la page FirstPage
 */
public class ControleurFirstPage implements Observateur{

    private Carnet carnet;

    // Constructeur par défaut
    public ControleurFirstPage() {
    }

    /**
     * Constructeur par défaut
     */
    public ControleurFirstPage(Carnet carnetl){
        this.carnet=Main.getCarnet();
        this.carnet.ajouterObservateur(this);
    }

    /**
     * Méthode pour sortir de l'application
     */
    @FXML
    public void sortir() throws IOException {
        saveCarnet();
        System.exit(0);
    }

    /**
     * Méthode pour aller à la page Menu
     */
    @FXML
    public void toMenu() {
        try {
            Main.showMenuPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour aller à la page FirstPage
     */
    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
                //System.out.println("Carnet saved to existing file: " + carnet.getPath());
            }
        }
    }

    /**
     * Méthode pour aller à la page FirstPage
     */
    @Override
    public void reagir() {

    }
}
