package appli.controleur;

import appli.Main;
import javafx.fxml.FXML;

public class ControleurFirstPage {
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
