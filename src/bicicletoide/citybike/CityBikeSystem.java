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
	
	private BigDecimal fianza;
	
	public void addCityBikeParkingPoint(CityBikeParkingPoint point){
		
	}
	public void removeCityBikeParkingPoint(CityBikeParkingPoint point){
		
	}
	public void setFianza(BigDecimal fianza){
		this.fianza = fianza;
	}
	
	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints(){
		return new ArrayList<CityBikeParkingPoint>();
	}
	
	public List<CityBikeParkingPoint> getAllCityBikeParkingPoints(GPS gps,long radius){
		return new ArrayList<CityBikeParkingPoint>();
	}
	
	public List<CityBikeParkingPoint> getCityBikeParkingPointsWithBikes(){
		return new ArrayList<CityBikeParkingPoint>();
	}
	public List<CityBikeParkingPoint> getCityBikeParkingPointsWithSpace(){
		return new ArrayList<CityBikeParkingPoint>();
	}
	
}
