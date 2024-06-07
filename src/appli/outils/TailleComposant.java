package appli.outils;

/**
 * Classe TailleComposant
 * Classe pour définir la taille des composants
 */
public class TailleComposant {
    private static final TailleComposant instance = new TailleComposant();
    /**
     * Constructeur par défaut
     */
    private TailleComposant(){
    }
    /**
     * Méthode pour récupérer l'instance de la classe
     * @return TailleComposant
     */
    public static TailleComposant getInstance() {
        return instance;
    }

    /**
     * Méthode pour récupérer la hauteur de la fenêtre
     * @return int
     */
    public int windHeight(){
        return 600;
    }
    /**
     * Méthode pour récupérer la largeur de la fenêtre
     * @return int
     */
    public int windWidth(){
        return 900;
    }
}
