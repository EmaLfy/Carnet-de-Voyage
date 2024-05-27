package appli.carnet;

import java.time.LocalDate;
import java.util.Date;

public class Page {
    private LocalDate date;
    private String titre;
    private String texte;

    public Page(LocalDate date) {
        System.out.println("\nCréation d'une page");
        this.titre = "Titre";
        System.out.println("Titre : " + this.titre);
        this.texte = "Texte";
        System.out.println("Texte : " + this.texte);
        this.date = date;
        System.out.println("Date : " + this.date);
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
