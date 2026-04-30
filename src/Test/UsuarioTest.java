package Test;

import cr.ac.ucenfotec.baron.federico.bl.entities.usuario.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;

public class UsuarioTest {

    @Test
    public void testCrearModerador() {
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        assertEquals("Federico", m.getNombre());
        assertEquals(117980880, m.getId());
    }

    @Test
    public void testCalcularEdad() {
        Moderador m = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        assertTrue(m.calcularEdad() >= 18);
    }

    @Test
    public void testCrearVendedor() {
        Vendedor v = new Vendedor("Santiago", 117980770, "1234", "santi@gmail.com", LocalDate.of(1996, 1, 5), 4.5, "Atenas");
        assertEquals("Santiago", v.getNombre());
        assertEquals(4.5, v.getPuntuacion(), 0.01);
    }

    @Test
    public void testEquals() {
        Moderador m1 = new Moderador("Federico", 117980880, "1234", "fede@gmail.com", LocalDate.of(2000, 12, 30));
        Moderador m2 = new Moderador("Federico", 117980880, "5678", "otro@gmail.com", LocalDate.of(2000, 12, 30));
        assertEquals(m1, m2);
    }
}