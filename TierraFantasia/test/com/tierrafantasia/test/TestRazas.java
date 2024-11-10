package com.tierrafantasia.test;


import com.tierrafantasia.entidades.*;
import org.junit.jupiter.api.*;


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
	void testAtacar() { //toy laburando con el atacar de los ejercitos, si podes deja este test en stand-by
		nortaichian.atacar(wrives);
		//108 - (18*2) = 72
		//el daño que reciben los wrives es el doble de lo normal
		Assertions.assertEquals(72,wrives.unidades.getFirst().getSaludActual(), 0.00001);
		//La salud del nortaichian no deberia modificarse porque no esta herido
		Assertions.assertEquals(66,nortaichian.unidades.getFirst().getSaludActual(),0.00001);
	}

	@Test
	void testDescansar() {
		//verificamos que el tamaño de nuestro ejercito es cantUnidades
		Assertions.assertEquals(cantUnidades,reralopes.getCantUnidades());
		
		reralopes.descansar(nortaichian);
		
		//despues de descansar vamos a tener la mitad del otro ejercito, por lo que
		//reralopes van a tener 15 unidades mientras que nortaichian van a tener su mitad
		Assertions.assertEquals(cantUnidades*1.5,reralopes.getCantUnidades(),0.00001);
		Assertions.assertEquals((double) cantUnidades*0.5,nortaichian.getCantUnidades(),0.00001);
	}
	
	@Test
	void testUnidadDesmayada() {
		Assertions.assertEquals(cantUnidades,radaiteran.getCantUnidades());
		wrives.atacar(radaiteran);
		//36 salud < 113 daño => unidad radaiteran se desmaya
		Assertions.assertEquals(cantUnidades-1,radaiteran.getCantUnidades());
		//Se queda con una unidad menos
		
	}

	@Test
	void testRazaNula() {
		Assertions.assertThrows(NullPointerException.class,
				() -> {
					new Ejercito(cantUnidades,null,Bando.ALIADO);
				});
	}

	@Test
	void testBatalla(){

	}




}
