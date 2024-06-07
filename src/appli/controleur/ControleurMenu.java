package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ControleurMenu implements Observateur {
    private Carnet carnet;

    @FXML
    private Label date;

    @FXML
    private Label jours;

    @FXML
    private TextField participantField;

    @FXML
    private ListView<String> participantsList;

    public ControleurMenu(Carnet carnetl) {
        this.carnet = carnetl;
        this.carnet.ajouterObservateur(this);
    }

    @FXML
    public void initialize() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getDateDeb().format(pattern));
        String nbJ = String.valueOf(carnet.getNbPages());
        jours.setText(nbJ);

        // Initialiser la ListView avec les participants actuels
        participantsList.getItems().setAll(carnet.getParticipants());
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
    public void toNewPage() {
        try {
            Main.showNewPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toVisuPage() {
        try {
            Main.showVisuPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toFirstPage() {
        try {
            Main.showFirstPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
            } else {
                FileChooser choixfichier = new FileChooser();
                choixfichier.setTitle("Sauvegarder votre Carnet");
                choixfichier.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Fichiers JSON", "*.json"),
                        new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));

                File selectedFile = choixfichier.showSaveDialog(date.getScene().getWindow());
                if (selectedFile != null) {
                    carnet.saveToFile(selectedFile.getAbsolutePath());
                }
            }
        }
    }

    @FXML
    public void ajouterParticipant() {
        String participantName = participantField.getText().trim();
        if (!participantName.isEmpty()) {
            carnet.ajouterParticipant(participantName);
            participantField.clear();
        }
        carnet.notifierObservateurs();
    }

    @Override
    public void reagir() {
        participantsList.getItems().setAll(carnet.getParticipants());
    }
}

