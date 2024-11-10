package com.tierrafantasia.app;

import com.tierrafantasia.entidades.*;
import com.tierrafantasia.utils.InputFile;


import java.io.*;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		try{
			juego();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void juego() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Ingrese el nombre del archivo: ");
		String filename = sc.nextLine();
		sc.close();

		InputFile file = new InputFile(filename);

		Mapa mapa = Mapa.getMapaUnico();
		Predecesor_Distancia pd = mapa.dijkstra(file.getPuebloInicio());

		mapa.recorrerMapa(pd, file.getPuebloInicio(), file.getPuebloFin());

	}
	
}
