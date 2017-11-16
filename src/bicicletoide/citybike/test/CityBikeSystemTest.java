package bicicletoide.citybike.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import bicicletoide.citybike.CityBikeSystem;
import bicicletoide.citybike.CityBikeParkingPoint;
import bicicletoide.citybike.gps.GPS;

import fabricante.externo.tarjetas.TarjetaMonedero;

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
	
	@Test
	public void prestarBiciOk(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(p, visa);
		
		CityBikeParkingPoint p2 = cbs.getAllCityBikeParkingPoints().get(0);
		assertEquals(6,p2.getNumeroAnclajesOcupados());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void prestarBiciTarjetaNull(){
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(p, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void prestarBiciNull(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(null, visa);
	}
	
	@Test(expected=IllegalStateException.class)
	public void prestarBiciSinHuecos(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(p, visa);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void prestarBiciSinSaldo(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(p, visa);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void prestarBiciSinFianza(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.prestarBici(p, visa);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void prestarBiciPuntoExterior(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.prestarBici(p, visa);
	}
	
	
	@Test
	public void devolverBiciOk(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(p, visa);
		
		CityBikeParkingPoint p2 = cbs.getAllCityBikeParkingPoints().get(0);
		assertEquals(8,p2.getNumeroAnclajesOcupados());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void devolverBiciTarjetaNull(){
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(p, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void devolverBiciNull(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,7,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(null, visa);
	}
	
	@Test(expected=IllegalStateException.class)
	public void devolverBiciSinHuecos(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",100);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,12,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(p, visa);
	}
	
	@Test
	public void devolverBiciSaldo(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		cbs.setFianza(50);
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(p, visa);
		assertEquals(75,visa.getSaldoActual(),0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void devolverBiciSinFianza(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.devolverBici(p, visa);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void devolverBiciPuntoExterior(){
		TarjetaMonedero visa = new TarjetaMonedero("A156Bv09_1zXo894",25);
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.devolverBici(p, visa);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getAllCityBikeParkingPointsGpsNull(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.getAllCityBikeParkingPoints(null,5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getAllCityBikeParkingPoints(){
		CityBikeSystem cbs = new CityBikeSystem();
		CityBikeParkingPoint p = new CityBikeParkingPoint(12,0,new GPS(0,0));
		cbs.addCityBikeParkingPoint(p);
		cbs.getAllCityBikeParkingPoints(new GPS(5,5),-4);
	}

}
