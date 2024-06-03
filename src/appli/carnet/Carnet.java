package appli.carnet;


import appli.outils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Carnet {
    private LocalDate debut;
    private Page[] pages;
    private int nbPages;
    private String filePath;

    public Carnet(){
    }

    public Carnet(LocalDate d, int nbJ) {
        this.debut=d;
        this.nbPages = nbJ;
        this.ajouterPages(this.nbPages);
    }

    public void setData(LocalDate d, int nbj) {
        //System.out.println("\nCréation d'un carnet");
        this.debut = d;
        //System.out.println("Date de debut : " + this.debut);
        this.nbPages = nbj;
        //System.out.println("Nombre de pages : " + this.nbPages);
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
        return pages[0];
    }

    public int getNbPages() {
        return nbPages;
    }

    public LocalDate getDateDeb() {
        return debut;
    }

    public String getPath() {
        return filePath;
    }

    public LocalDate getDateFin() {
        return debut.plusDays(nbPages);
    }

    public LocalDate getDatePage(int index) {
        return debut.plusDays(index);
    }

    // Méthode pour charger les données d'un fichier et les mettre à jour dans l'objet actuel
    public void updateFromFile(File file) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        try (FileReader reader = new FileReader(file)) {
            Carnet tempCarnet = gson.fromJson(reader, Carnet.class);
            this.filePath = file.getAbsolutePath(); // Mettre à jour le chemin du fichier
            //System.out.println("Carnet loaded from file: " + this.filePath);

            // Mettre à jour les informations du carnet actuel avec les données chargées
            this.debut = tempCarnet.debut;
            this.nbPages = tempCarnet.nbPages;
            this.pages = tempCarnet.pages;

            // Assurer que les pages sont correctement initialisées
            if (this.pages == null || this.pages.length != this.nbPages) {
                this.ajouterPages(this.nbPages);
            } else {
                // Mettre à jour les dates des pages
                for (int i = 0; i < this.nbPages; i++) {
                    if (this.pages[i] != null) {
                        this.pages[i].setDate(this.debut.plusDays(i));
                    } else {
                        this.pages[i] = new Page(this.debut.plusDays(i));
                    }
                }
            }
        }
    }


    // Méthode pour sauvegarder dans un fichier
    public void saveToFile(String filename) throws IOException {
        if (!filename.toLowerCase().endsWith(".json")) {
            filename += ".json";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
        }
        this.filePath = filename; // Mettre à jour le chemin du fichier
    }



    @Override
    public String toString() {
        return "Carnet{" +
                "debut=" + debut +
                ", nbPages=" + nbPages +
                '}';
    }
}
