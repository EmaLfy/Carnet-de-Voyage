package appli.controleur;

import appli.carnet.Carnet;
import javafx.fxml.FXML;
import appli.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

/**
 * Classe ControleurMenuPage
 * Contrôleur pour la page MenuPage
 */
public class ControleurMenuPage implements Observateur{
    private Carnet carnet;

    @FXML
    private DatePicker date;

    @FXML
    private TextField jours;
    /**
     * Constructeur par défaut
     * @param carnetl Carnet
     */
    public ControleurMenuPage(Carnet carnetl) {
        this.carnet = carnetl;
        this.carnet.ajouterObservateur(this);
    }
    /**
     * Méthode pour mettre à jour le carnet
     */
    @FXML
    public void updateCarnet() {
        LocalDate d = date.getValue();
        String joursText = jours.getText();

        // Vérifier si la date est sélectionnée
        if (d == null) {
            afficherMessageErreur("Veuillez sélectionner une date.");
            return;
        }

        // Vérifier si le champ jours est vide
        if (joursText.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nombre de jours.");
            return;
        }

        // Vérifier si le champ jours contient un nombre
        int nbJours;
        try {
            nbJours = Integer.parseInt(joursText);
        } catch (NumberFormatException e) {
            afficherMessageErreur("Le nombre de jours doit être un entier.");
            return;
        }

        // Vérifier si le nombre de jours est positif
        if (nbJours <= 0) {
            afficherMessageErreur("Le nombre de jours doit être supérieur à zéro.");
            return;
        }

        // Si tous les champs sont valides, mettre à jour le carnet
        carnet.setData(d, nbJours);
        toMenu();
    }
    /**
     * Méthode pour afficher un message d'erreur
     * @param message String
     */
    private void afficherMessageErreur(String message) {
        // Afficher le message d'erreur dans une boîte de dialogue ou une alerte
        // par exemple :
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        URL cssURL = getClass().getResource("/styles/styles.css");
        if (cssURL != null) {
            alert.getDialogPane().getStylesheets().add(cssURL.toExternalForm());
            alert.getDialogPane().getStyleClass().add("alert");
        } else {
            System.out.println("Stylesheet not found!");
        }
        alert.show();
    }

    /**
     * Méthode pour aller à la page FirstPage
     */
    public void toFirstPage(){
        try{
            Main.showFirstPage();
        } catch (Exception e){
            e.printStackTrace();
        }
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
     * Méthode pour initialiser les données
     */
    public void initialize() {
    }

    /**
     * Méthode pour aller à la page Menu
     */
    public void toMenu(){
        try{
            Main.showMenu();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour sauvegarder le carnet
     */
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

    /**
     * Méthode pour charger un carnet
     */
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
            //System.out.println("Carnet chargé depuis le fichier: " + selectedFile.getAbsolutePath());
            //System.out.println(carnet);
        }
        toMenu();
    }
    /**
     * Méthode pour réagir
     */
    @Override
    public void reagir() {

    }
}

