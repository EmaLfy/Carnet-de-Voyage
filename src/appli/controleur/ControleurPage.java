package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import appli.carnet.Page;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ControleurPage {
    private Carnet carnet;

    private Page page;

    private int index;

    @FXML
    private TextField titre;

    @FXML
    private TextArea texte;

    @FXML
    private Label date;

    public ControleurPage(Carnet carnetl){
        this.carnet=carnetl;
        this.page=carnet.getNewPage();
    }

    public void initialize(){
        titre.setText(page.getTitre());
        texte.setText(page.getTexte());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getPage(this.index).getDate().format(pattern));
    }

    public void updateData(){
        titre.setText(carnet.getPage(this.index).getTitre());
        texte.setText(carnet.getPage(this.index).getTexte());
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getPage(this.index).getDate().format(pattern));
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
        try{
            Main.showFirstPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void suivant(){
        if(index<carnet.getNbPages()-1){
            index++;
        }else{
            index=0;
        }
        updateData();
    }

    @FXML
    public void precedent(){
        if(index==0){
            index= carnet.getNbPages()-1;
        }else{
            index--;
        }
        updateData();
    }

    @FXML
    public void save() {
        // Récupérer les valeurs des champs de texte
        String titreP = titre.getText();
        String compteRendu = texte.getText();

        // Mettre à jour la page actuelle dans le carnet
        Page currentPage = carnet.getPage(index);
        if (currentPage != null) {
            currentPage.setTitre(titreP);
            currentPage.setTexte(compteRendu);

            // Afficher les valeurs pour vérifier
            System.out.println("Page mise à jour :");
            System.out.println("Date: " + currentPage.getDate());
            System.out.println("Titre: " + currentPage.getTitre());
            System.out.println("Compte Rendu: " + currentPage.getTexte());
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

                File selectedFile = choixfichier.showSaveDialog(titre.getScene().getWindow());
                if (selectedFile != null) {
                    carnet.saveToFile(selectedFile.getAbsolutePath());
                    //System.out.println("Carnet saved to new file: " + selectedFile.getAbsolutePath());
                }
            }
        }
    }
}

