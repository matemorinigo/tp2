package com.tierrafantasia.entidades;

public class Wrives extends Guerrero{
	
	private boolean fueAtacado = true;
	private int cantAtaquesDados = 0;
	
	public Wrives(Bando bando) {
		super(108, 113, bando);
		
	}

	@Override
	public void atacar(Guerrero enemigo) {
		if(!this.equals(enemigo) && !this.isDesmayado() && this.fueAtacado) {
			int damage = this.getBasicDamage();
			
			if(this.cantAtaquesDados == 2) {
				damage *= 2;
				this.cantAtaquesDados = 0;
			} else {
				this.cantAtaquesDados++;
			}
			
			enemigo.esAtacado(damage);
		}
	}
	
	@Override
	public void atacar(Ejercito enemigo) {
		if(!this.equals(enemigo.unidades.getFirst()) && !this.isDesmayado() && this.fueAtacado) {
			int damage = this.getBasicDamage();
			
			if(this.cantAtaquesDados == 2) {
				damage *= 2;
				this.cantAtaquesDados = 0;
			} else {
				this.cantAtaquesDados++;
			}
			
			enemigo.esAtacado(damage);
		}
	}
	
	
	@Override
	public void esAtacado(int damage) {
		if(!this.isDesmayado()) {
			double totalDamage = this.getSaludActual()-(damage*2);
			this.setSaludActual(totalDamage);
			this.fueAtacado = true;
			if(this.getSaludActual() <= 0) {
				this.setSaludActual(0);
				this.setDesmayado(true);
			}
		}
	}
	
	@Override
	public void descansar() {
		if(!this.isDesmayado()) {
			double aux = this.getSaludActual();
			this.fueAtacado = false;
			this.setSaludInicial(this.getSaludInicial()+50);
			
			if(aux+50 <= this.getSaludInicial())
				this.setSaludActual(aux+50);
			
			
		}
	}
}
