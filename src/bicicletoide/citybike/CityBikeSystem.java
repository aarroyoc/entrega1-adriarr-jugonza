package bicicletoide.citybike;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bicicletoide.citybike.gps.GPS;

// TODO: TESTS
// TODO: JavaDoc

/**
 * 
 * @author adriarr
 *
 */
public class CityBikeSystem {

	private double fianza;
	private ArrayList<CityBikeParkingPoint> points;

	public CityBikeSystem() {
		this.points = new ArrayList();
	}

	/**
	 * A�ade un punto al sistema. El punto no tiene que haber sido introducido en el sistema con anterioridad
	 * 
	 * @param point
	 */
	public void addCityBikeParkingPoint(CityBikeParkingPoint point) {
		if(point == null)
			throw new IllegalArgumentException();
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
		if(point == null)
			throw new IllegalArgumentException();
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
	 * 
	 * @param gps
	 * @param radius
	 * @return
	 */
	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints(GPS gps, long radius) {
		ArrayList<CityBikeParkingPoint> pointsNuevo = new ArrayList <CityBikeParkingPoint>();
		for (CityBikeParkingPoint p : points) {
			if (p.getDistancia(gps) < radius) {
				pointsNuevo.add(new CityBikeParkingPoint(p));
			}
		}
		return pointsNuevo;
	}

	public List<CityBikeParkingPoint> getCityBikeParkingPointsWithBikes() {
		ArrayList<CityBikeParkingPoint> pointsNuevo = new ArrayList <CityBikeParkingPoint>();
		for (CityBikeParkingPoint p : points) {
			if (p.getNumeroAnclajesOcupados() > 0) {
				pointsNuevo.add(new CityBikeParkingPoint(p));
			}
		}
		return pointsNuevo;
	}

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
