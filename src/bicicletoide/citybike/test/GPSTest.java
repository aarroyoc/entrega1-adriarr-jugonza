package bicicletoide.citybike.test;
import bicicletoide.citybike.gps.GPS;

import static org.junit.Assert.*;

import bicicletoide.citybike.gps.GPS;

import org.junit.Test;

public class GPSTest {

	@Test
	public void test() {
		GPS gps = new GPS(50,70);
		GPS gps2 = new GPS(55,75);
		double distancia = gps.getDistancia(gps2);
		assertEquals(distancia,650.5,0.01);
	}
	@Test
	public void testGetLatitud() {
		GPS gps = new GPS(50,70);
		double latitud = gps.getLatitud();
		assertEquals(latitud,50,0.01);
	}
	@Test
	public void testGetLongitud() {
		GPS gps = new GPS(50,70);
		double longitud = gps.getLongitud();
		assertEquals(longitud,70,0.01);
	}

}
