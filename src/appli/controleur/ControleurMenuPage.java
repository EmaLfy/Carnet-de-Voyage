package appli.controleur;

import appli.carnet.Carnet;
import javafx.fxml.FXML;
import appli.Main;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class ControleurMenuPage {
    private Carnet carnet;

    @FXML
    private DatePicker date;

    @FXML
    private TextField jours;

    public ControleurMenuPage(Carnet carnetl) {
        this.carnet = carnetl;
    }

    @FXML
    public void updateCarnet() {
        LocalDate d=date.getValue();
        //carnet.setDebut(date.getValue());
        int nbJours = Integer.parseInt(jours.getText());
        //carnet.setNbPages(nbJours);
        this.carnet.setData(d, nbJours);

        toMenu();
//        if (carnet != null) {
//            LocalDate d=date.getValue();
//            //carnet.setDebut(date.getValue());
//            int nbJours = Integer.parseInt(jours.getText());
//            //carnet.setNbPages(nbJours);
//            carnet.setData(d, nbJours);
//        }
    }

    public void toFirstPage(){
        try{
            Main.showFirstPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


//    public void toNewPage(){
//        try{
//            Main.showNewPage();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @FXML
    public void sortir() throws IOException {
        saveCarnet();
        System.exit(0);
    }

    // Méthode appelée lors du chargement de la page
    public void initialize() {

    }
    public void toMenu(){
        try{
            Main.showMenu();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
                System.out.println("Carnet saved to existing file: " + carnet.getPath());
            } else {
                // Le carnet n'a pas encore été sauvegardé, on utilise le FileChooser
                FileChooser choixfichier = new FileChooser();
                choixfichier.setTitle("Sauvegarder votre Carnet");
                choixfichier.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("json", "*.json"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));

                File selectedFile = choixfichier.showSaveDialog(date.getScene().getWindow());
                if (selectedFile != null) {
                    carnet.saveToFile(selectedFile.getAbsolutePath());
                    System.out.println("Carnet saved to new file: " + selectedFile.getAbsolutePath());
                }
            }
        }
    }


    @FXML
    public void chargerCarnet() throws IOException {
        FileChooser choixfichier = new FileChooser();
        choixfichier.setTitle("Charger un Carnet");
        choixfichier.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = choixfichier.showOpenDialog(null);
        if (selectedFile != null) {
            carnet.updateFromFile(selectedFile);
            System.out.println("Carnet chargé depuis le fichier: " + selectedFile.getAbsolutePath());
            System.out.println(carnet);
        }
        toMenu();
    }




}

