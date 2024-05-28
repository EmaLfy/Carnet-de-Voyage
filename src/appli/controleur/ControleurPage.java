package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import appli.carnet.Page;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControleurPage {
    private Carnet carnet;

    private Page page;

    private int currentPageIndex;

    @FXML
    private TextField titreTextField;

    @FXML
    private TextField compteRenduTextField;

    public ControleurPage(Carnet carnetl){
        this.carnet=carnetl;
        this.page=carnet.getNewPage();
    }

    public void initialize(){

    }

    // Méthode pour initialiser le contrôleur avec le carnet et la page actuelle
    public void initData(Carnet carnet, int pageIndex) {
        this.carnet = carnet;
        this.currentPageIndex = pageIndex;
        loadPageData();
    }

    // Méthode pour charger les données de la page actuelle dans les champs de texte
    private void loadPageData() {
        Page currentPage = carnet.getPage(currentPageIndex);
        if (currentPage != null) {
            titreTextField.setText(currentPage.getTitre());
            compteRenduTextField.setText(currentPage.getTexte());
        }
    }

    @FXML
    public void sortir() {
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

    @FXML
    public void save() {
        // Récupérer les valeurs des champs de texte
        String titre = titreTextField.getText();
        String compteRendu = compteRenduTextField.getText();

        // Mettre à jour la page actuelle dans le carnet
        Page currentPage = carnet.getPage(currentPageIndex);
        if (currentPage != null) {
            currentPage.setTitre(titre);
            currentPage.setTexte(compteRendu);

            // Afficher les valeurs pour vérifier
            System.out.println("Page mise à jour :");
            System.out.println("Date: " + currentPage.getDate());
            System.out.println("Titre: " + currentPage.getTitre());
            System.out.println("Compte Rendu: " + currentPage.getTexte());
        }
    }
}

