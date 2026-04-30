package Test;

import cr.ac.ucenfotec.baron.federico.bl.entities.*;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SubastaTest {

    @Test
    public void testCrearSubasta() {
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        Subasta s = new Subasta(LocalDateTime.now().plusDays(5), m, 100.0);
        assertEquals(100.0, s.getPrecioMinimo(), 0.01);
        assertEquals(EstadoSubasta.ACTIVA, s.getEstado());
    }

    @Test
    public void testSubastaActiva() {
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        Subasta s = new Subasta(LocalDateTime.now().plusDays(5), m, 100.0);
        assertNotEquals(EstadoSubasta.VENCIDA, s.getEstado());
    }

    @Test
    public void testAgregarOferta() {
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        Subasta s = new Subasta(LocalDateTime.now().plusDays(5), m, 100.0);
        Coleccionista c = new Coleccionista("Santiago", 117980770, "1234", "santi@gmail.com", LocalDate.of(1996, 1, 5), 4.5, "Atenas");
        Oferta o = new Oferta(c, 200.0);
        s.getListaOfertas().add(o);
        assertEquals(1, s.getListaOfertas().size());
    }
}