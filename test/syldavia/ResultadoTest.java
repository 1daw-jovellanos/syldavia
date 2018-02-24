/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syldavia;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victor
 */
public class ResultadoTest {

    Resultado r1, r2, r3, r4;

    public ResultadoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        r1 = new Resultado(Estilo.BRAZA, 200, 244, 91);
        r2 = new Resultado(Estilo.MARIPOSA, 250, 283, 119);
        r3 = new Resultado(Estilo.ESPALDA, 250, 284, 119);
        r4 = new Resultado(500, 487, 222);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructores() {
        boolean hayExcepcion;
        Resultado instance;

        //Constructor de 4 params
        hayExcepcion = false;
        try {
            instance = new Resultado(null, 100, 120, 50);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que estilo sea null", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(Estilo.ESPALDA, 101, 120, 50);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que distancia no sea múltiplo de 25", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(Estilo.ESPALDA, 100, 0, 50);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que tiempo sea 0", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(Estilo.ESPALDA, 100, 120, 0);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que brazadas sea 0", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(101, 120, 50);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que distancia no sea múltiplo de 25", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(100, 0, 50);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que tiempo sea 0", true, hayExcepcion);

        hayExcepcion = false;
        try {
            instance = new Resultado(100, 50, 0);
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no tiene en cuenta que brazadas sea 0", true, hayExcepcion);

    }

    @Test
    public void testAccesores() {
        assertEquals(Estilo.BRAZA, r1.getEstilo());
        assertEquals(200, r1.getDistancia());
        assertEquals(244, r1.getTiempo());
        assertEquals(91, r1.getBrazadas());

        assertEquals(Estilo.LIBRE, r4.getEstilo());
        assertEquals(500, r4.getDistancia());
        assertEquals(487, r4.getTiempo());
        assertEquals(222, r4.getBrazadas());
    }

    @Test
    public void testSwolf() {

        assertEquals("Cálculo de SWOLF incorrecto", 43, r1.getSwolf());
        assertEquals("Cálculo de SWOLF incorrecto", 41, r2.getSwolf());
        assertEquals("Cálculo de SWOLF incorrecto", 41, r3.getSwolf());
        assertEquals("Cálculo de SWOLF incorrecto", 37, r4.getSwolf());
    }

    @Test
    public void testCompareTo() {
        assertEquals("compareTo incorrecto", true, r1.compareTo(r1) == 0);
        assertEquals("compareTo incorrecto", true, r1.compareTo(r2) > 0);
        assertEquals("compareTo incorrecto", true, r1.compareTo(r3) > 0);
        assertEquals("compareTo incorrecto", true, r1.compareTo(r4) > 0);

        assertEquals("compareTo incorrecto", true, r2.compareTo(r1) < 0);
        assertEquals("compareTo incorrecto", true, r2.compareTo(r2) == 0);
        assertEquals("compareTo incorrecto", true, r2.compareTo(r3) < 0);
        assertEquals("compareTo incorrecto", true, r2.compareTo(r4) > 0);

        assertEquals("compareTo incorrecto", true, r3.compareTo(r1) < 0);
        assertEquals("compareTo incorrecto", true, r3.compareTo(r2) > 0);
        assertEquals("compareTo incorrecto", true, r3.compareTo(r3) == 0);
        assertEquals("compareTo incorrecto", true, r3.compareTo(r4) > 0);

        assertEquals("compareTo incorrecto", true, r4.compareTo(r1) < 0);
        assertEquals("compareTo incorrecto", true, r4.compareTo(r2) < 0);
        assertEquals("compareTo incorrecto", true, r4.compareTo(r3) < 0);
        assertEquals("compareTo incorrecto", true, r4.compareTo(r4) == 0);
    }

}
