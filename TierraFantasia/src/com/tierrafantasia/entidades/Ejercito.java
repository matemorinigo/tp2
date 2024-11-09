package com.tierrafantasia.entidades;

import com.tierrafantasia.utils.Heap;

import java.util.List;


public class Ejercito {

	//public Heap<Guerrero> unidades = new Heap<>();
	public List<Guerrero> unidades;
	public  Bando bando;
	public Ejercito(int cantUnidades, Raza raza, Bando bando) {

		this.bando = bando;
		switch(raza){
			case WRIVES -> sumaWrives(cantUnidades);
			case RERALOPES -> sumaReralopes(cantUnidades);
			case RADAITERAN -> sumaRadaiteran(cantUnidades);
			case NORTAICHIAN -> sumaNortaichian(cantUnidades);
		}

	} //carga la Lista de guerreros

	//El ejercito agarra al primer guerrero, este guerrero ataca al ejercito enemigo
	public void atacar(Ejercito enemigo) {
		Guerrero unidad = this.unidades.get(0);
		if (enemigo.sinUnidades() && unidad.getSaludActual() != unidad.getSaludInicial()) { //si no hay mas enemigos y el soldado esta herido, va al final de la cola
			this.unidades.remove(0);
			this.unidades.add(unidad);
		} else {
			unidad.atacar(enemigo);
		}
		///aca habria que hacer un return victoria cuando se queda sin enemigos el rival (esta en el main tambien, hay que ver donde va a parar la win
	}


	public void descansar(Ejercito aliado) {
		Guerrero aux;
		for (Guerrero guerrero : this.unidades){  //se aplica la funcion descansar a los aliados
			guerrero.descansar();
		}

		int mitad = aliado.unidades.size()/2;
		List<Guerrero> mitadAliada = aliado.unidades.subList(0,mitad);
		this.unidades.addAll(0,mitadAliada);
	}


	//cuando un ejercito es atacado recibe el daño y lo pasa a su primer guerrero en la cola/heap
	public void esAtacado(int damage) {
		Guerrero unidad = this.unidades.get(0);
		unidad.esAtacado(damage);

		if(unidad.isDesmayado())  //si se queda sin salud, se saca de la cola
			this.unidades.remove(unidad);
	}

	public boolean sinUnidades() {
		return this.unidades.isEmpty();
	}

	private void sumaWrives(int cantASumar) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Wrives());
	}

	private void sumaReralopes(int cantASumar) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Reralopes());
	}

	private void sumaRadaiteran(int cantASumar) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Radaiteran());
	}

	private void sumaNortaichian(int cantASumar) {
		for(int i = 0; i < cantASumar; i++)
			unidades.add(new Nortaichian());
	}

}

