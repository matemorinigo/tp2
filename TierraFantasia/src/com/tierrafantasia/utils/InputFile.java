package com.tierrafantasia.utils;

import com.tierrafantasia.entidades.Bando;
import com.tierrafantasia.entidades.Ejercito;
import com.tierrafantasia.entidades.Mapa;
import com.tierrafantasia.entidades.Raza;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class InputFile {

    LinkedList<Ejercito> ejercitos;
    Mapa mapa;
    int puebloInicio;
    int puebloFin;
    int cantPueblos;

    public InputFile(String filename){
        ejercitos = new LinkedList<>();
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
                raza = ParsingUtils.parseRaza(sc.next());
                bando = ParsingUtils.parseBando((sc.next()));
                this.ejercitos.add(new Ejercito(cantUnidades, raza, bando));
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

            matrizAdyacencia[ciudadOrigen-1][ciudadDestino-1] = costo;

            sc.close();
        }

        this.mapa.setMatAdy(matrizAdyacencia);
        this.mapa.setPuebloInicial(puebloInicio);
        this.mapa.setPuebloFinal(puebloFin);

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


    public LinkedList<Ejercito> getEjercitos() {
        return ejercitos;
    }

    public Mapa getMapa() {
        return mapa;
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
