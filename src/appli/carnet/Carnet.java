package appli.carnet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Carnet {
    private Date debut;
    private Date fin;
    private Page[] pages;
    private int nbPages;

    public Carnet(Date d,Date f) {
        this.debut=d;
        this.fin=f;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        long diff = f.getTime() - d.getTime();
        float res = (diff / (1000*60*60*24));
        this.nbPages = (int)res;
        this.pages = new Page[this.nbPages];
    }

    public void ajouterPage(Page page) {
        if (nbPages < pages.length) {
            pages[nbPages] = page;
            nbPages++;
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
