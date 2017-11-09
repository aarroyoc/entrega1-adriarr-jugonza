package bicicletoide.citybike.test;

import static org.junit.Assert.*;

import org.junit.Test;
import bicicletoide.citybike.gps.GPS;
import bicicletoide.citybike.CityBikeParkingPoint;

public class CityBikeParkingPointTest {

	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorCopiaNulo() {
		CityBikeParkingPoint point = new CityBikeParkingPoint(null);
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorAnclajesNegativos(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(-1,2,new GPS(5,5));
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorAnclajesMenorQueOcupados(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(2,3,new GPS(5,5));
	}
	
	@Test(expected=IllegalStateException.class)
	public void testPrestarBiciVacio(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(5,0,new GPS(5,5));
		point.prestarBici();
	}
	
	@Test(expected=IllegalStateException.class)
	public void testDevolverBiciLleno(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(5,5,new GPS(5,5));
		point.devolverBici();
	}
	
	@Test
	public void testPrestarBici(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(5,5,new GPS(5,5));
		point.prestarBici();
		assertEquals(4,point.getNumeroAnclajesOcupados());
	}
	
	@Test
	public void testDevolverBici(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(5,4,new GPS(5,5));
		point.devolverBici();
		assertEquals(5,point.getNumeroAnclajesOcupados());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanciaGPSNull(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		GPS g = null;
		point.getDistancia(g);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetDistanciaPuntoNull(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		CityBikeParkingPoint p = null;
		point.getDistancia(p);
	}
	
	@Test
	public void testGetDistanciaPuntoBien(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		CityBikeParkingPoint p = new CityBikeParkingPoint(3,2,new GPS(70,0));
		assertEquals(2228.13,point.getDistancia(p), 10 );
	}
	
	@Test
	public void testGetDistanciaGPSBien(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		GPS g = new GPS(70,0);
		assertEquals(2228.13,point.getDistancia(g), 10 );
	}
	
	@Test
	public void testEqualsBien(){
		CityBikeParkingPoint point1 = new CityBikeParkingPoint(3,2,new GPS(50,0));
		CityBikeParkingPoint point2 = new CityBikeParkingPoint(3,2,new GPS(50,0));
		assertTrue(point1.equals(point2));
	}

}
