package com.tierrafantasia.entidades;

public class Radaiteran extends Guerrero{

	private int cantAtaquesDados = 0;

	public Radaiteran() {
		super(36, 56);
	}

	@Override
	public void atacar(Guerrero enemigo) {
		int damage = this.getBasicDamage() + (3*this.cantAtaquesDados);
		enemigo.esAtacado(damage);
		this.cantAtaquesDados++;
	}

	@Override
	public void atacar(Ejercito enemigo) {
		if(!this.equals(enemigo.unidades.get(0)) && !this.isDesmayado()) {
			int damage = this.getBasicDamage() + (3*this.cantAtaquesDados);
			enemigo.esAtacado(damage);
			this.cantAtaquesDados++;

		}
	}
	
	
	/*
	@Override
	public void esAtacado(int damage) {
		this.cantAtaquesDados = 0;
		this.setSaludActual(this.getSaludActual()-damage);
	}
	*/
}
