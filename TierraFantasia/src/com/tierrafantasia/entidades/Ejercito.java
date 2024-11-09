package com.tierrafantasia.entidades;

import com.tierrafantasia.utils.Heap;


public class Ejercito {

	public Heap<Guerrero> unidades = new Heap<>();
	//public List<Guerrero> unidades;

	public Ejercito(int cantUnidades, Raza raza, Bando bando) {

		switch(raza){
			case WRIVES -> sumaWrives(cantUnidades, bando);
			case RERALOPES -> sumaReralopes(cantUnidades, bando);
			case RADAITERAN -> sumaRadaiteran(cantUnidades, bando);
			case NORTAICHIAN -> sumaNortaichian(cantUnidades, bando);
		}

	} //carga la Lista de guerreros

	//El ejercito agarra al primer guerrero, este guerrero ataca al ejercito enemigo
	public void atacar(Ejercito enemigo) {
		Guerrero unidad = this.unidades.getFirst();
		if (enemigo.sinUnidades() && unidad.getSaludActual() != unidad.getSaludInicial()) {

		} else {
			this.unidades.getFirst().atacar(enemigo);
		}

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
	//cuando un ejercito es atacado recibe el daño y lo pasa a su primer guerrero en la cola/heap
	public void esAtacado(int damage) {
		Guerrero unidad = this.unidades.getFirst();
		unidad.esAtacado(damage);
		
		if(unidad.getSaludActual() <=0 )  //si se queda sin salud, se saca de la cola
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
