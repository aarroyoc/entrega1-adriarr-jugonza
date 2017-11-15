package bicicletoide.citybike.test;

import bicicletoide.citybike.gps.GPS;

import static org.junit.Assert.*;


import org.junit.Test;

public class GPSTest {

	@Test
	public void testDistancia() {
		GPS gps = new GPS(50,70);
		GPS gps2 = new GPS(51,71);
		double distancia = gps.getDistancia(gps2);
		assertEquals(131.94,distancia,1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDistanciaNull() {
		GPS gps = new GPS(50,70);
		gps.getDistancia(null);
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
	public void ContructorLatitudGDMenorMenos90(){
		GPS gps = new GPS(-180,0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void ContructorLatitudGDMayor90(){
		GPS gps = new GPS(180,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void SetLatitudGDMayor90(){
		GPS gps = new GPS(0,0);
		gps.setLatitud(180);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void SetLatitudGDMenorMenos90(){
		GPS gps = new GPS(0,0);
		gps.setLatitud(-180);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ConstructorLongitudGDMayor180(){
		GPS gps = new GPS(0,200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ConstructorLongitudGDMenorMenos180(){
		GPS gps = new GPS(0,-200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void SetLongitudGDMayor180(){
		GPS gps = new GPS(0,0);
		gps.setLongitud(200);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void SetLongitudGDMenorMenos180(){
		GPS gps = new GPS(0,0);
		gps.setLongitud(-200);
	}
	
	@Test
	public void ConstructorGDBien(){
		GPS gps = new GPS(10,20);
		assertNotNull(gps);
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
	
	@Test
	public void testEqualsNull(){
		GPS gps1 = new GPS(5,10);
		GPS gps2 = new GPS(5,10);
		assertFalse(gps1.equals(null));
	}
	
	//Tests del constructor gms
	
	@Test(expected=IllegalArgumentException.class)
	public void latitudGMSMenor90(){
		GPS gps = new GPS(-91,0,0,0,0,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void latitudGMSMayor90(){
		GPS gps = new GPS(91,0,0,0,0,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void longitudGMSMenor180(){
		GPS gps = new GPS(0,0,0,-181,0,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void longitudGMSMayor180(){
		GPS gps = new GPS(0,0,0,180,10,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void latitudMNegativo(){
		GPS gps = new GPS(0,-2,0,0,0,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void latitudSNegativo(){
		GPS gps = new GPS(0,0,-5,0,0,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void longitudMNegativo(){
		GPS gps = new GPS(0,0,0,0,-5,0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void longitudSNegativo(){
		GPS gps = new GPS(0,0,0,0,0,-7);
	}
	
	@Test
	public void ConstructorGMSBien(){
		GPS gps = new GPS(10,20,30,-10,20,30);
		assertNotNull(gps);
	}
	
	@Test
	public void getLatitudGMS(){
		GPS gps = new GPS(10,0,0,0,0,0);
		assertEquals(10, gps.getLatitud(),0);;
	}
	
	@Test
	public void getLongitudGMS(){
		GPS gps = new GPS(0,0,0,10,0,0);
		assertEquals(10, gps.getLongitud(),0);;
	}
	
	@Test
	public void testCopia(){
		GPS gps = new GPS(0,0,0,10,0,0);
		assertEquals(gps, new GPS(gps));;
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCopiaNull(){
		new GPS(null);
	}
}
