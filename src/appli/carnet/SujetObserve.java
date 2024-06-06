package appli.carnet;

import appli.controleur.Observateur;

import java.util.ArrayList;

public class SujetObserve {
    private ArrayList<Observateur> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void notifierObservateurs() {
        for (Observateur o : observateurs) {
            o.reagir();
        }
    }
//    public ArrayList<Observateur> getObservateurs() {
//        return observateurs;
//    }

}
