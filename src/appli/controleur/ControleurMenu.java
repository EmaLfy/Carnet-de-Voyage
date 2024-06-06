package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ControleurMenu implements Observateur{
    private Carnet carnet;

    @FXML
    private Label date;

    @FXML
    private Label jours;

    public ControleurMenu(Carnet carnetl){
        this.carnet=carnetl;
        this.carnet.ajouterObservateur(this);
    }

    public void initialize(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getDateDeb().format(pattern));
        String nbJ= String.valueOf(carnet.getNbPages());
        jours.setText(nbJ);

    }

    public Carnet getCarnet() {
        return carnet;
    }

    @FXML
    public void sortir() throws IOException {
        saveCarnet();
        System.exit(0);
    }

    @FXML
    public void toNewPage(){
        try{
            Main.showNewPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void toVisuPage(){
        try{
            Main.showVisuPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void toFirstPage(){
        try{
            Main.showFirstPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void saveCarnet() throws IOException {
        if (carnet != null) {
            // Création d'un FileChooser pour sélectionner ou spécifier le fichier de sauvegarde
            FileChooser choixfichier = new FileChooser();
            choixfichier.setTitle("Sauvegarder votre Carnet");
            choixfichier.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Fichiers JSON", "*.json"),
                    new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));

            File selectedFile = choixfichier.showSaveDialog(date.getScene().getWindow());
            if (selectedFile != null) {
                // Sauvegarde du carnet dans le fichier sélectionné
                carnet.saveToFile(selectedFile.getAbsolutePath());
            }
        }
    }



    @Override
    public void reagir() {

    }
}
