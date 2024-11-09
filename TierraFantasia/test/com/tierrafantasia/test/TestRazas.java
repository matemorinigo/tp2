package com.tierrafantasia.test;


import com.tierrafantasia.app.*;
import com.tierrafantasia.entidades.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TestRazas{

	int cantUnidades = 10;
	Ejercito wrives;
	Ejercito reralopes;
	Ejercito radaiteran;
	Ejercito nortaichian;



	@BeforeEach
	public void init(){
		wrives = new Ejercito(cantUnidades,Raza.WRIVES,Bando.ALIADO);
		radaiteran = new Ejercito(cantUnidades,Raza.RADAITERAN,Bando.ALIADO);
		nortaichian = new Ejercito(cantUnidades,Raza.NORTAICHIAN,Bando.ALIADO);
		reralopes = new Ejercito(cantUnidades,Raza.RERALOPES,Bando.ALIADO);
	}

	@Test
	void test(){
		//Un Wrives tiene una salud inicial de 108 y ocasiona un daño básico de 113 puntos
		//Una Reralopes tiene una salud inicial de 53 y ocasiona un daño básico de 27 puntos.
		//Una Radaiteran tiene una salud inicial de 36 y ocasiona un daño básico de 56 puntos.
		//Un Nortaichian tiene una salud inicial de 66 y ocasiona un daño básico de 18 puntos.


		

	}




}
