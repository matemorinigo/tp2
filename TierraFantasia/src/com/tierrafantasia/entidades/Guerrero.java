package com.tierrafantasia.entidades;

public class Guerrero implements UnidadDeCombate{
	private double saludInicial;
	private double saludActual;
	private int basicDamage;
	private boolean desmayado = false;

	public Guerrero(double saludInicial, int basicDamage) {
		this.saludInicial = saludInicial;
		this.saludActual = saludInicial;
		this.basicDamage = basicDamage;
	}

	
	public void atacar(UnidadDeCombate enemigo) {
		enemigo.esAtacado(this.basicDamage);
	}

	public void descansar() {

	}

	public void esAtacado(int damage) {
		if(this.saludActual - damage <= 0){
			this.saludActual = 0;
			this.desmayado = true;
		}
		else{
			this.saludActual -= damage;
		}

	}

	public double getSaludInicial() {
		return saludInicial;
	}

	protected void setSaludInicial(double saludInicial) {
		this.saludInicial = saludInicial;
	}

	public double getSaludActual() {
		return saludActual;
	}

	protected void setSaludActual(double saludActual) {
		this.saludActual = saludActual;
	}

	public int getBasicDamage() {
		return basicDamage;
	}

	public boolean isDesmayado() {
		return desmayado;
	}

	protected void setDesmayado(boolean desmayado) {
		this.desmayado = desmayado;
	}

	@Override
	public String toString() {
		return "Guerrero [saludInicial=" + saludInicial + ", saludActual=" + saludActual + ", basicDamage="
				+ basicDamage + ", desmayado=" + desmayado + "]";
	}
}
