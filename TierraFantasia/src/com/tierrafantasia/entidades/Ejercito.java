package com.tierrafantasia.entidades;

import java.util.LinkedList;


public class Ejercito implements UnidadDeCombate{

	public LinkedList<Guerrero> unidades = new LinkedList<Guerrero>();
	
	public Ejercito(int cantUnidades, Raza raza) {
		switch (raza) {
			case WRIVES -> sumaWrives(cantUnidades);
			case RERALOPES -> sumaReralopes(cantUnidades);
			case RADAITERAN -> sumaRadaiteran(cantUnidades);
			case NORTAICHIAN -> sumaNortaichian(cantUnidades);
		}
	}

	
	public int batalla(Ejercito enemigo){
		
		while(!this.sinUnidades() && !enemigo.sinUnidades()){ //pelea a muerte
			this.atacar(enemigo);
			enemigo.atacar(this);
		}
		
		if(!this.sinUnidades()){		//si me quedaron guerreros y hay un herido, se manda atras de todo
			Guerrero guerreroAliado = this.unidades.getFirst();
			if(guerreroAliado.getSaludActual()!=guerreroAliado.getSaludInicial()){
				this.unidades.removeFirst();
				this.unidades.add(guerreroAliado);
			}
			
		}
		return this.getCantUnidades();		//se devuelve la cantidad de sobrevivientes
		 //si no hay sobrevivientes se va a devolver 0 de todas formas
	}
	
	
	//para poder hacer que el metodo fuera comun entre guerrero y ejercito
	//el ejercito aliado que se va a sumar no se pasa por parametro como antes sino que ya tiene que ser
	//un atributo de la instancia, esto ya estaria configurado en el metodo RecorrerMapa y esta en las pruebas
	public void descansar(){
		for (Guerrero guerrero : this.unidades){  //se aplica la funcion descansar a los aliados
			guerrero.descansar();
		}
	}

	public void sumarAliados(Ejercito aliado) {
		int mitad = aliado.unidades.size() / 2;
		for (int i = 0; i < mitad; i++)
			this.unidades.addFirst(aliado.unidades.removeFirst());
	}

	public void atacar(UnidadDeCombate enemigo) {
		if(this != enemigo && !this.sinUnidades()) {
			this.unidades.getFirst().atacar(enemigo);
		}
	}

	public void esAtacado(int damage) {
		Guerrero guerreroAtacado = this.unidades.getFirst();
		guerreroAtacado.esAtacado(damage);
		
		if(guerreroAtacado.isDesmayado())
			this.unidades.removeFirst();
		
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

