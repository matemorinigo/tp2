package edu.unlam.avanzada.fantasia;


public class App {
	public static void main(String[] args) {
		juego();
	}
	
	
	public static void juego() {
		
		Ejercito ej1 = new Ejercito(100, "wrives", "propio");
		Ejercito ej2 = new Ejercito(200, "reralopes", "enemigo");
		batalla(ej1,ej2);
		
	}
	
	public static void batalla(Ejercito ejercito1, Ejercito ejercito2) {
		while(!ejercito1.sinUnidades() && !ejercito2.sinUnidades()) {
			
			ejercito1.atacar(ejercito2);
			
			if(!ejercito2.sinUnidades())
				ejercito2.atacar(ejercito1);
		}
		if(!ejercito1.sinUnidades())
			System.out.println("Gano ejercito 1");
		else
			System.out.println("Gano ejercito 2");
	}
	
}
