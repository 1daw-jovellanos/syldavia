package syldavia;

import org.junit.Test;
import static org.junit.Assert.*;

public class CategoriaEdadTest {
    
    @Test
    public void testEstructura() {
        assertEquals("Cantidad de elementos incorrecta", 3, CategoriaEdad.values().length);
        CategoriaEdad c = CategoriaEdad.JUNIOR;
        c = CategoriaEdad.CLASS;
        c = CategoriaEdad.MASTER;

    }
    
}
