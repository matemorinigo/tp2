package edu.unlam.avanzada.fantasia;

import edu.unlam.avanzada.utils.Heap;


public class Ejercito {
	//private LinkedList<Guerrero> unidades;
	//private Bando bando;
	public Heap<Guerrero> unidades = new Heap<>();
	
	public Ejercito(int cantUnidades, String raza, String bando) {
		Bando aux = null;
		if(bando.toLowerCase().contains("propio"))
			aux = Bando.PROPIO;
		else if(bando.toLowerCase().contains("aliado"))
			aux = Bando.ALIADO;
		else if(bando.toLowerCase().contains("enemigo"))
			aux = Bando.ENEMIGO;
		
		if(raza.toLowerCase().contains("wrives"))
			sumaWrives(cantUnidades, aux);
		else if(raza.toLowerCase().contains("reralopes"))
			sumaReralopes(cantUnidades, aux);
		else if(raza.toLowerCase().contains("radaiteran"))
			sumaRadaiteran(cantUnidades, aux);
		else if(raza.toLowerCase().contains("nortaichian"))
			sumaNortaichian(cantUnidades, aux);
		
		//this.bando = Bando.valueOf(bando);
	}
	
	
	
	public void atacar(Ejercito enemigo) {
		
		this.unidades.getFirst().atacar(enemigo);
	}
	
	public void descansar() {
		Guerrero unidad = null;
		Heap<Guerrero> aux = new Heap<>();
		
		while(!this.unidades.isEmpty()) {
			unidad = this.unidades.poll();
			unidad.descansar();
			aux.add(unidad);
		}
		
		this.unidades = aux;
	}
	
	public void esAtacado(int damage) {
		Guerrero unidad = this.unidades.getFirst();
		unidad.esAtacado(damage);
		
		if(unidad.isDesmayado()) 
			this.unidades.poll();
	}
	
	public boolean sinUnidades() {
		return this.unidades.isEmpty();
	}
	
	private void sumaWrives(int cantASumar, Bando bando) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Wrives(bando));
	}
	
	private void sumaReralopes(int cantASumar, Bando bando) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Reralopes(bando));
	}
	
	private void sumaRadaiteran(int cantASumar, Bando bando) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Radaiteran(bando));
	}
	
	private void sumaNortaichian(int cantASumar, Bando bando) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Nortaichian(bando));
	}
	
}
