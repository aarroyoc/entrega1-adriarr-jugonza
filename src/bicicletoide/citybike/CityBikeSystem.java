package bicicletoide.citybike;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	 * Añade un punto al sistema
	 * 
	 * @param point
	 */
	public void addCityBikeParkingPoint(CityBikeParkingPoint point) {
		points.add(point);
	}

	public void removeCityBikeParkingPoint(CityBikeParkingPoint point) {
		points.remove(point);
	}

	public void setFianza(double fianza){
		if (fianza<0){
			throw new IllegalArgumentException();
		}
		this.fianza = fianza;
	}

	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints() {
		return (List) points.clone();
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
