package appli.carnet;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PageTest {
    @Test
    public void PageCarnetTest() {
        Page p = new Page(LocalDate.of(2004, 2, 12));
        assertEquals("Titre", p.getTitre());
        assertEquals("Texte", p.getTexte());
        assertEquals(LocalDate.of(2004, 2, 12), p.getDate());
        p.setDate(LocalDate.of(2004, 2, 13));
        assertEquals(LocalDate.of(2004, 2, 13), p.getDate());
        p.setTitre("Nouveau titre");
        assertEquals("Nouveau titre", p.getTitre());
        p.setTexte("Nouveau texte");
        assertEquals("Nouveau texte", p.getTexte());
    }
}