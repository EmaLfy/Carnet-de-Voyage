package appli.carnet;

import appli.outils.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Carnet
 * Représente un carnet de Voyage
 */
public class Carnet extends SujetObserve {
    private LocalDate debut;
    private Page[] pages;
    private int nbPages;
    private String filePath;
    private List<String> participants;  // Liste des participants

    /**
     * Constructeur par défaut
     */
    public Carnet(){
        this.participants = new ArrayList<>();
    }

    /**
     * Constructeur avec paramètres
     * @param d Date de début du carnet
     * @param nbJ Nombre de jours du carnet
     */
    public Carnet(LocalDate d, int nbJ) {
        this.debut = d;
        this.nbPages = nbJ;
        this.participants = new ArrayList<>();
        this.ajouterPages(this.nbPages);
    }

    /**
     * Méthode pour initialiser les données du carnet
     * @param d Date de début du carnet
     * @param nbj Nombre de jours du carnet
     */
    public void setData(LocalDate d, int nbj) {
        this.debut = d;
        this.nbPages = nbj;
        this.participants = new ArrayList<>();
        this.ajouterPages(this.nbPages);
    }

    /**
     * Méthode pour ajouter des pages au carnet
     * @param nbPages Nombre de pages à ajouter
     */
    public void ajouterPages(int nbPages) {
        pages = new Page[nbPages];
        for (int i = 0; i < nbPages; i++) {
            pages[i] = new Page(this.debut.plusDays(i));
        }
    }

    /**
     * Méthode pour recuperer une page du carnet
     * @param index int
     */
    public Page getPage(int index) {
        if (index >= 0 && index < nbPages) {
            return pages[index];
        }
        return null;
    }

    /**
     * Méthode pour recuperer la première page du carnet
     */
    public Page getNewPage() {
        return pages[0];
    }

    /**
     * Méthode pour recuperer le nombre de pages du carnet
     */
    public int getNbPages() {
        return nbPages;
    }

    /**
     * Méthode pour recuperer la date de début du carnet
     */
    public LocalDate getDateDeb() {
        return debut;
    }

    /**
     * Méthode pour recuperer le chemin du fichier
     */
    public String getPath() {
        return filePath;
    }

    /**
     * Méthode pour recuperer la date de fin du carnet
     */
    public LocalDate getDateFin() {
        return debut.plusDays(nbPages);
    }

    /**
     * Méthode pour recuperer la date d'une page du carnet
     * @param index int
     */
    public LocalDate getDatePage(int index) {
        return debut.plusDays(index);
    }

    /**
     * Méthode pour recuperer la liste des participants
     */
    public List<String> getParticipants() {
        return participants;
    }

    /**
     * Méthode pour ajouter un participant
     * @param participant String
     */
    public void ajouterParticipant(String participant) {
        this.participants.add(participant);
    }

    /**
     * Méthode charger les données d'un fichier de sauvegarde
     * @param file File
     */
    public void updateFromFile(File file) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        try (FileReader reader = new FileReader(file)) {
            Carnet tempCarnet = gson.fromJson(reader, Carnet.class);
            this.filePath = file.getAbsolutePath(); // Mettre à jour le chemin du fichier

            // Mettre à jour les informations du carnet actuel avec les données chargées
            this.debut = tempCarnet.debut;
            this.nbPages = tempCarnet.nbPages;
            this.pages = tempCarnet.pages;
            this.participants = tempCarnet.participants;

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

    /**
     * Méthode pour sauvegarder les données dans un fichier
     * @param filename String
     */
    public void saveToFile(String filename) throws IOException {
        if (!filename.toLowerCase().endsWith(".json")) {
            filename += ".json";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT) // Exclure les champs avec le modificateur transient
                .create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
        }
        this.filePath = filename; // Mettre à jour le chemin du fichier
    }
    /**
     * Méthode pour afficher les informations du carnet
     */
    @Override
    public String toString() {
        return "Carnet{" +
                "debut=" + debut +
                ", nbPages=" + nbPages +
                ", participants=" + participants +
                '}';
    }
}

