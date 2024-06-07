package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;

/**
 * Classe ControleurVisuPage
 * Contrôleur pour la page VisuPage
 */
public class ControleurVisuPage implements Observateur{
    private Carnet carnet;
    private int index;

    @FXML
    private Label titre;

    @FXML
    private Label texte;

    @FXML
    private Label date;

    @FXML
    private ImageView photo;

    /**
     * Constructeur par défaut
     * @param carnetl Carnet
     */
    public ControleurVisuPage(Carnet carnetl){
        this.carnet= carnetl;
        this.index=0;
        this.carnet.ajouterObservateur(this);
    }

    /**
     * Méthode pour aller à la page suivante
     */
    @FXML
    public void suivant(){
        if(index<carnet.getNbPages()-1){
            index++;
        }else{
            index=0;
        }
        carnet.notifierObservateurs();
    }

    /**
     * Méthode pour aller à la page précédente
     */
    @FXML
    public void precedent(){
        if(index==0){
            index= carnet.getNbPages()-1;
        }else{
            index--;
        }
        carnet.notifierObservateurs();
    }

    /**
     * Méthode pour initialiser les données de la page
     */
    public void initialize(){
        carnet.notifierObservateurs();

    }

    /**
     * Méthode pour mettre à jour les données de la page
     */
    public void updateData(){
        titre.setText(carnet.getPage(this.index).getTitre());
        texte.setText(carnet.getPage(this.index).getTexte());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getPage(this.index).getDate().format(pattern));
        updateImage();
    }

    /**
     * Méthode pour mettre à jour l'image de la page
     */
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
    public void toFirstPage(){
        try{
            Main.showFirstPage();
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

                File selectedFile = choixfichier.showSaveDialog(titre.getScene().getWindow());
                if (selectedFile != null) {
                    carnet.saveToFile(selectedFile.getAbsolutePath());
                    //System.out.println("Carnet saved to new file: " + selectedFile.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Méthode pour réagir
     */
    @Override
    public void reagir() {
        updateData();
    }
}
