package bicicletoide.citybike.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

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
	public void testConstructorAnclajesOcupadosNegativo(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(1,-2,new GPS(5,5));
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorAnclajesMenorQueOcupados(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(2,3,new GPS(5,5));
	}
	
	@SuppressWarnings("unused")
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorGPSNulo(){
		GPS g = null;
		CityBikeParkingPoint point = new CityBikeParkingPoint(2,3,g);
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
	public void testGetAnclajesOcupados(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		assertEquals(2,point.getNumeroAnclajesOcupados());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetAnclajeIndiceNegativo(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		point.getAnclaje(-3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetAnclajeIndiceMayorQueSize(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		point.getAnclaje(4);
	}
	
	@Test
	public void testGetAnclajeBien(){
		CityBikeParkingPoint point = new CityBikeParkingPoint(3,2,new GPS(50,0));
		assertEquals(point.getAnclaje(1),true);
	}
	
	@Test
	public void testGetCoordenadas(){
		GPS gps = new GPS(10,10);
		CityBikeParkingPoint p = new CityBikeParkingPoint(4,2,gps);
		assertEquals(gps,p.getCoordenadas());
		assertFalse(gps == p.getCoordenadas());
	}
	
	@Test
	public void testGetNumeroAnclajes(){
		CityBikeParkingPoint p = new CityBikeParkingPoint(4,2,new GPS(50,0));
		assertEquals(4,p.getNumeroAnclajes());
	}
	
	@Test
	public void testGetAnclajes(){
		CityBikeParkingPoint p = new CityBikeParkingPoint(4,2,new GPS(50,0));
		ArrayList b = new ArrayList<Boolean>();
		b.add(false);
		b.add(false);
		b.add(true);
		b.add(true);
		
		assertEquals(b,p.getAnclajes());
	}
	
	@Test
	public void testGetID(){
		CityBikeParkingPoint p = new CityBikeParkingPoint(4,2,new GPS(50,0));
		
		assertNotNull(p.getId());
	}
	
	@Test
	public void testEqualsBien(){
		CityBikeParkingPoint point1 = new CityBikeParkingPoint(3,2,new GPS(50,0));
		CityBikeParkingPoint point2 = new CityBikeParkingPoint(3,2,new GPS(50,0));
		assertTrue(point1.equals(point2));
	}

}
