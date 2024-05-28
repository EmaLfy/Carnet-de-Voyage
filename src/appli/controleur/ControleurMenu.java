package appli.controleur;

import appli.Main;
import appli.carnet.Carnet;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.lang.Integer.parseInt;

public class ControleurMenu {
    private Carnet carnet;

    @FXML
    private Label date;

    @FXML
    private Label jours;

    public ControleurMenu(Carnet carnetl){
        this.carnet=carnetl;
    }

    public void initialize(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.setText(carnet.getDateDeb().format(pattern));
        String nbJ= String.valueOf(carnet.getNbPages());
        jours.setText(nbJ);

    }

    public Carnet getCarnet() {
        return carnet;
    }

    @FXML
    public void sortir() {
        System.exit(0);
    }

    @FXML
    public void toNewPage(){
        try{
            Main.showNewPage();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
