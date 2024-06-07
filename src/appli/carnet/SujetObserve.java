package appli.carnet;

import appli.controleur.Observateur;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
/**
 * Classe SujetObserve
 * Classe abstraite pour les objets observables
 */
public class SujetObserve {
    @Expose(serialize = false, deserialize = false)
    private transient ArrayList<Observateur> observateurs = new ArrayList<>();
    /**
     * Méthode pour ajouter un observateur
     * @param o Observateur
     */
    public void ajouterObservateur(Observateur o) {
        observateurs.add(o);
    }
    /**
     * Méthode pour notifier un observateur
     */
    public void notifierObservateurs() {
        for (Observateur o : observateurs) {
            o.reagir();
        }
    }
}

