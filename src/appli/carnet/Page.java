package appli.carnet;

import java.util.Date;

public class Page {
    private Date date;
    private String titre;
    private String texte;

    public Page(Date date,String titre, String texte) {
        this.titre = titre;
        this.texte = texte;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
