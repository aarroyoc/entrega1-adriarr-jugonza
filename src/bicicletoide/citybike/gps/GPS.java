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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPS other = (GPS) obj;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		return true;
	}

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
	 * Devuelve la distancia entre dos puntos GPS calculada mediante la f�rmula del haversine
	 * @param punto
	 * @return
	 */
	public double getDistancia(GPS punto){
		double distancia;
		double dlat = Math.abs(degToRad(this.getLatitud() - punto.getLatitud()));
		double dlon = Math.abs(degToRad(this.getLongitud() - punto.getLongitud()));
		double R = 6371;
		double a = Math.pow(Math.sin(dlat/2), 2) + 
				Math.cos(degToRad(this.getLatitud()))*Math.cos(degToRad(punto.getLatitud()))*Math.pow(Math.sin(dlon/2),2);
		//double c = 2*Math.asin(Math.min(1, Math.sqrt(a)));
		double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		distancia = R*c;
		
		return distancia;
	}
	
	private double degToRad(double deg){
		return Math.PI*deg/180;
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
	
}
