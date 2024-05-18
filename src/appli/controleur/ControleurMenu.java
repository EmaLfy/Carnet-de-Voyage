package appli.controleur;

import javafx.fxml.FXML;
import appli.Main;

public class ControleurMenu {

    @FXML
    public void retourner() {
        try {
            Main.showFirstPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sortir() {
        System.exit(0);
    }
}

