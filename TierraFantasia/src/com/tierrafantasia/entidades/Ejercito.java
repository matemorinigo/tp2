package com.tierrafantasia.entidades;

//import com.tierrafantasia.utils.Heap;

import java.util.ArrayList;
import java.util.List;


public class Ejercito {

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


	public int batalla(Ejercito enemigo){
		Guerrero guerreroAliado = this.unidades.getFirst();
		Guerrero guerreroEnemigo = enemigo.unidades.getFirst();

		while(!this.sinUnidades() && !enemigo.sinUnidades()){ //pelea a muerte

			while(!guerreroAliado.isDesmayado()){		//mientras no se desmaye el nuestro que pelee con el otro
				guerreroAliado.atacar(guerreroEnemigo);
				if(!guerreroEnemigo.isDesmayado()){			// si el otro se desmaya, pasa el siguiente, y sino le pega al nuestro
					guerreroEnemigo.atacar(guerreroAliado);
				} else {
					enemigo.unidades.removeFirst();
					guerreroEnemigo = enemigo.unidades.getFirst();
				}
			}

			this.unidades.removeFirst();			//si se desmayo el nuestro, se reasigna
			guerreroAliado = this.unidades.getFirst();

		}
		if(!this.sinUnidades()){		//si me quedaron peleadores y hay un herido, se manda atras de tod0
			if(guerreroAliado.getSaludActual()!=guerreroAliado.getSaludInicial()){
				this.unidades.removeFirst();
				this.unidades.add(guerreroAliado);
			}
			return this.getCantUnidades();		//se devuelve la cantidad de sobrevivientes
		}
		return 0; //si no hay sobrevivientes se devuelve 0
	}




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

