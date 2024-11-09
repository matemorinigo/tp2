package com.tierrafantasia.test;

import static org.junit.jupiter.api.Assertions.*;

import com.tierrafantasia.entidades.Bando;
import com.tierrafantasia.entidades.Ejercito;
import com.tierrafantasia.entidades.Raza;
import org.junit.jupiter.api.Test;

class TestRazas {

	@Test
	void test() {
		fail("Not yet implemented");
	}


	//test batalla?
	@Test
	void testBatalla(){
		Ejercito propio = new Ejercito(500, Raza.WRIVES, Bando.PROPIO);
		Ejercito enemigo = new Ejercito(500, Raza.WRIVES, Bando.ENEMIGO);



	}


}
