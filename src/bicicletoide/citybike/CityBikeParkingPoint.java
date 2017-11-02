package bicicletoide.citybike;

import fabricante.externo.tarjetas.*;
import bicicletoide.citybike.gps.GPS;

public class CityBikeParkingPoint {
	int numeroAnclajes;
	int numeroAnclajesOcupados;
	GPS coordenadas;
	
	/**
	 * Crea un nuevo punto de aparcamiento con el número de anclajes y anclajes ocupados
	 * especificado, y en las corrdenadas dadas
	 * @param numeroAnclajes
	 * @param numeroAnclajesOcupados
	 * @param latitud
	 * @param longitud
	 */
	public CityBikeParkingPoint(int numeroAnclajes,int numeroAnclajesOcupados,
			double latitud, double longitud){
		this.numeroAnclajes = numeroAnclajes;
		this.numeroAnclajesOcupados = numeroAnclajesOcupados;
		this.coordenadas = new GPS(latitud,longitud);
	}
	/**
	 * Constructor de copia
	 * @param punto
	 */
	public CityBikeParkingPoint(CityBikeParkingPoint punto){
		this.numeroAnclajes = punto.getNumeroAnclajes();
		this.numeroAnclajesOcupados = punto.getNumeroAnclajesOcupados();
		this.coordenadas = new GPS(punto.getCoordenadas().getLatitud(),punto.getCoordenadas().getLongitud());
	}
	/**
	 * Quita una bici del punto de aparcamiento si quedan bicis en los anclajes
	 */
	public void prestarBici(){
		if(numeroAnclajesOcupados>0){
			numeroAnclajesOcupados-=1;
		}else{
			System.out.println("Todos los anclajes están vacíos, hay que ver que hacemos con esos");
		}
	}
	/**
	 * Añade una bici al punto de aparcamiento si quedan anclajes libres
	 */
	public void devolverBici(){
		if(numeroAnclajesOcupados<numeroAnclajes){
			numeroAnclajes+=1;
		}else{
			System.out.println("Todos los anclajes están ocupados, hay que ver que hacemos con esos");
		}
	}
	/**Devuelve la distancia a un punto dado
	 * 
	 * @param punto
	 * @return
	 */
	public double getDistancia(GPS punto){
		return coordenadas.getDistancia(punto);
	}
	/**
	 * Devuelve la distancia a otro punto de aparcamiento
	 * @param punto
	 * @return
	 */
	public double getDistancia(CityBikeParkingPoint punto){
		return coordenadas.getDistancia(punto.getCoordenadas());
	}
	
	public int getNumeroAnclajesOcupados() {
		return numeroAnclajesOcupados;
	}

	public GPS getCoordenadas() {
		return coordenadas;
	}
	public int getNumeroAnclajes() {
		return numeroAnclajes;
	}
	
}
