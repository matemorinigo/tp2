package com.tierrafantasia.test;



import org.junit.jupiter.api.Test;

import com.tierrafantasia.app.App;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class TestApp {
	
	@Test
	void testMain() {
		
		String ruta = "../TierraFantasia/res/ejemplo1.txt";
		
		// Convertimos el string a un InputStream
        InputStream entrada = new ByteArrayInputStream(ruta.getBytes());

        // Guardamos la referencia original de System.in para restaurarla después
        InputStream originalSystemIn = System.in;
        
        // Redirigimos System.in a nuestro InputStream simulado
        System.setIn(entrada);

		
		
		try {
			App.main(null);
		} finally {
            // Restauramos System.in a su valor original para no afectar otros tests
            System.setIn(originalSystemIn);
        }
	}
	
	@Test
	void testMainException() {
		InputStream originalSystemIn = System.in;
		System.setIn(null);
		
		try {
			App.main(null);
		} finally {
            // Restauramos System.in a su valor original para no afectar otros tests
            System.setIn(originalSystemIn);
        }
		
	}

}
