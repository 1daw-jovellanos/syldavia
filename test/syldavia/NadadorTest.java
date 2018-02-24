package syldavia;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.*;
import java.util.*;

/**
 * Pruebas unitarias de la clase Nadador
 * @author victor
 * @version 2018-Feb-24.0
 */
public class NadadorTest {
    
    Nadador n1, n2, n3;
    Resultado r1, r2, r3, r4;
    int thisYear;
    
    
    public NadadorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        thisYear = LocalDate.now().getYear();
        n1 = new Nadador(" José  -  Alfredo \"El Torrijas\"  ", 
                " Nuñez de   Garcia-Flojera",
                LocalDate.of(thisYear-14, 1, 1)
        );
        r1 = new Resultado(Estilo.BRAZA, 200, 244, 91);
        r2 = new Resultado(Estilo.MARIPOSA, 250, 283, 119);
        r3 = new Resultado(Estilo.ESPALDA, 250, 284, 119);
        r4 = new Resultado(500, 487, 222);
        n1.addResultado(r1);
        n1.addResultado(r2);
        n1.addResultado(r3);
        n1.addResultado(r4);
    }
    
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testConstructors() {
        boolean hayExcepcion;
        Nadador instance;

        
        hayExcepcion = false;
        try {
            instance = new Nadador(" - -- --- ", "ape", LocalDate.of(1900, Month.MARCH, 1));
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no lanza excepción si nombre vacío", true, hayExcepcion);

                
        hayExcepcion = false;
        try {
            instance = new Nadador("nom", " - -- --- ", LocalDate.of(1900, Month.MARCH, 1));
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no lanza excepción si apellido está vacío", true, hayExcepcion);

                
        hayExcepcion = false;
        try {
            instance = new Nadador(" nom ", "ape", LocalDate.of(thisYear - 14, Month.JUNE, 15));
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("Constructor no lanza excepción nadador < 14 años", true, hayExcepcion);
    }

    @Test
    public void testRestriccionesAccesores() {
        boolean hayExcepcion;

        
        hayExcepcion = false;
        try {
            n1.setNombre(" * --  ### ");
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("setNombre no lanza excepción si nombre está vacío", true, hayExcepcion);

        hayExcepcion = false;
        try {
            n1.setApellidos(" * --  ### ");
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("setApellidos no lanza excepción si apellido está vacío", true, hayExcepcion);

        hayExcepcion = false;
        try {
            n1.setFechaNacimiento(LocalDate.of(thisYear-14, Month.JUNE, 15)); // 13 años
        } catch (IllegalArgumentException ex) {
            hayExcepcion = true;
        }
        assertEquals("setFechaNacimiento no lanza excepcion si nadador es menor", true, hayExcepcion);
    }
    
    
    @Test
    public void testAccesors() {
        assertEquals("Nombre no es como se espera", "JOSÉ ALFREDO EL TORRIJAS", n1.getNombre());
        assertEquals("Apellidos no son como se espera", "NUÑEZ DE GARCIAFLOJERA", n1.getApellidos());
        assertEquals("Fecha de nacimiento no es como se espera", LocalDate.of(thisYear-14, 1, 1), n1.getFechaNacimiento());
        n1.setNombre(" Juañelo  ## Tinajas");
        assertEquals("Nombre no es como se espera", "JUAÑELO TINAJAS", n1.getNombre());
        n1.setApellidos(" Tomillero Güeón  de Fuenlabr@da ");
        assertEquals("Apellidos no son como se espera", "TOMILLERO GÜEÓN DE FUENLABRDA", n1.getApellidos());
        n1.setFechaNacimiento(LocalDate.MIN);
        assertEquals(LocalDate.MIN, n1.getFechaNacimiento());
    }
    
    @Test
    public void testCategoriaEdad() {
        n1.setFechaNacimiento(LocalDate.of(thisYear - 18, Month.JUNE, 15)); // 17 años
        assertEquals("Categoria incorrecta. Nadador de 17 años", CategoriaEdad.JUNIOR, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 14, Month.JUNE, 13)); // 14 años
        assertEquals("Categoria incorrecta. Nadador de 14 años", CategoriaEdad.JUNIOR, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 18, Month.JUNE, 13)); // 18 años
        assertEquals("Categoria incorrecta. Nadador de 18 años", CategoriaEdad.CLASS, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 26, Month.JUNE, 15)); // 25 años
        assertEquals("Categoria incorrecta. Nadador de 25 años", CategoriaEdad.CLASS, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 26, Month.JUNE, 10)); // 26 años
        assertEquals("Categoria incorrecta. Nadador de 26 años", CategoriaEdad.MASTER, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 44, Month.MAY, 1)); // 443 años
        assertEquals("Categoria incorrecta. Nadador de 44 años", CategoriaEdad.MASTER, n1.getCategoriaEdad());
        n1.setFechaNacimiento(LocalDate.of(thisYear - 444, Month.JUNE, 18)); // 443 años
        assertEquals("Categoria incorrecta. Nadador de 443 años", CategoriaEdad.MASTER, n1.getCategoriaEdad());
    }
    
    @Test
    public void testResultados() {
        n2 = new Nadador("prueba", "prueba", LocalDate.MIN);
        assertNull("El mejor resultado debe ser null si no hay resultados", n2.getMejorResultado());
        assertNull("El peor resultado debe ser null si no hay resultados", n2.getPeorResultado());
        //Si solo hay un resultado, ese es el mejor y el peor.
        n2.addResultado(r1);
        assertEquals(r1, n2.getMejorResultado());
        assertEquals(r1, n2.getPeorResultado());
        
        assertEquals(r4, n1.getMejorResultado()); //El mejor resultado de n1 es r3. Swolf 37
        assertEquals(r1, n1.getPeorResultado()); //El peor resultado de n1 es r1. Swolf 43     
    }
    
    @Test
    public void testFilterPorEstilo() {
        
        assertEquals(1, n1.filterPorEstilo(Estilo.ESPALDA).size());
        assertEquals(1, n1.filterPorEstilo(Estilo.BRAZA).size());
        assertEquals(1, n1.filterPorEstilo(Estilo.LIBRE).size());
        assertEquals(1, n1.filterPorEstilo(Estilo.MARIPOSA).size());
        
        
        Random g = new Random();
        int n = g.nextInt(500)+10;
        int numBraza = 1, numEspalda = 1;
        int checksumBraza = 0, checksumEspalda = 0;
        
        // Generar resultados de braza y espalda al azar.
        for (int i = 1; i < n; i++) {
            if (g.nextBoolean()) {
                n1.addResultado(new Resultado(Estilo.BRAZA, i * 25, i * 30 + i, i * 18 - i));
                numBraza++;
                checksumBraza += (i * 25) + (i * 30 + i) + (i * 18 - i); // Suma de comprobacion: distancia+tiempo+brazadas.
            } else {
                n1.addResultado(new Resultado(Estilo.ESPALDA, i * 25, i * 30 + i, i * 18 - i));
                numEspalda++;
                checksumEspalda += (i * 25) + (i * 30 + i) + (i * 18 - i);
            }
        }
        
        //Comprobar a ver si sale la misma cantidad de espalda que han entrado
        Collection<Resultado> c1 = n1.filterPorEstilo(Estilo.ESPALDA);
        assertEquals(numEspalda, c1.size());
        
        //braza
        c1 = n1.filterPorEstilo(Estilo.BRAZA);
        assertEquals(numBraza, c1.size());
        
        //Comprobar también el checksumTiempo de la braza
        int checksum = 0;
        for (Resultado r : c1) {
            checksum += r.getDistancia() + r.getTiempo() + r.getBrazadas();
        }
        checksum -= r1.getDistancia() + r1.getTiempo() + r1.getBrazadas();
        assertEquals("Los resultados al filtrar por estilo no son los esperados (fallo de checksum)", 
                checksumBraza, checksum 
                );
        
    }
    
    @Test
    public void testFilterPorTiempo() {
        n2 = new Nadador("nombre", "apellidos", LocalDate.of(thisYear-40,1,1));
        Random g = new Random();

        // añade 1000 resultados, con tiempos consecutivos, de 1 a 1000
        int n = 1000;
        for (int i = 0; i < n; i ++) {
            n2.addResultado(new Resultado(1000, i + 1, g.nextInt(1000)+1));
        }
        
        
        // Filtrar y calcular checksum
        int min = g.nextInt(400) + 10;
        int max = g.nextInt(400)+ min;
        
        // Como tienen tiempos consecutivos, tiene que haber tantos como la diferencia
        // entre min y max
        Resultado[] res = n2.filterPorTiempo(min, max);
        assertEquals("La cantidad de resultados filtrados por tiempo no es la esperada", max - min, res.length );
        
        // chequeamos que sean los resultados correctos, sumando sus tiempos.
        int checksum = 0;
        for (Resultado r : res) {
            checksum += r.getTiempo();
        }
        
        // Suma de todos los números entre min y max con la fórmula de Gauss
        int suma = (((max - 1) * max)/ 2) - ((min * (min - 1)) / 2); 
        

        assertEquals("Los resultados filtrados por tiempo no son los esperados (fallo de checksum)", suma , checksum );
        
    }
    
    @Test
    public void testGetVelocidades() {
        double[] v = {1.026, 0.883, 0.880, 0.819};
        assertArrayEquals("Velocidades no son las esperadas", v, n1.getVelocidades(), 0.001); // Una centésima de error 
    }
    
    @Test
    public void testDeletePorMetros() {
        int borrados = n1.deletePorMetros(200,250);
        assertEquals("La cantidad de borrados no es la esperada", 1, borrados);
        borrados = n1.deletePorMetros(200,500);
        assertEquals("La cantidad de borrados no es la esperada", 2, borrados);
        borrados = n1.deletePorMetros(200,250);
        assertEquals("La cantidad de borrados no es la esperada", 0, borrados);
        assertEquals("Solo debería quedar uno y tendría que ser el mismo", true, n1.getMejorResultado().getSwolf() == n1.getPeorResultado().getSwolf());
    }

    @Test
    public void testDeletePorTiempo() {
        int borrados = n1.deletePorTiempo(222,284);
        assertEquals("La cantidad de borrados no es la esperada", 2, borrados);
        borrados = n1.deletePorTiempo(284,487);
        assertEquals("La cantidad de borrados no es la esperada", 1, borrados);
        borrados = n1.deletePorTiempo(200,250);
        assertEquals("La cantidad de borrados no es la esperada", 0, borrados);
        assertEquals("Solo debería quedar uno y tendría que ser el mismo", true, n1.getMejorResultado().getSwolf() == n1.getPeorResultado().getSwolf());    
    }

    @Test
    public void testHayLargaDistancia() {
        assertEquals(false, n1.hayLargaDistancia());
        n2 = new Nadador("nombre", "apellido", LocalDate.of(1900,1,1) );
        assertEquals(false, n2.hayLargaDistancia());
        n2.addResultado(new Resultado(1000,1900,1400));
        n2.addResultado(r1);
        n2.addResultado(r2);
        n2.addResultado(r3);
        assertEquals(true, n2.hayLargaDistancia());   
    }
    
}
