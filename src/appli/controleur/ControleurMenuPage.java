package appli.controleur;

import appli.carnet.Carnet;
import javafx.fxml.FXML;
import appli.Main;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ControleurMenuPage {
    private Carnet carnet;

    @FXML
    private DatePicker date;

    @FXML
    private TextField jours;

    public ControleurMenuPage(Carnet carnetl) {
        this.carnet = carnetl;
    }

    @FXML
    public void updateCarnet() {
        LocalDate d=date.getValue();
        //carnet.setDebut(date.getValue());
        int nbJours = Integer.parseInt(jours.getText());
        //carnet.setNbPages(nbJours);
        this.carnet.setData(d, nbJours);
        toMenu();
//        if (carnet != null) {
//            LocalDate d=date.getValue();
//            //carnet.setDebut(date.getValue());
//            int nbJours = Integer.parseInt(jours.getText());
//            //carnet.setNbPages(nbJours);
//            carnet.setData(d, nbJours);
//        }
    }

    @FXML
    public void retourner() {
        try {
            Main.showFirstPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toNewPage(){
        try{
            Main.showNewPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void sortir() {
        System.exit(0);
    }

    // Méthode appelée lors du chargement de la page
    public void initialize() {

    }
    public void toMenu(){
        try{
            Main.showMenu();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

