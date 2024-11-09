package com.tierrafantasia.app;

import com.tierrafantasia.entidades.Bando;
import com.tierrafantasia.entidades.Ejercito;
import com.tierrafantasia.entidades.Raza;
import com.tierrafantasia.utils.ParsingUtils;

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		try{
			juego();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void juego() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.print("Ingrese el nombre del archivo: ");
		String filename = sc.nextLine();
		sc.close();
		File input = new File(filename);

		if(!input.exists()) {throw new FileNotFoundException(filename);}

		BufferedReader br = new BufferedReader(new FileReader(input));

		String linea;

		linea = br.readLine();
		int cantEjercitos = Integer.parseInt(linea);
		LinkedList<Ejercito> ejercitos = new LinkedList<>();
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
				ejercitos.add(new Ejercito(cantUnidades, raza, bando));
				sc.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		br.close();

		ejercitos.stream().map(ejercito -> ejercito.unidades).forEach();

		Ejercito ej1 = new Ejercito(100, Raza.WRIVES, Bando.PROPIO);
		Ejercito ej2 = new Ejercito(200, Raza.RADAITERAN, Bando.ENEMIGO);
		batalla(ej1,ej2);
		
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
