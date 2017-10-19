package bicicletoide.citybike.gps;

/**
 * 
 * @author adriarr
 *
 */
public class GPS {
	// estan almacenados en grados decimales
	private double latitud;
	private double longitud;
	
	public static enum Latitud{
		LATITUD_NORTE,
		LATITUD_SUR
	};
	
	public static enum Longitud{
		LONGITUD_ESTE,
		LONGITUD_OESTE
	};
	
	public GPS(Latitud lat,double latitud, Longitud lon, double longitud){

	}
}
