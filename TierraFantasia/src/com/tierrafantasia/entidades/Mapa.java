package com.tierrafantasia.entidades;

import java.util.HashMap;
import java.util.Stack;

public class Mapa {
	public final static int KM_POR_DIA = 10;
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
		int nodo_inicial_real = nodo_inicial - 1;
		int cant_nodos = matAdy.length;
		int[] predecesores = new int[cant_nodos];
		double[] distancias = new double[cant_nodos];
		boolean[] visitados = new boolean[cant_nodos];
		int cant_visitados = 0;

		for(int i = 0; i < predecesores.length; i++){
			predecesores[i] = nodo_inicial_real;
			distancias[i] = Double.POSITIVE_INFINITY;
		}

		distancias[nodo_inicial_real] = 0;
		visitados[nodo_inicial_real] = true;
		cant_visitados++;

		for(int i = 0; i < matAdy[nodo_inicial_real].length; i++){
			if(matAdy[nodo_inicial_real][i] != Double.POSITIVE_INFINITY){
				distancias[i] = matAdy[nodo_inicial_real][i];
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

	/*
	* El recorrido del mapa funciona de la siguiente manera:
	*
	* Utilizamos una pila, donde pondremos el camino hacia la Tierra de Fantasia
	* Ayudandonos con el vector de predecesores el flujo es el siguiente:
	* - Apilamos el pueblo final
	* - Buscamos su predecesor, y lo apilamos
	* - Repetimos buscando predecesores hasta que nuestro i sea el pueblo inicial
	*
	* De esta forma, a medida que sacamos pueblos de la pila, se respeta el recorrido
	*
	* Luego mientras la pila no este vacia, sacamos un pueblo y evaluamos si es aliado o enemigo para continuar
	*
	*
	* */

	public void recorrerMapa(Predecesor_Distancia pd){
		Stack<Integer> pila = new Stack<>();
		Pueblo puebloPropio = pueblos.get(this.puebloInicial);
		if(pd.getDistancia(this.puebloFinal) == Double.POSITIVE_INFINITY){
			System.out.println("La mision no es factible, no hay ningun camino hacia el pueblo " + puebloFinal);
			return;
		}


		pila.push(this.puebloFinal);
		int cantDias = 0;
		int i=this.puebloFinal;
		while((i=pd.getPredecesor(i))!=this.puebloInicial){
			pila.push(i);
		}

		while(!pila.isEmpty()  ){
			int puebloSiguiente = pila.pop();
			Pueblo pueblo = pueblos.get(puebloSiguiente);
			cantDias += (int)pd.getDistancia(puebloSiguiente) / KM_POR_DIA;
			if(pueblo.bando == Bando.ALIADO){
				puebloPropio.ejercito.descansar();
				puebloPropio.ejercito.sumarAliados(pueblo.ejercito);

			} else {
				if(puebloPropio.ejercito.batalla(pueblo.ejercito) == 0){
					System.out.println("La mision no es factible, perderiamos contra el pueblo " + puebloSiguiente);
					return;
				}
			}

		}

		System.out.println("La mision es factible");
		System.out.println("Se tarda " + cantDias + " dias en llegar al pueblo final");
		System.out.println("Sobrevivieron: " + puebloPropio.ejercito.getCantUnidades() + " guerreros");
	}
}
