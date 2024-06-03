package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class ControleurMenu {
    private Carnet carnet;

    @FXML
    private Label date;

    @FXML
    private Label jours;

    public ControleurMenu(Carnet carnetl){
        this.carnet=carnetl;
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

    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
                //System.out.println("Carnet saved to existing file: " + carnet.getPath());
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
                    //System.out.println("Carnet saved to new file: " + selectedFile.getAbsolutePath());
                }
            }
        }
    }
}
