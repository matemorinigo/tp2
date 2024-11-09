package com.tierrafantasia.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class FileManager {
	
	public static final String ruta1 = "..\\TierraFantasia\\res\\ejemplo1.txt";
	public static final String ruta2 = "..\\TierraFantasia\\res\\ejemplo2.txt";
	public static final String ruta3 = "..\\TierraFantasia\\res\\ejemplo3.txt";
	public static final String ruta4 = "..\\TierraFantasia\\res\\ejemplo4.txt";
	public static final String ruta5 = "..\\TierraFantasia\\res\\ejemplo5.txt";

	
	public static void crearArchivo(String ruta) {
		try  {  
			File file=new File(ruta); 
			file.createNewFile();
		}  catch(Exception e)  {  
			e.printStackTrace();  
		}
	}
	
	
	public static String[] abreArchivo(String ruta) {
		String[] lineas = new String[contarLineas(ruta)];
		if(lineas.length > 0) {
			try  {  
				File file=new File(ruta);   
				BufferedReader br=new BufferedReader(new FileReader(file));
				
				String linea;
				int i = 0;
				
				while((linea = br.readLine()) != null)  {  
					lineas[i++] = linea;
				}
				
				br.close();
			}  catch(Exception e)  {  
				e.printStackTrace();  
			}
		}
		
		
		return lineas;
	}
	
	public static void updateArchivo(String ruta, String[] lineas) {
		if(lineas.length == 0)
			return;
		
		try  {  
			File file=new File(ruta);
			OutputStream os;
	        OutputStreamWriter osw;
	        BufferedWriter bw = null;



			file.delete();
			file.createNewFile();
			
            os = (OutputStream) new FileOutputStream(file);
            osw = new OutputStreamWriter(os, "UTF8");
            bw = new BufferedWriter(osw);
			
            for(int i = 0; i < lineas.length; i++) {
            	bw.write(lineas[i]);
            	bw.newLine();
            }
            
			bw.close();
		}  catch(IOException e)  {  
			e.printStackTrace();  
		}
	}
	
	public static int contarLineas(String ruta) {
		int i = 0;
		try  {  
			File file=new File(ruta); 
			if(file.exists()) {
				BufferedReader br=new BufferedReader(new FileReader(file));
				
				while(br.readLine() != null)  {  
					i++;
				}
				
				br.close();
			}
		}  catch(Exception e)  {  
			e.printStackTrace();  
		}
		
		return i;
	}
	
	public static boolean existeArchivo(String ruta) {
		try  {  
			File file=new File(ruta); 
			return file.exists();
		}  catch(Exception e)  {  
			e.printStackTrace(); 
			return false;
		}
	}


}
