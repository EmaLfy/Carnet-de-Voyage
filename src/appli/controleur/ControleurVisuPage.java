package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControleurVisuPage {
    private Carnet carnet;
    private int index;

    @FXML
    private Label titre;

    @FXML
    private Label texte;

    public ControleurVisuPage(Carnet carnetl){
        this.carnet= carnetl;
        this.index=0;

    }

    public void initialize(){
        titre.setText(carnet.getPage(this.index).getTitre());
        texte.setText(carnet.getPage(this.index).getTexte());
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
}
