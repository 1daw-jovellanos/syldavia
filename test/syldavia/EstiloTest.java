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
public class EstiloTest {
    
    public EstiloTest() {
    }

    @Test
    public void testEstilo() {
        assertEquals("Cantidad de elementos incorrecta", 4, Estilo.values().length);
    }
    
}
