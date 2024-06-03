package appli.carnet;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Page {
    private int numPage;
    private LocalDate date;
    private String titre;
    private String texte;

    public Page(LocalDate date, int num) {
        //System.out.println("\nCréation d'une page");
        this.titre = "";
        //System.out.println("Titre : " + this.titre);
        this.texte = "";
        //System.out.println("Texte : " + this.texte);
        this.date = date;
        //System.out.println("Date : " + this.date);
        this.numPage = num;
        //System.out.println("Numéro de page : " + this.numPage);
    }

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
}
