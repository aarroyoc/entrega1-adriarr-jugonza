package bicicletoide.citybike.test;
import bicicletoide.citybike.gps.GPS;

import static org.junit.Assert.*;

import bicicletoide.citybike.gps.GPS;

import org.junit.Test;

public class GPSTest {

	@Test
	public void test() {
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

}
