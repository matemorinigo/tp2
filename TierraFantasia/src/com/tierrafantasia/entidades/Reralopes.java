package com.tierrafantasia.entidades;

public class Reralopes extends Guerrero{
	private int concentracion = 0;
	
	public Reralopes(Bando bando) {
		super(53, 27, bando);
		
	}

	@Override
	public void atacar(Guerrero enemigo) {
		if(!this.equals(enemigo) && !this.isDesmayado()) {
			int damage = this.getBasicDamage();
			
			if(this.concentracion > 0) {
				damage *= 2;
				this.concentracion--;
			}
			
			enemigo.esAtacado(damage);
		}
	}
	
	@Override
	public void atacar(Ejercito enemigo) {
		if(!this.equals(enemigo.unidades.getFirst()) && !this.isDesmayado()) {
			int damage = this.getBasicDamage();
			
			if(this.concentracion > 0) {
				damage *= 2;
				this.concentracion--;
			}
			
			enemigo.esAtacado(damage);
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
}
