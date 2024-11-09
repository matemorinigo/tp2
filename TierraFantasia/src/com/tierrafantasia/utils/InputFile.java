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

    public InputFile(String filename){
        ejercitos = new LinkedList<>();
        mapa = new Mapa();
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
        int cantEjercitos = Integer.parseInt(linea);
        int cantUnidades;
        Raza raza;
        Bando bando;

        for (int i = 0; i < cantEjercitos; i++) {
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

        br.close();
    }

}
