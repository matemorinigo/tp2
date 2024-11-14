package com.tierrafantasia.entidades;

import java.util.Random;

public class Reralopes extends Guerrero{
	private int concentracion = 0;
	private int ataquesErrados = 0;
	private int ataquesTotales = 0;
	

	public Reralopes() {
		super(53, 27);
	}

	@Override
	public void atacar(UnidadDeCombate enemigo) {
		if (ataquesTotales >= 4) {
			ataquesTotales = 0;
			ataquesErrados = 0;
		}
		// Erra 2 de cada 4 ataques
		boolean acierto;
		
		//si ya erro 2 ataques los proximos van a ser aciertos
		if(ataquesErrados > 1)
			acierto = true;
		//si ataco 3 veces y erro menos 
		else if(ataquesTotales >= 2 && ataquesErrados < 2)
			acierto = false;
		else 
			acierto = ataqueAcertado();
		
		//acierto = ataquesErrados < 2 ? ataqueAcertado() : true;

		int damage = getBasicDamage();
		if (concentracion > 0) {
			damage *= 2;
			concentracion--;
		}
		ataquesTotales++;
		if (acierto) {
			enemigo.esAtacado(damage);
		} else {
			ataquesErrados++;
		}
	}


	@Override
	public void esAtacado(int damage) {
		if(!this.isDesmayado()) {
			this.setSaludActual(this.getSaludActual()-damage);
			this.concentracion = 0;

			if(this.getSaludActual() <= 0) {
				this.setSaludActual(0);
				this.setDesmayado(true);
			}
		}
	}

	@Override
	public void descansar() {
		this.concentracion = 3;
	}

	public  boolean ataqueAcertado() {
		Random random = new Random();
		return random.nextBoolean();
	}

	public int getConcentracion() {
		return concentracion;
	}

	public int getAtaquesErrados() {
		return ataquesErrados;
	}

	public int getAtaquesTotales() {
		return ataquesTotales;
	}

}
