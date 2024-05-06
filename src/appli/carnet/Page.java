package appli.carnet;

import java.time.LocalDate;
import java.util.Date;

public class Page {
    private LocalDate date;
    private String titre;
    private String texte;

    public Page(LocalDate date) {
        this.titre = "Titre";
        this.texte = "Texte";
        this.date = date;
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
