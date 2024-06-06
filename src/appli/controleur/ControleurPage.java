package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import appli.carnet.Page;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class ControleurPage implements Observateur{
    private Carnet carnet;
    private int index;

    @FXML
    private TextField titre;

    @FXML
    private TextArea texte;

    @FXML
    private Label date;

    @FXML
    private ImageView photo;

    public ControleurPage(Carnet carnetl){
        this.carnet = carnetl;
        this.index = 0; // Initialiser l'index à 0
        this.carnet.ajouterObservateur(this);
    }

    public void initialize(){
        updateData();
        photo.setOnMouseClicked(event -> choisirImage());
    }

    public void updateData(){
        Page currentPage = carnet.getPage(this.index);
        if (currentPage != null) {
            titre.setText(currentPage.getTitre());
            texte.setText(currentPage.getTexte());
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            date.setText(currentPage.getDate().format(pattern));
            updateImage();
        }
    }

    private void updateImage() {
        String photoPath = this.carnet.getPage(this.index).getPhotoPath();
        Image image;
        if (photoPath != null && !photoPath.isEmpty()) {
            image = new Image("file:" + photoPath);
        } else {
            // Charger l'image par défaut
            InputStream defaultImageStream = getClass().getResourceAsStream("/medias/photo_default.jpg");
            if (defaultImageStream != null) {
                image = new Image(defaultImageStream);
            } else {
                System.out.println("Default image not found!");
                return;
            }
        }
        photo.setImage(image);
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

    public void toFirstPage(){
        try {
            Main.showFirstPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void suivant(){
        if (index < carnet.getNbPages() - 1) {
            index++;
        } else {
            index = 0;
        }
        carnet.notifierObservateurs();
        //reagir();
    }

    @FXML
    public void precedent(){
        if (index == 0) {
            index = carnet.getNbPages() - 1;
        } else {
            index--;
        }
        carnet.notifierObservateurs();
    }

    @FXML
    public void save() {
        String titreP = titre.getText();
        String compteRendu = texte.getText();

        Page currentPage = carnet.getPage(index);
        if (currentPage != null) {
            currentPage.setTitre(titreP);
            currentPage.setTexte(compteRendu);

            // Vérifiez et définissez le chemin de la photo par défaut si nécessaire
            if (photo.getImage() != null && photo.getImage().getUrl() != null) {
                currentPage.setPhotoPath(photo.getImage().getUrl().replace("file:", ""));
            }
            // Message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Page enregistrée avec succès!");

            // Charger et appliquer la feuille de style
            URL cssURL = getClass().getResource("/styles/styles.css");
            if (cssURL != null) {
                alert.getDialogPane().getStylesheets().add(cssURL.toExternalForm());
                alert.getDialogPane().getStyleClass().add("alert");
            } else {
                System.out.println("Stylesheet not found!");
            }
            alert.show();

            // Fermer l'alerte après 1 secondes
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(1),
                    event -> alert.close()
            ));
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    @FXML
    public void choisirImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File selectedFile = fileChooser.showOpenDialog(titre.getScene().getWindow());
        if (selectedFile != null) {
            String photoPath = selectedFile.getAbsolutePath();
            carnet.getPage(this.index).setPhotoPath(photoPath);
            carnet.notifierObservateurs();
        }
    }

    public void saveCarnet() throws IOException {
        if (carnet != null) {
            if (carnet.getPath() != null) {
                // Le carnet a déjà été sauvegardé, on utilise le chemin existant
                carnet.saveToFile(carnet.getPath());
            } else {
                // Le carnet n'a pas encore été sauvegardé, on utilise le FileChooser
                FileChooser choixfichier = new FileChooser();
                choixfichier.setTitle("Sauvegarder votre Carnet");
                choixfichier.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("json", "*.json"),
                        new FileChooser.ExtensionFilter("All Files", "*.*"));

                File selectedFile = choixfichier.showSaveDialog(titre.getScene().getWindow());
                if (selectedFile != null) {
                    carnet.saveToFile(selectedFile.getAbsolutePath());
                }
            }
        }
    }

    @Override
    public void reagir() {
        updateData();
    }
}


