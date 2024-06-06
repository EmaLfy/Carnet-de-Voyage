package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ControleurFirstPage implements Observateur{

    private Carnet carnet;

    public ControleurFirstPage(){
    }

    public ControleurFirstPage(Carnet carnetl){
        this.carnet=Main.getCarnet();
        this.carnet.ajouterObservateur(this);
    }

    @FXML
    public void sortir() throws IOException {
        saveCarnet();
        System.exit(0);
    }

    @FXML
    public void toMenu() {
        try {
            Main.showMenuPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
                //System.out.println("Carnet saved to existing file: " + carnet.getPath());
            }
        }
    }

    @Override
    public void reagir() {

    }
}
