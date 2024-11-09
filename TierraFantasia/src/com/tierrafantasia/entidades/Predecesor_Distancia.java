package com.tierrafantasia.entidades;

public class Predecesor_Distancia {
    private int[] predecesores;
    private double[] distancias;

    public Predecesor_Distancia(int[] predecesores, double[] distancias){
        this.predecesores = predecesores;
        this.distancias = distancias;
    }

    public int getPredecesor(int nodo) {
        return predecesores[nodo - 1] + 1;
    }

    public double getDistancia(int nodo) {
        return distancias[nodo - 1];
    }
}
