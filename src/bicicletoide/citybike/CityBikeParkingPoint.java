package bicicletoide.citybike;

import bicicletoide.citybike.gps.GPS;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * La clase representa un punto de parking de bicicletas, identificado por su ID y su coordenada
 * @author adriarr, jugonza
 *
 */
public class CityBikeParkingPoint {
	private List<Boolean> anclajes;
	private final GPS coordenadas;
	private final UUID id;
	
	/**
	 * Crea un nuevo punto de aparcamiento con el numero de anclajes y anclajes ocupados
	 * especificado, y en las cordenadas dadas
	 * @param numeroAnclajes Numero de modulos de anclaje de los que dispone el parking
	 * @param numeroAnclajesOcupados Numero de modulos de anclaje ocupados
	 * @param gps Coordenada GPS del punto
	 * @throws IllegalArgumentException Si el numero de anclajes o anclajes ocupados es negativo
	 * @throws IllegalArgumentException Si hay más anclajes ocupados que anclajes
	 * @throws IllegalArgumentException Si la coordenada es null
	 */
	public CityBikeParkingPoint(int numeroAnclajes,int numeroAnclajesOcupados,
			GPS gps){
		if ( numeroAnclajes <= 0 || numeroAnclajesOcupados < 0){
			throw new IllegalArgumentException();
		}
		if(numeroAnclajesOcupados > numeroAnclajes){
			throw new IllegalArgumentException();
		}
		if(gps == null){
			throw new IllegalArgumentException();
		}
		
		this.anclajes = new ArrayList<Boolean>();
		for(int i=0;i<numeroAnclajes-numeroAnclajesOcupados;i++){
			this.anclajes.add(false);
		}
		for(int i=numeroAnclajes-numeroAnclajesOcupados;i<numeroAnclajes;i++){
			this.anclajes.add(true);
		}
		this.coordenadas = new GPS(gps);
		this.id = UUID.randomUUID();
	}
	/**
	 * Constructor de copia. Realiza una copia independiente del punto
	 * @param punto El punto fuente
	 * @throws IllegalArgumentException Si punto es null
	 */
	public CityBikeParkingPoint(CityBikeParkingPoint punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
		this.anclajes = punto.getAnclajes();
		this.coordenadas = new GPS(punto.getCoordenadas().getLatitud(),punto.getCoordenadas().getLongitud());
		this.id = punto.getId();
	}
	/**
	 * Quita una bici del punto de aparcamiento si quedan bicis en los anclajes
	 * @throws IllegalStateException Si no hay bicis disponibles para prestar en ningun modulo de anclaje
	 */
	public void prestarBici(){
		if(this.anclajes.stream().filter(t -> t == true).count() > 0){
			for(int i=0;i<this.anclajes.size();i++){
				if(this.anclajes.get(i) == true){
					this.anclajes.set(i, false);
					break;
				}
			}
		}else{
			throw new IllegalStateException("No quedan bicis en el punto de aparcamiento");
		}
	}
	/**
	 * Anade una bici al punto de aparcamiento si quedan anclajes libres
	 * @throws IllegalStateException Si no quedan modulos de anclaje libres para devolver la bici
	 */
	public void devolverBici(){
		if(this.anclajes.stream().filter(t-> t==true).count() < this.anclajes.size()){
			for(int i=0;i<this.anclajes.size();i++){
				if(this.anclajes.get(i) == false){
					this.anclajes.set(i, true);
					break;
				}
			}
		}else{
			throw new IllegalStateException("No quedan puntos de anclaje en el punto de aparcamiento");
		}
	}
	/**Devuelve la distancia a un punto dado a una coordenada GPS
	 * 
	 * @param punto Coordenada GPS
	 * @return Distancia (en km) al otro punto
	 * @throws IllegalArgumentException Si punto es null
	 */
	public double getDistancia(GPS punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
		return coordenadas.getDistancia(punto);
	}
	/**
	 * Devuelve la distancia a otro punto de aparcamiento
	 * @param punto Punto de la red
	 * @return Distancia (en km)
	 * @throws IllegalArgumentException Si punto es null
	 */
	public double getDistancia(CityBikeParkingPoint punto){
		if ( punto == null){
			throw new IllegalArgumentException();
		}
		return coordenadas.getDistancia(punto.getCoordenadas());
	}
	
	/**
	 * 
	 * @return El número de modulos de anclajes actualmente ocupados
	 */
	public int getNumeroAnclajesOcupados() {
		return (int) this.anclajes.stream().filter(t -> t == true).count();
	}
	
	/**
	 * Devuelve si el anclaje especificado tiene una bici o no
	 * @param index Identificador del anclaje
	 * @return True si el anclaje está ocupado, false en caso contrario
	 * @throws IllegalArgumentException Si el anclaje especificado no existe
	 */
	public boolean getAnclaje(int index){
		if(index < 0 || index >= this.anclajes.size())
			throw new IllegalArgumentException();
		return this.anclajes.get(index);
	}

	/**
	 * 
	 * @return Las coordenadas GPS del punto
	 */
	public GPS getCoordenadas() {
		return new GPS(coordenadas);
	}
	
	/**
	 * 
	 * @return El número de anclajes de los que dispone el punto
	 */
	public int getNumeroAnclajes() {
		return this.anclajes.size();
	}
	
	/**
	 * 
	 * @return Una lista con todos los módulos de anclaje del punto
	 */
	@SuppressWarnings("unchecked")
	public List<Boolean> getAnclajes(){
		return (List<Boolean>) ((ArrayList<Boolean>) this.anclajes).clone();
	}
	
	/**
	 * 
	 * @return El identificador único del punto
	 */
	public UUID getId(){
		return this.id;
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordenadas == null) ? 0 : coordenadas.hashCode());
		return result;
	}
	/**
	 * 
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
