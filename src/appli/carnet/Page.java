package appli.carnet;

import java.time.LocalDate;

/**
 * Classe Page
 * Représente une page du carnet de voyage
 */
public class Page extends SujetObserve{
    private LocalDate date;
    private String titre;
    private String texte;
    private String photoPath;
    /**
     * Constructeur par défaut
     * @param date LocalDate
     */
    public Page(LocalDate date) {
        //System.out.println("\nCréation d'une page");
        this.titre = "";
        //System.out.println("Titre : " + this.titre);
        this.texte = "";
        //System.out.println("Texte : " + this.texte);
        this.date = date;
        //System.out.println("Date : " + this.date);
        //System.out.println("Numéro de page : " + this.numPage);
        this.photoPath=null; //= getDefaultPhotoPath();
    }

    /**
     * Methode pour recuperer le titre de la page
     */
    public String getTitre() {
        return titre;
    }
    /**
     * Methode pour modifier le titre de la page
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    /**
     * Methode pour recuperer le texte de la page
     */
    public String getTexte() {
        return texte;
    }
    /**
     * Methode pour modifier le texte de la page
     */
    public void setTexte(String texte) {
        this.texte = texte;
    }

    /**
     * Methode pour recuperer la date de la page
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Methode pour modifier la date de la page
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * Methode pour recuperer le chemin de la photo de la page
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
    /**
     * Methode pour modifier le chemin de la photo de la page
     */
    public String getPhotoPath() {
        return photoPath;
    }
}
