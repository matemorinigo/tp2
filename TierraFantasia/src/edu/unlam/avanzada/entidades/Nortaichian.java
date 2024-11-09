package edu.unlam.avanzada.entidades;

public class Nortaichian extends Guerrero{
	
	private int esPiedra = 0;
	private int furia = 0;
	
	public Nortaichian(Bando bando) {
		super(66, 18, bando);
	}
	
	@Override
	public void atacar(Guerrero enemigo) {
		if(!this.equals(enemigo) && !this.isDesmayado() && this.esPiedra <= 0) {
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
	}
	
	@Override
	public void atacar(Ejercito enemigo) {
		if(!this.equals(enemigo.unidades.getFirst()) && !this.isDesmayado() && this.esPiedra <= 0) {
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
	}
	
	@Override
	public void esAtacado(int damage) {
		if(!this.isDesmayado()) {
			if(esPiedra > 0)
				damage*=0.5;
			this.setSaludActual(this.getSaludActual()-damage);
			this.furia = 2;
		}
		
	}
	
	@Override
	public void descansar() {
		if(!this.isDesmayado() && this.esPiedra <= 0) {
			this.setSaludActual(this.getSaludInicial());
			this.esPiedra = 2;
		}
		
	}
	
	
}