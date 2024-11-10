package com.tierrafantasia.entidades;

public class Radaiteran extends Guerrero{

		private int cantAtaquesDados = 0;

	public Radaiteran() {
		super(36, 56);
	}

	@Override
	public void atacar(UnidadDeCombate enemigo) {
		int damage = this.getBasicDamage() + (3*this.cantAtaquesDados);
		enemigo.esAtacado(damage);
		this.cantAtaquesDados++;
	}


}
