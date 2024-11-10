package com.tierrafantasia.entidades;

public class Nortaichian extends Guerrero{

	private int esPiedra = 0;
	private int furia = 0;

	public Nortaichian() {
		super(66, 18);
	}

	@Override
	public void atacar(UnidadDeCombate enemigo) {
		if(this.esPiedra == 0) {
			int damage = this.getBasicDamage();
			double salud = this.getSaludActual()*1.04;

			if(this.furia > 0)
				damage*=2;

			enemigo.esAtacado(damage);

			if(this.getSaludInicial() > salud)
				this.setSaludActual(salud);
			else
				this.setSaludActual(this.getSaludInicial());
		}
		else {
			this.esPiedra--;
		}
	}


	@Override
	public void esAtacado(int damage) {
		if(!this.isDesmayado()) {
			if(esPiedra > 0) {
				damage *= 0.5;
				this.esPiedra--;
			}
			this.setSaludActual(this.getSaludActual()-damage);
			this.furia = 2;
		}

	}

	@Override
	public void descansar() {
		if(this.esPiedra <= 0) {
			this.setSaludActual(this.getSaludInicial());
			this.esPiedra = 2;
		}

	}


}