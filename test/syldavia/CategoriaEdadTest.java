/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syldavia;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class CategoriaEdadTest {
    
    public CategoriaEdadTest() {
    }

    @Test
    public void testEstructura() {
        assertEquals("Cantidad de elementos incorrecta", 3, CategoriaEdad.values().length);
    }
    
}
