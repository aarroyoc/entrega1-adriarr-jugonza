package bicicletoide.citybike.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import bicicletoide.citybike.CityBikeSystem;
import bicicletoide.citybike.CityBikeParkingPoint;
import bicicletoide.citybike.gps.GPS;

public class CityBikeSystemTest {

	@Test
	public void add() {
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point = new CityBikeParkingPoint(1,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(point);
		List<CityBikeParkingPoint> list=cbs.getAllCityBikeParkingPoints();
		assertEquals(1,list.size());
		assertNotSame(point,list.get(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addnull(){
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.addCityBikeParkingPoint(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void addDuplicate(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point = new CityBikeParkingPoint(1,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(point);
		cbs.addCityBikeParkingPoint(point);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removeNonExistant(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point = new CityBikeParkingPoint(1,0,new GPS(0,0));
		cbs.removeCityBikeParkingPoint(point);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removeNullPoint(){
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.removeCityBikeParkingPoint(null);
	}
	
	@Test
	public void remove(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point = new CityBikeParkingPoint(1,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(point);
		cbs.removeCityBikeParkingPoint(point);
		List<CityBikeParkingPoint> list=cbs.getAllCityBikeParkingPoints();
		assertEquals(0,list.size());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void fianzaSetNegative(){
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(-1.0);
	}
	
	@Test
	public void getAll(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point1 = new CityBikeParkingPoint(1,0,new GPS(0,0));
		CityBikeParkingPoint point2 = new CityBikeParkingPoint(1,0,new GPS(1,0));
		CityBikeParkingPoint point3 = new CityBikeParkingPoint(1,0,new GPS(1,1));
		cbs.addCityBikeParkingPoint(point1);
		cbs.addCityBikeParkingPoint(point2);
		cbs.addCityBikeParkingPoint(point3);
		List<CityBikeParkingPoint> list = cbs.getAllCityBikeParkingPoints();
		assertFalse(list.isEmpty());
		assertTrue(list.contains(point1));
		assertTrue(list.contains(point2));
		assertTrue(list.contains(point3));
		assertEquals(3,list.size());
	}
	
	@Test
	public void getNothingBecauseThereAreNoBikes(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint point1 = new CityBikeParkingPoint(1,0,new GPS(0,0));
		CityBikeParkingPoint point2 = new CityBikeParkingPoint(1,0,new GPS(1,0));
		CityBikeParkingPoint point3 = new CityBikeParkingPoint(1,0,new GPS(1,1));
		cbs.addCityBikeParkingPoint(point1);
		cbs.addCityBikeParkingPoint(point2);
		cbs.addCityBikeParkingPoint(point3);
		List<CityBikeParkingPoint> list = cbs.getCityBikeParkingPointsWithBikes();
		assertTrue(list.isEmpty());
	}

}
