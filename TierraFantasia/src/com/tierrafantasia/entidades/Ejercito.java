package com.tierrafantasia.entidades;

import com.tierrafantasia.utils.Heap;


public class Ejercito {
	//private LinkedList<Guerrero> unidades;
	//private Bando bando;
	public Heap<Guerrero> unidades = new Heap<>();
	
	public Ejercito(int cantUnidades, Raza raza, Bando bando) {

		switch(raza){
			case WRIVES -> sumaWrives(cantUnidades, bando);
			case RERALOPES -> sumaReralopes(cantUnidades, bando);
			case RADAITERAN -> sumaRadaiteran(cantUnidades, bando);
			case NORTAICHIAN -> sumaNortaichian(cantUnidades, bando);
		}

	}

	
	public void atacar(Ejercito enemigo) {
		this.unidades.getFirst().atacar(enemigo);
	}
	
	public void descansar(Ejercito aliado) {
		Guerrero unidad = null;
		Heap<Guerrero> aux = new Heap<>();
		
		while(!this.unidades.isEmpty()) {
			unidad = this.unidades.poll();
			unidad.descansar();
			aux.add(unidad);
		}
		for(int i = 0; i < aliado.unidades.size()/2; i++) 
			aux.add(aliado.unidades.poll());
		
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
