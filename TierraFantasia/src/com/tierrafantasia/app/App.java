package com.tierrafantasia.app;

import com.tierrafantasia.entidades.*;
import com.tierrafantasia.utils.InputFile;
import com.tierrafantasia.utils.ParsingUtils;

import java.io.*;
import java.util.LinkedList;
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
		System.out.println("1: " + pd.getPredecesor(0));
		System.out.println("2: " + pd.getPredecesor(1));
		System.out.println("3: " + pd.getPredecesor(2));
		System.out.println("4: " + pd.getPredecesor(3));
		//mapa.recorrerMapa(pd, file.getPuebloInicio(), file.getPuebloFin());


		
	}



	public static void batalla(Ejercito ejercito1, Ejercito ejercito2) {
		while(!ejercito1.sinUnidades() && !ejercito2.sinUnidades()) {
			
			ejercito1.atacar(ejercito2);
			
			if(!ejercito2.sinUnidades())
				ejercito2.atacar(ejercito1);
		}
		if(!ejercito1.sinUnidades())
			System.out.println("Gano ejercito 1");
		else
			System.out.println("Gano ejercito 2");
	}
	
}
