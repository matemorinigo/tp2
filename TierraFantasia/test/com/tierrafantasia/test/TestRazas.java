package com.tierrafantasia.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.tierrafantasia.entidades.*;


class TestRazas {

	int cantUnidades = 10;

	Ejercito wrives;
	/*
	Un Wrives tiene una salud inicial de 108 y ocasiona un daño básico de 113 puntos.
	Cuando ataca, lo hace con 2 veces su daño, cada 2 ataques.
	Al recibir un ataque recibe 2 veces el daño, ya que no lleva armadura.
	Cuando descansa, medita, y como considera la violencia como algo malo,
	se rehúsa a atacar hasta que lo ataquen.
	Gracias a esto, aumenta su salud y su salud máxima en 50.
	*/

	Ejercito reralopes;
	/*
	Una Reralopes tiene una salud inicial de 53 y ocasiona un daño básico de 27 puntos.
	Cuando ataca, erra 2 de cada 4 ataques.
	Al recibir un ataque se desconcentra y sus ataques vuelven al valor normal inicial.
	Cuando descansa, se concentra y sus próximos 3 ataques
	(de esa unidad) dañan el doble del valor correspondiente.
	*/

	Ejercito radaiteran;
	/*
	Una Radaiteran tiene una salud inicial de 36 y ocasiona un daño básico de 56 puntos.
	Cuando ataca, lo hace cada vez con más fuerza (3 de daño extra x la cantidad de ataques dados).
	Al recibir un ataque lo hace normalmente.
	Cuando descansa, no le sucede nada.
	*/

	Ejercito nortaichian;
	/*
	Un Nortaichian tiene una salud inicial de 66 y ocasiona un daño básico de 18 puntos.
	Cuando ataca, se cura un 4 por ciento de su salud.
	Al recibir un ataque se enfurece y sus ataques multiplican por 2 su daño (dura 2 turnos propios).
	Cuando descansa, recupera toda su salud, pero se vuelve de piedra por 2 turnos (contiguos),
	lo que hace que no pueda atacar, pero reduce el daño entrante en 1/2.
	*/

	@BeforeEach
	public void setup() {
		wrives = new Ejercito(cantUnidades, Raza.WRIVES, Bando.ALIADO);
		reralopes = new Ejercito(cantUnidades, Raza.RERALOPES, Bando.ALIADO);
		radaiteran = new Ejercito(cantUnidades, Raza.RADAITERAN, Bando.ALIADO);
		nortaichian = new Ejercito(cantUnidades, Raza.NORTAICHIAN, Bando.ALIADO);
	}

	@Test
	void testBatalla() {
		nortaichian.batalla(reralopes);
		radaiteran.batalla(wrives);
		assertEquals(0, wrives.getCantUnidades());
	}
	
	@Test
	void testBatallaSinUnidades() {
		radaiteran = new Ejercito(0,Raza.RADAITERAN, Bando.ALIADO);
		nortaichian = new Ejercito(0, Raza.NORTAICHIAN, Bando.ALIADO);
		radaiteran.batalla(nortaichian);
	}
	
	@Test
	void testDescansar() {
		Pueblo puebloWrives = new Pueblo(wrives,wrives.bando);
		nortaichian.setAliado(puebloWrives.ejercito);
		nortaichian.descansar();
		assertEquals(cantUnidades*1.5, nortaichian.getCantUnidades());
		assertEquals(cantUnidades*0.5, wrives.getCantUnidades());
		
		wrives.setAliado(radaiteran);
		wrives.descansar();
		
		reralopes.setAliado(nortaichian);
		reralopes.descansar();
		nortaichian.batalla(radaiteran);
	}
	
	@Test
	void testRazaNull() {
		assertThrows(NullPointerException.class, () -> {
			new Ejercito(cantUnidades, null, Bando.ALIADO);
		});
	}
	
	@Test
	void testGuerreros() {
		Guerrero g1 = new Guerrero(100,50);
		Guerrero g2 = new Guerrero(100,50);
	
		g1.atacar(g2);
		
		assertEquals(50, g2.getSaludActual());
		g2.descansar();
		assertEquals(50, g2.getSaludActual());
		//descansar no hace nada
	}
	
	@Test
	void testWrives() {
		for(int i = 0; i < 3; i++)
			wrives.atacar(nortaichian);
		
	}
	
	@Test
	void testReralopes() {
		Reralopes g1 = new Reralopes();
		
		for(int i = 0; i < 5; i++)
			g1.atacar(wrives);
		
		g1.descansar();
		
		for(int i = 0; i < 5; i++)
			g1.atacar(wrives);
		
	}
	
	
}
