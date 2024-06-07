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

/**
 * Classe ControleurMenu
 * Contrôleur pour la page de Menu
 */
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

    /**
     * Constructeur par défaut
     */
    public ControleurMenu(Carnet carnetl) {
        this.carnet = carnetl;
        this.carnet.ajouterObservateur(this);
    }

    /**
     * Méthode pour initialiser les données de la page
     */
    @FXML
    public void initialize() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getDateDeb().format(pattern));
        String nbJ = String.valueOf(carnet.getNbPages());
        jours.setText(nbJ);

        // Initialiser la ListView avec les participants actuels
        participantsList.getItems().setAll(carnet.getParticipants());
    }

    /**
     * Méthode pour récupérer le carnet
     * @return le carnet
     */
    public Carnet getCarnet() {
        return carnet;
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
     * Méthode pour aller à la page de création de page
     */
    @FXML
    public void toNewPage() {
        try {
            Main.showNewPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode pour aller à la page de visualisation
     */
    @FXML
    public void toVisuPage() {
        try {
            Main.showVisuPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode pour aller à la première page
     */
    @FXML
    public void toFirstPage() {
        try {
            Main.showFirstPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Méthode pour sauvegarder le carnet
     */
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
    /**
     * Méthode pour ajouter un participant
     */
    @FXML
    public void ajouterParticipant() {
        String participantName = participantField.getText().trim();
        if (!participantName.isEmpty()) {
            carnet.ajouterParticipant(participantName);
            participantField.clear();
        }
        carnet.notifierObservateurs();
    }
    /**
     * Méthode pour réagir à un changement
     */
    @Override
    public void reagir() {
        participantsList.getItems().setAll(carnet.getParticipants());
    }
}

