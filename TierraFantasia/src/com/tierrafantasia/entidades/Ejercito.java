package com.tierrafantasia.entidades;

//import com.tierrafantasia.utils.Heap;

import java.util.ArrayList;
import java.util.List;


public class Ejercito {

	//public Heap<Guerrero> unidades = new Heap<>();
	public List<Guerrero> unidades = new ArrayList<Guerrero>();
	public  Bando bando;
	public Ejercito(int cantUnidades, Raza raza, Bando bando) {

		this.bando = bando;
		switch (raza) {
			case WRIVES -> sumaWrives(cantUnidades);
			case RERALOPES -> sumaReralopes(cantUnidades);
			case RADAITERAN -> sumaRadaiteran(cantUnidades);
			case NORTAICHIAN -> sumaNortaichian(cantUnidades);
		}
	}
	//carga la Lista de guerreros

	//El ejercito agarra al primer guerrero, este guerrero ataca al ejercito enemigo
//	public void atacar(Ejercito enemigo) {
//		Guerrero unidad = this.unidades.get(0);
//		if (enemigo.sinUnidades() && unidad.getSaludActual() != unidad.getSaludInicial()) { //si no hay mas enemigos y el soldado esta herido, va al final de la cola
//			this.unidades.remove(0);
//			this.unidades.add(unidad);
//		} else {
//			unidad.atacar(enemigo);
//		}

	// no sé si hace falta preguntar si no hay enemigos, a este //método siempre llega cuando sí hay, y creo que otra la condición hace que nunca pelee un herido y entraría en bucle

	public boolean batalla(Ejercito enemigo){
		Guerrero guerreroAliado = this.unidades.getFirst();
		Guerrero guerreroEnemigo = enemigo.unidades.getFirst();

		while(!this.sinUnidades() && !enemigo.sinUnidades()){

			while(!guerreroAliado.isDesmayado() || !guerreroEnemigo.isDesmayado()){
				guerreroAliado.atacar(guerreroEnemigo);
				if(!guerreroEnemigo.)
			}

		}


		return false;
	}

	/*para poder hacer algo como
	mientras(hayaEjercito1, hayaEjercito2) {
		guerrero1 = ejercito1.unidad.getFirst()
		guerrero2 = ejercito2.unidad.getFirst()
		while(!guerrero1.desmayado() || !guerrero2.desmayado()){
		guerrero1.atacar(guerrero2)
		guerrero2.atacar(guerrero1)
}
	 */
//https://docs.google.com/document/d/1qtNQxtsFG6et27j49AROzUBzbk0NxiWSIVQeyAyeLsY/edit?usp=sharing el link al docs, peguenlo en wpp q no tengo bateria gracias


	public void descansar(Ejercito aliado) {
		
		for (Guerrero guerrero : this.unidades){  //se aplica la funcion descansar a los aliados
			guerrero.descansar();
		}

		int mitad = aliado.unidades.size()/2;
		for(int i = 0; i < mitad; i++)
			this.unidades.addFirst(aliado.unidades.removeFirst());
		
	}

	//cuando un ejercito es atacado recibe el daño y lo pasa a su primer guerrero en la cola/heap
	public void esAtacado(int damage) {
		Guerrero unidad = this.unidades.get(0);
		unidad.esAtacado(damage);

		if(unidad.isDesmayado())  //si se queda sin salud, se saca de la cola
			this.unidades.remove(unidad);
	}

//	public void batalla(Ejercito enemigo) {
//		while(!this.sinUnidades() && !enemigo.sinUnidades()) {
//			this.atacar(enemigo);
//			if(!enemigo.sinUnidades())
//				enemigo.atacar(this);
//		}
//	}

	public boolean sinUnidades() {
		return this.unidades.isEmpty();
	}

	public int getCantUnidades(){
		return unidades.size();
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

