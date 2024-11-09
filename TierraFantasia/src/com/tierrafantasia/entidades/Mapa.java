package com.tierrafantasia.entidades;

import java.security.cert.TrustAnchor;
import java.util.HashMap;
import java.util.Stack;

import com.tierrafantasia.entidades.Predecesor_Distancia;

public class Mapa {
	
	private static Mapa mapaUnico;
	private double[][] matAdy;
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

	public void setMatAdy(double[][] matAdy) {
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

	public double[][] getMatAdy() {
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

	private int nodo_mas_cercano_sin_visitar(boolean[] visitados, double[] distancias){
		int nodo_menor = 0;
		double distancia_min = Double.POSITIVE_INFINITY;

		for (int i = 0; i < visitados.length; i++) {
			if(!visitados[i] && distancias[i] < distancia_min){
				nodo_menor = i;
				distancia_min = distancias[i];
			}
		}
		return nodo_menor;
	}
	
	public Predecesor_Distancia dijkstra(int nodo_inicial){
		int cant_nodos = matAdy.length;
		int[] predecesores = new int[cant_nodos];
		double[] distancias = new double[cant_nodos];
		boolean[] visitados = new boolean[cant_nodos];
		int cant_visitados = 0;

		for(int i = 0; i < predecesores.length; i++){
			predecesores[i] = nodo_inicial;
			distancias[i] = Double.POSITIVE_INFINITY;
		}

		distancias[nodo_inicial] = 0;
		visitados[nodo_inicial] = true;
		cant_visitados++;
        System.out.println("Inicializado:");
        System.out.println("Predecesores:");
        System.out.println(predecesores);
        System.out.println("Inicializado:");
        System.out.println("Inicializado:");

		for(int i = 0; i < matAdy[nodo_inicial].length; i++){
			if(matAdy[nodo_inicial][i] != Double.POSITIVE_INFINITY){
				distancias[i] = matAdy[nodo_inicial][i];
			}
		}

		while (cant_visitados < cant_nodos){
			int nodo_actual = nodo_mas_cercano_sin_visitar(visitados, distancias);
			visitados[nodo_actual] = true;
			cant_visitados++;
			for(int i = 0; i < matAdy[nodo_actual].length; i ++){
				if(matAdy[nodo_actual][i] + distancias[nodo_actual] < distancias[i]){
					distancias[i] = distancias[nodo_actual] + matAdy[nodo_actual][i];
					predecesores[i] = nodo_actual;
				}
			}
		}

		return  new Predecesor_Distancia(predecesores, distancias);
	}

	public void recorrerMapa(Predecesor_Distancia pd, int puebloInicial, int puebloFinal){
		Stack<Integer> pila = new Stack<Integer>();
		pila.push(puebloFinal);
		int i=puebloFinal;
		while((i=pd.getPredecesor(i-1))!=puebloInicial){
			pila.push(i);
		}
		while(!pila.isEmpty()){
			System.out.println(pila.pop());
		}
	}
}
