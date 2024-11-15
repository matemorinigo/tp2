package com.tierrafantasia.utils;

import com.tierrafantasia.entidades.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class InputFile {
    Mapa mapa;
    int puebloInicio;
    int puebloFin;
    int cantPueblos;

    public InputFile(String filename){

        mapa = Mapa.getMapaUnico();
        try {
            procesarInput(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void procesarInput(String filename) throws Exception {
        File input = new File(filename);
        Scanner sc;

        if (!input.exists()) {
            throw new FileNotFoundException(filename);
        }

        BufferedReader br = new BufferedReader(new FileReader(input));

        String linea;

        linea = br.readLine();
        this.cantPueblos = Integer.parseInt(linea);
        double[][] matrizAdyacencia = new double[this.cantPueblos][this.cantPueblos];
        HashMap<Integer, Pueblo> pueblos = new HashMap<>();
        inicializarMatriz(matrizAdyacencia);
        int cantUnidades;
        int ciudadOrigen;
        int ciudadDestino;
        int costo;
        Raza raza;
        Bando bando;

        for (int i = 0; i < this.cantPueblos; i++) {
            linea = br.readLine();
            sc = new Scanner(linea);
            sc.nextInt();
            cantUnidades = sc.nextInt();

            try {

                raza = Raza.valueOf(sc.next().toUpperCase());

                bando = Bando.valueOf(sc.next().toUpperCase());
                pueblos.put(i+1, new Pueblo(new Ejercito(cantUnidades, raza), bando));
                sc.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        linea = br.readLine();
        sc = new Scanner(linea);
        sc.useDelimiter(" -> ");

        puebloInicio = sc.nextInt();
        puebloFin = sc.nextInt();

        sc.close();

        while((linea = br.readLine()) != null){
            sc = new Scanner(linea);
            ciudadOrigen = sc.nextInt();
            ciudadDestino = sc.nextInt();
            costo = sc.nextInt();

            matrizAdyacencia[ciudadOrigen-1][ciudadDestino-1] = costo+10;

            sc.close();
        }

        this.mapa.setMatAdy(matrizAdyacencia);
        this.mapa.setPuebloInicial(puebloInicio);
        this.mapa.setPuebloFinal(puebloFin);
        this.mapa.setPueblos(pueblos);

        br.close();
    }

    private void inicializarMatriz(double[][] matriz){
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz.length; j++){
                if (i != j) {
                    matriz[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    matriz[i][j] = 0;
                }
            }
        }
    }


    public int getPuebloInicio() {
        return puebloInicio;
    }

    public int getPuebloFin() {
        return puebloFin;
    }

    public int getCantPueblos() {
        return cantPueblos;
    }
}
