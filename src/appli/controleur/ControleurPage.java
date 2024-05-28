package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import appli.carnet.Page;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ControleurPage {
    private Carnet carnet;

    private Page page;

    private int currentPageIndex;

    @FXML
    private TextField titre;

    @FXML
    private TextArea texte;

    public ControleurPage(Carnet carnetl){
        this.carnet=carnetl;
        this.page=carnet.getNewPage();
    }

    public void initialize(){
        titre.setText(page.getTitre());
        texte.setText(page.getTexte());
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
            titre.setText(currentPage.getTitre());
            texte.setText(currentPage.getTexte());
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
        String titreP = titre.getText();
        String compteRendu = texte.getText();

        // Mettre à jour la page actuelle dans le carnet
        Page currentPage = carnet.getPage(currentPageIndex);
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
}

