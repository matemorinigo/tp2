package edu.unlam.avanzada.entidades;

import java.util.HashMap;

public class Mapa {
	
	private static Mapa mapaUnico;
	private int[][] matAdy;
	private int puebloInicial;
	private int puebloFinal;
	private HashMap<Integer, Pueblo> pueblos = new HashMap<>();
	
	private Mapa() {
		super();
	}
	
	public static Mapa getMapaUnico() {
		if(mapaUnico == null)
			mapaUnico = new Mapa();
		
		return mapaUnico;
	}
	
	
	public void setMatAdy(int[][] matAdy) {
		this.matAdy = matAdy;
	}

	public void setPuebloInicial(int puebloInicial) {
		this.puebloInicial = puebloInicial;
	}

	public void setPuebloFinal(int puebloFinal) {
		this.puebloFinal = puebloFinal;
	}
	
	public void setPueblos(HashMap<Integer, Pueblo> pueblos) {
		this.pueblos = pueblos;
	}

	public int[][] getMatAdy() {
		return matAdy;
	}

	public int getPuebloInicial() {
		return puebloInicial;
	}

	public int getPuebloFinal() {
		return puebloFinal;
	}

	public Pueblo getPueblo(Integer numPueblo) {
		return this.pueblos.get(numPueblo);
	}
	
}
