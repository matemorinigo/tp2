package edu.unlam.avanzada.entidades;

public class Guerrero implements Comparable<Guerrero>{
	private double saludInicial;
	private double saludActual;
	private int basicDamage;
	private boolean desmayado = false;
	private Bando bando;
	
	public Guerrero(double saludInicial, int basicDamage, Bando bando) {
		this.saludInicial = saludInicial;
		this.saludActual = saludInicial;
		this.basicDamage = basicDamage;
		this.bando = bando;
	}
	
	public void atacar(Guerrero enemigo) {
		if(!this.equals(enemigo) && !this.desmayado)
			enemigo.esAtacado(this.basicDamage);
	}
	
	public void atacar(Ejercito enemigo) {
		if(!this.equals(enemigo.unidades.getFirst()) && !this.desmayado)
			enemigo.esAtacado(this.basicDamage);
	}
	
	public void descansar() {
		
	}
	
	public void esAtacado(int damage) {
		if(!this.desmayado) {
			this.saludActual -= damage;
			
			if(this.saludActual <= 0) {
				this.saludActual = 0;
				this.desmayado = true;
			}
		}
	}
	
	public Bando getBando() {
		return this.bando;
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
	
	@Override
	public int compareTo(Guerrero o) {
		int aux = this.bando.compareTo(o.bando); 
		if(aux == 0) {
			if(this.saludActual > o.saludActual)
				return -1;
			else
				return 1;
		}
		
		return aux;
	}
}
