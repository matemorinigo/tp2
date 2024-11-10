package com.tierrafantasia.entidades;

//La interfaz comun tanto para guerrero y ejercito
//para asegurar el pattern design Composite, identificamos las acciones comunes entre las 2 clases
//atacar, serAtacado y descansar
//de esta manera agregamos los metodos en la intefaz y cada clase va a implementarlas
public interface UnidadDeCombate {
	
	//El hecho de que mandemos por parametro la interfaz nos asegura que un guerrero puede atacar
	//tanto un ejercito como un guerrero
	public void atacar(UnidadDeCombate enemigo);
	public void descansar();
	public void esAtacado(int damage); 
}
