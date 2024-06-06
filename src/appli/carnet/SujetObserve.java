package appli.carnet;

import appli.controleur.Observateur;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class SujetObserve {

    @Expose(serialize = false, deserialize = false)
    private transient ArrayList<Observateur> observateurs = new ArrayList<>();

    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }

    public void notifierObservateurs() {
        for (Observateur o : observateurs) {
            o.reagir();
        }
    }
}

