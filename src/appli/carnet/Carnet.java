package appli.carnet;

import java.time.LocalDate;

public class Carnet {
    private LocalDate debut;
    private Page[] pages;
    private int nbPages;

    public Carnet(LocalDate d, int nbJ) {
        this.debut=d;
        this.nbPages = nbJ;
        this.ajouterPage(this.nbPages);
    }

    public void ajouterPage(int nbPages) {
        pages = new Page[nbPages];
        for (int i = 0; i < nbPages; i++) {
            pages[i] = new Page(this.debut.plusDays(i));
        }
    }

    public Page getPage(int index) {
        if (index >= 0 && index < nbPages) {
            return pages[index];
        }
        return null;
    }

    public int getNbPages() {
        return nbPages;
    }
}
