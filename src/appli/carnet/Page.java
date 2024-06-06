package appli.carnet;

import java.time.LocalDate;
import java.util.Objects;

public class Page extends SujetObserve{
    private LocalDate date;
    private String titre;
    private String texte;
    private String photoPath;

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

//    private String getDefaultPhotoPath() {
//        String defaultPhotoPath = "/medias/photo_default.jpg";
//        System.out.println("Default photo path: " + getClass().getResource(defaultPhotoPath));
//        return getClass().getResource(defaultPhotoPath) != null
//                ? getClass().getResource(defaultPhotoPath).toString()
//                : null;
//    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
