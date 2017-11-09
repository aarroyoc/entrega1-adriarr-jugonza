package bicicletoide.citybike;

import java.util.ArrayList;
import fabricante.externo.tarjetas.*;
import java.util.List;
import java.util.NoSuchElementException;

import bicicletoide.citybike.gps.GPS;

// TODO: TESTS
// TODO: JavaDoc

/**
 * 
 * @author adriarr, jugonza
 *
 */
public class CityBikeSystem {

	private double fianza;
	private ArrayList<CityBikeParkingPoint> points;

	/**
	 * Crea una instancia del sistema de gestión de bicicletas de la ciudad. Por defecto, no lleva asignado ningún punto.
	 */
	public CityBikeSystem() {
		this.points = new ArrayList<CityBikeParkingPoint>();
	}
	
	/**
	 * Permite al usuario coger una bici del punto de aparcamiento especificado usando la 
	 * tarjeta monedero, genera una excepcion en caso de que no haya saldo suficiente,
	 * Si no hay bicis en el punto de aparcamiento se devuelve el dinero.
	 * 
	 * Lanza una excepcion en caso de que alguno de los argumentos sea null
	 * @param p
	 * @param t
	 * @throws IllegalArgumentException
	 */
	public void prestarBici(CityBikeParkingPoint p, TarjetaMonedero t){
		if(p == null || t == null){
			throw new IllegalArgumentException();
		}
		try{
			if(t.getSaldoActual()>=fianza){
				t.descontarDelSaldo("6Z1y00Nm31aA-571", fianza);
				p.prestarBici();
			}
		}catch(Exception e){
			t.recargaSaldo("A156Bv09_1zXo894", fianza);
		}
	}
	
	/**
	 * Permite al usuario devolver una bici al punto de aparcamiento especificado usando la 
	 * tarjeta monedero, si no hay espacio en el punto de aparcamiento se vuelve a cargar la fianza
	 *  
	 * Lanza una excepcion en caso de que alguno de los argumentos sea null
	 * @param p
	 * @param t
	 * @throws IllegalArgumentException
	 */
	public void devolverBici(CityBikeParkingPoint p, TarjetaMonedero t){
		if(p == null || t == null){
			throw new IllegalArgumentException();
		}
		try{
			t.recargaSaldo("A156Bv09_1zXo894", fianza);
			p.devolverBici();
		}catch(Exception e){
			t.descontarDelSaldo("6Z1y00Nm31aA-571", fianza);
		}
	}

	/**
	 * A�ade un punto al sistema. El punto no tiene que haber sido introducido en el sistema con anterioridad
	 * 
	 * @param point
	 */
	public void addCityBikeParkingPoint(CityBikeParkingPoint point) {
		if(point == null){
			throw new IllegalArgumentException();
		}
		boolean alreadyExists = points.stream().filter(t -> t.equals(point)).findAny().isPresent();
		if(alreadyExists)
			throw new IllegalArgumentException("El punto ya forma parte del sistema");
		points.add(new CityBikeParkingPoint(point));
	}
	
	/**
	 * Elimina un punto del sistema. Se permite (y se recomienda) pasar una copia del punto en cuestión. El punto debe estar en el sistema
	 * @param point
	 */
	public void removeCityBikeParkingPoint(CityBikeParkingPoint point) {
		if(point == null){
			throw new IllegalArgumentException();
		}
		try{
			CityBikeParkingPoint p = points.stream().filter(t -> t.equals(point)).findFirst().get();
			points.remove(p);
		}catch(NoSuchElementException e){
			throw new IllegalArgumentException("El punto pasado no existe en el sistema");
		}
	}

	/**
	 * Asigna un nuevo valor para la fianza de las bicicletas en todo el sistema
	 * @param fianza
	 */
	public void setFianza(double fianza){
		if (fianza<0){
			throw new IllegalArgumentException("La fianza debe ser un número positivo");
		}
		this.fianza = fianza;
	}
	
	/**
	 * Devuelve una lista con los puntos que forman parte del sistema
	 * @return
	 */
	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints() {
		List<CityBikeParkingPoint> pointsNuevo = new ArrayList<CityBikeParkingPoint>();
		for(CityBikeParkingPoint p : points){
			pointsNuevo.add(new CityBikeParkingPoint(p));
		}
		return pointsNuevo;
	}

	/**
	 * Devuelve una lista de puntos de parking cercanos a una coordenada
	 * @param gps La coordenada GPS sobre la que buscar
	 * @param radius El radio alrededor de la coordenada sobre el que buscar puntos
	 * @return
	 */
	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints(GPS gps, long radius) {
		if(gps == null){
			throw new IllegalArgumentException();
		}
		ArrayList<CityBikeParkingPoint> pointsNuevo = new ArrayList <CityBikeParkingPoint>();
		for (CityBikeParkingPoint p : points) {
			if (p.getDistancia(gps) < radius) {
				pointsNuevo.add(new CityBikeParkingPoint(p));
			}
		}
		return pointsNuevo;
	}

	/**
	 * Devuelve una lista de puntos de parking con bicicletas disponibles
	 * @return
	 */
	public List<CityBikeParkingPoint> getCityBikeParkingPointsWithBikes() {
		ArrayList<CityBikeParkingPoint> pointsNuevo = new ArrayList <CityBikeParkingPoint>();
		for (CityBikeParkingPoint p : points) {
			if (p.getNumeroAnclajesOcupados() > 0) {
				pointsNuevo.add(new CityBikeParkingPoint(p));
			}
		}
		return pointsNuevo;
	}

	/**
	 * Devuelve una lista de puntos de parking con sitio libre para dejar la bicicleta
	 * @return
	 */
	public List<CityBikeParkingPoint> getCityBikeParkingPointsWithSpace() {
		ArrayList <CityBikeParkingPoint> pointsNuevo = new ArrayList <CityBikeParkingPoint>();
		for (CityBikeParkingPoint p : points) {
			if (p.getNumeroAnclajesOcupados() < p.getNumeroAnclajes()) {
				pointsNuevo.add(new CityBikeParkingPoint(p));
			}
		}
		return pointsNuevo;
	}

}
