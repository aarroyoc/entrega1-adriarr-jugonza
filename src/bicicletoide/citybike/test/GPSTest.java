package bicicletoide.citybike.test;

import bicicletoide.citybike.gps.GPS;

import static org.junit.Assert.*;


import org.junit.Test;

public class GPSTest {

	@Test
	public void distancia() {
		GPS gps = new GPS(50,70);
		GPS gps2 = new GPS(51,71);
		double distancia = gps.getDistancia(gps2);
		assertEquals(131.94,distancia,1);
	}
	@Test
	public void testGetLatitud() {
		GPS gps = new GPS(50,70);
		double latitud = gps.getLatitud();
		assertEquals(50,latitud,0.01);
	}
	@Test
	public void testGetLongitud() {
		GPS gps = new GPS(50,70);
		double longitud = gps.getLongitud();
		assertEquals(70,longitud,0.01);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void wrongConstruct(){
		GPS gps = new GPS(-180,0);
		gps.setLatitud(50);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void wrongLatitud(){
		GPS gps = new GPS(0,0);
		gps.setLatitud(180);
	}
	
	@Test
	public void gmsAndGd(){
		GPS gps1 = new GPS(-5.0847,10.0847);
		GPS gps2 = new GPS(-5,5,5,10,5,5);
		assertEquals(gps1,gps2);
	}
	
	@Test
	public void testEquals(){
		GPS gps1 = new GPS(5,10);
		GPS gps2 = new GPS(5,10);
		assertEquals(gps1,gps2);
	}

}
