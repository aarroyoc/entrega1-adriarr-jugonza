package bicicletoide.citybike;

import fabricante.externo.tarjetas.*;
import bicicletoide.citybike.gps.GPS;
import java.util.UUID;

public class CityBikeParkingPoint {
	private int numeroAnclajes;
	private int numeroAnclajesOcupados;
	private final GPS coordenadas;
	private final UUID id;
	
	/**
	 * Crea un nuevo punto de aparcamiento con el n�mero de anclajes y anclajes ocupados
	 * especificado, y en las cordenadas dadas
	 * @param numeroAnclajes
	 * @param numeroAnclajesOcupados
	 * @param latitud
	 * @param longitud
	 */
	public CityBikeParkingPoint(int numeroAnclajes,int numeroAnclajesOcupados,
			double latitud, double longitud){
		if ( numeroAnclajes <= 0 || numeroAnclajesOcupados < 0){
			throw new IllegalArgumentException();
		}
		if(numeroAnclajesOcupados > numeroAnclajes){
			throw new IllegalArgumentException();
		}
		this.numeroAnclajes = numeroAnclajes;
		this.numeroAnclajesOcupados = numeroAnclajesOcupados;
		this.coordenadas = new GPS(latitud,longitud);
		this.id = UUID.randomUUID();
	}
	/**
	 * Constructor de copia
	 * @param punto
	 */
	public CityBikeParkingPoint(CityBikeParkingPoint punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
		this.numeroAnclajes = punto.getNumeroAnclajes();
		this.numeroAnclajesOcupados = punto.getNumeroAnclajesOcupados();
		this.coordenadas = new GPS(punto.getCoordenadas().getLatitud(),punto.getCoordenadas().getLongitud());
		this.id = punto.getId();
	}
	/**
	 * Quita una bici del punto de aparcamiento si quedan bicis en los anclajes
	 */
	public void prestarBici(){
		if(numeroAnclajesOcupados>0){
			numeroAnclajesOcupados-=1;
		}else{
			throw new IllegalStateException("No quedan bicis en el punto de aparcamiento");
		}
	}
	/**
	 * A�ade una bici al punto de aparcamiento si quedan anclajes libres
	 */
	public void devolverBici(){
		if(numeroAnclajesOcupados < numeroAnclajes){
			numeroAnclajesOcupados+=1;
		}else{
			throw new IllegalStateException("No quedan puntos de anclaje en el punto de aparcamiento");
		}
	}
	/**Devuelve la distancia a un punto dado
	 * 
	 * @param punto
	 * @return
	 */
	public double getDistancia(GPS punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
		return coordenadas.getDistancia(punto);
	}
	/**
	 * Devuelve la distancia a otro punto de aparcamiento
	 * @param punto
	 * @return
	 */
	public double getDistancia(CityBikeParkingPoint punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
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
	public UUID getId(){
		return this.id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenadas == null) ? 0 : coordenadas.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityBikeParkingPoint other = (CityBikeParkingPoint) obj;
		if (coordenadas == null) {
			if (other.coordenadas != null)
				return false;
		} else if (!coordenadas.equals(other.coordenadas))
			return false;
		return true;
	}
	
}
