package bicicletoide.citybike.gps;
import java.lang.Math;

/**
 * 
 * @author adriarr
 *
 */
public class GPS {
	// estan almacenados en grados decimales
	private double latitud;
	//va de -90 a 90
	private double longitud;
	//va de -180 a 180
	
	//public static enum Latitud{
	//	LATITUD_NORTE,
	//	LATITUD_SUR
	//};
	
	//public static enum Longitud{
	//	LONGITUD_ESTE,
	//	LONGITUD_OESTE
	//};
	
	/**
	 * Construye un nuevo punto GPS con la latitud y longitud indicadas, que tienen que estar
	 * entre -90 y 90, y -180 y 180 GD, respectivamente
	 * @param latitud
	 * @param longitud
	 */
	public GPS(double latitud,  double longitud){
		
		if(Math.abs(latitud)>90){
			throw new IllegalArgumentException("La latitud tiene que estar entre -90 y 90 GD");
		}
		
		if(Math.abs(longitud)>180){
			throw new IllegalArgumentException("La longitud tiene que estar entre -180 y 180 GD");
		}
		
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}
	
	/**
	 * Devuelve la distancia entre dos puntos GPS calculada mediante la fórmula del haversine
	 * @param punto
	 * @return
	 */
	public double getDistancia(GPS punto){
		double distancia;
		double dlon= Math.abs(this.getLongitud()-punto.getLongitud());
		System.out.print(dlon+"\n");
		double dlat = Math.abs(this.getLatitud() - punto.getLatitud());
		System.out.print(dlat+"\n");
		double a = Math.pow(Math.sin(dlat/2), 2)+ Math.cos(this.getLatitud()) *
				Math.cos(punto.getLatitud()) * Math.pow(Math.sin(dlon/2), 2);
		System.out.print(a+"\n");
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		System.out.print(c+"\n");
		distancia = 6371 * c;
		return distancia;
	}

	public double getLatitud() {
		
			return latitud;
	}
	/**
	 * Cambia la latitud del punto, el argumento tiene que estar entre -90 y 90 GD
	 * @param latitud
	 * @throws IllegalArgumentException
	 */
	public void setLatitud(double latitud) throws IllegalArgumentException{
		
		if(Math.abs(latitud)>90){
			throw new IllegalArgumentException("La latitud tiene que estar entre -90 y 90 GD");
		}
		
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Cambia la longitud del punto, el argumento tiene que estar entre -180 y 180 GD
	 * @param longitud
	 * @throws IllegalArgumentException
	 */
	public void setLongitud(double longitud) throws IllegalArgumentException {
		
		if(Math.abs(longitud)>180){
			throw new IllegalArgumentException("La longitud tiene que estar entre -180 y 180 GD");
		}
		
		this.longitud = longitud;
	}
	
	//Prueba
	public static void main(String[]args){
		
		GPS p1 = new GPS(2.816182,-36.117559);
		GPS p2 = new GPS(30.84167,10.84167);
		
		System.out.print(p1.getDistancia(p2));
	}
	
}
