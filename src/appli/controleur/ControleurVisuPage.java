package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ControleurVisuPage {
    private Carnet carnet;
    private int index;

    @FXML
    private Label titre;

    @FXML
    private Label texte;

    @FXML
    private Label date;

    public ControleurVisuPage(Carnet carnetl){
        this.carnet= carnetl;
        this.index=0;

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

    public void initialize(){
        titre.setText(carnet.getPage(this.index).getTitre());
        texte.setText(carnet.getPage(this.index).getTexte());
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

}
