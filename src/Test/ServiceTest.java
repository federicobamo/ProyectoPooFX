package Test;

import cr.ac.ucenfotec.baron.federico.bl.logic.Service;
import cr.ac.ucenfotec.baron.federico.bl.entities.*;
import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceTest {

    @Test
    public void testPuedeCrearSubasta() {
        Service service = new Service();
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        assertFalse(service.puedeCrearSubasta(m));
    }

    @Test
    public void testPuedeOfertar() {
        Service service = new Service();
        Vendedor v = new Vendedor("Santiago", 117980770, "1234", "santi@gmail.com", LocalDate.of(1996, 1, 5), 4.5, "Atenas");
        assertFalse(service.puedeOfertar(v));
    }

    @Test
    public void testOfertaValida() {
        Service service = new Service();
        assertTrue(service.ofertaValida(200.0, 100.0));
        assertFalse(service.ofertaValida(50.0, 100.0));
    }

    @Test
    public void testExisteModerador() {
        Service service = new Service();
        assertNotNull(service);
    }
}