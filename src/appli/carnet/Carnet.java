package appli.carnet;

import java.time.LocalDate;
import java.util.Arrays;

public class Carnet {
    private LocalDate debut;
    private Page[] pages;
    private int nbPages;

    public Carnet(){
//        this.nbPages=1;
//        this.debut = LocalDate.now();
//        this.ajouterPages(this.nbPages);
    }

    public Carnet(LocalDate d, int nbJ) {
        this.debut=d;
        this.nbPages = nbJ;
        this.ajouterPages(this.nbPages);
    }

    public void setData(LocalDate d, int nbj) {
        System.out.println("\nCréation d'un carnet");
        this.debut = d;
        System.out.println("Date de debut : " + this.debut);
        this.nbPages = nbj;
        System.out.println("Nombre de pages : " + this.nbPages);
        this.ajouterPages(this.nbPages);
    }

    public void ajouterPages(int nbPages) {
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

    public Page getNewPage(){
        for(Page p:pages){
            if(p.estVierge()){
                return p;
            }
        }
        return pages[0]; // Si toutes les pages sont remplies, on retourne à la page numéro 1
    }

    public int getNbPages() {
        return nbPages;
    }

    public LocalDate getDateDeb() {
        return debut;
    }

    public LocalDate getDateFin() {
        return debut.plusDays(nbPages);
    }

    public LocalDate getDatePage(int index) {
        return debut.plusDays(index);
    }



    @Override
    public String toString() {
        return "Carnet{" +
                "debut=" + debut +
                ", nbPages=" + nbPages +
                '}';
    }
}
