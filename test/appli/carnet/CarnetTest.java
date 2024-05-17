package appli.carnet;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CarnetTest {
    @Test
    public void nbPageTest() {
        Carnet c=new Carnet(LocalDate.of(2004,2,12),6);
        assert(c.getNbPages()==6):"Erreur nbPages";
    }
}