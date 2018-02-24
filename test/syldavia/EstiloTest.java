package syldavia;

import org.junit.Test;
import static org.junit.Assert.*;

public class EstiloTest {

    @Test
    public void testEstilo() {
        assertEquals("Cantidad de elementos incorrecta", 4, Estilo.values().length);
        Estilo e = Estilo.BRAZA;
        e = Estilo.ESPALDA;
        e = Estilo.LIBRE;
        e = Estilo.MARIPOSA;
    }
    
}
