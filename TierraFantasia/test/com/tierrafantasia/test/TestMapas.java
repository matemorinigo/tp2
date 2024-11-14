package com.tierrafantasia.test;

import com.tierrafantasia.entidades.*;
import org.junit.jupiter.api.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMapas {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeAll
    public static void setUp(){
        double[][] mat = {
                {0,10,20,Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY,0,5,Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0,7},
                {Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY,0}};
        Mapa mapa = Mapa.getMapaUnico();
        mapa.setMatAdy(mat);
        mapa.setPuebloInicial(1);
        mapa.setPuebloFinal(4);

        HashMap<Integer, Pueblo> pueblos = new HashMap<>();

        pueblos.put(1, new Pueblo(new Ejercito(100, Raza.WRIVES), Bando.PROPIO));
        pueblos.put(2, new Pueblo(new Ejercito(30, Raza.RERALOPES), Bando.ALIADO));
        pueblos.put(3, new Pueblo(new Ejercito(10, Raza.NORTAICHIAN), Bando.ENEMIGO));
        pueblos.put(4, new Pueblo(new Ejercito(60, Raza.NORTAICHIAN), Bando.ENEMIGO));

        mapa.setPueblos(pueblos);
    }

    @Test
    void testDijkstra() {
        Mapa mapa = Mapa.getMapaUnico();
        Predecesor_Distancia pd = mapa.dijkstra(1);
        assertEquals(22, pd.getDistancia(4));
        assertEquals(3, pd.getPredecesor(4));
        assertEquals(2, pd.getPredecesor(3));
        assertEquals(1, pd.getPredecesor(2));
    }

    @Test
    void testRecorrido(){
        Mapa mapa = Mapa.getMapaUnico();
        Predecesor_Distancia pd = mapa.dijkstra(1);
        mapa.recorrerMapa(pd);

        assertEquals("La mision no es factible, perderiamos contra el pueblo 3", outContent.toString().trim());
    }

}
