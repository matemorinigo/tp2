package com.tierrafantasia.utils;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
	
	private ArrayList<T> arr = new ArrayList<>();
	
	public Heap() {
		arr.add(null);	
	}
	
	public void add(T elem) {
		T padre;
		int posActual = 0, band = 1;
		
		arr.add(elem);
		posActual = arr.indexOf(elem);
		while(posActual != 1 && band == 1){
			band = 0;
			padre = arr.get(posActual/2);
			
			if(elem.compareTo(padre) < 0) {
				arr.set(posActual, padre);
				arr.set(posActual/2, elem);
				posActual = arr.indexOf(elem);
				band = 1;
			}
		}
	}
	
	
	public T poll() {
		T elem = arr.get(1), nuevaRaiz = arr.getLast();
		int band = 1, posActual = 1, nuevaPos;
		arr.set(1, nuevaRaiz);
		arr.removeLast();
		
		while(band == 1 && (posActual*2 <= arr.size()-1)) {
			T hijoAComparar;
			band = 0; 
			
			if((posActual*2) + 1 <= arr.size()-1)
				hijoAComparar = minimo(arr.get(posActual*2),arr.get((posActual*2)+1));
			else 
				hijoAComparar = arr.get(posActual*2);
			
			if(hijoAComparar.compareTo(nuevaRaiz) < 0) {
				nuevaPos = arr.indexOf(hijoAComparar);
				arr.set(posActual, hijoAComparar);
				arr.set(nuevaPos, nuevaRaiz);
				posActual = nuevaPos;
				band = 1;
			}
		}
		
		
		return elem;
	}
	
	public int size() {
		return this.arr.size()-1;
	}
	
	public T getFirst() {
		return this.arr.get(1);
	}
	
	public boolean isEmpty() {
		return arr.size() == 1;
	}
	
	private T minimo(T x, T y) {
		return (x.compareTo(y) == -1) ? x : y;
	}
	
}
