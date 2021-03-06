package bicicletoide.citybike.gps;
import java.lang.Math;

/**
 * Tipo de dato que representa una coordenada en el planeta Tierra
 * @author adriarr, jugonza
 * @version 1.0
 */
public class GPS {
	// estan almacenados en grados decimales
	private double latitud;
	//va de -90 a 90
	private double longitud;
	//va de -180 a 180
	
	
	/**
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
		if (Math.abs(latitud - other.latitud) > 0.1)
			return false;
		if (Math.abs(longitud - other.longitud) > 0.1)
			return false;
		return true;
	}
	
	/**
	 * Constructor de copia de la clase GPS
	 * @param gps Objeto GPS del cual se realiza la copia
	 * @throws IllegalArgumentException Si el objeto a copiar es null
	 */
	public GPS(GPS gps){
		if(gps == null){
			throw new IllegalArgumentException();
		}
		this.setLatitud(gps.getLatitud());
		this.setLongitud(gps.getLongitud());
	}

	/**
	 * Construye un nuevo punto GPS con la latitud y longitud indicadas, que tienen que estar
	 * entre -90 y 90, y -180 y 180 GD, respectivamente
	 * @param latitud Latitud en GD
	 * @param longitud Longitud en GD
	 * @throws IllegalArgumentException Si la latitud no esta entre [-90,90] o la longitud no esta entre [-180,180]
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
	 * Construye un nuevo punto GPS con la latitud y longitud indicadas, expresado en grados,
	 * minutos y segundos. Los valores de minutos y segundos tienen que ser positivos. Y los de grados dentro del rango [-90,90] (latitud) y [-180,180] (longitud)
	 * @param latitudG Grados de la latitud
	 * @param latitudM Minutos de la latitud
	 * @param latitudS Segundos de la latitud
	 * @param longitudG Grados de la longitud
	 * @param longitudM Minutos de la longitud
	 * @param longitudS Segundos de la longitud
	 * @throws IllegalArgumentException Si alguno de los valores no esta en su rango aceptado
	 */
	public GPS(int latitudG, int latitudM, double latitudS, int longitudG, int longitudM, double longitudS){
		if(latitudM < 0 || latitudS < 0 || longitudM < 0 || longitudS <0){
			throw new IllegalArgumentException();
		}
		
		double latitud = Math.abs(latitudG)+latitudM/60.0 + latitudS/3600;
		latitud *= Math.signum(latitudG);
		if(Math.abs(latitud) > 90){
			throw new IllegalArgumentException();
		}
		this.setLatitud(latitud);
		
		double longitud = Math.abs(longitudG) + longitudM/60.0 + longitudS/3600;
		longitud *= Math.signum(longitudG);
		if(Math.abs(longitud) > 180){
			throw new IllegalArgumentException();
		}
		this.setLongitud(longitud);
	}
	
	/**
	 * Devuelve la distancia entre dos puntos GPS calculada mediante la formula del haversine
	 * @param punto punto GPS al que se calcula la distancia
	 * @return La distancia entre los dos puntos
	 */
	public double getDistancia(GPS punto){
		if(punto == null){
			throw new IllegalArgumentException();
		}
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

	/**
	 * Obtiene la latitud en grados decimales
	 * @return la latitud del punto
	 */
	public double getLatitud() {
			return latitud;
	}
	/**
	 * Cambia la latitud del punto, el argumento tiene que estar entre -90 y 90 GD
	 * @param latitud La nueva latitud proporcionada por el usuario
	 * @throws IllegalArgumentException Si la latitud no esta en el rango [-90,90]
	 */
	public void setLatitud(double latitud) throws IllegalArgumentException{
		
		if(Math.abs(latitud)>90){
			throw new IllegalArgumentException("La latitud tiene que estar entre -90 y 90 GD");
		}
		
		this.latitud = latitud;
	}

	/**
	 * Obtiene la longitud en grados decimales
	 * @return  La longitud en grados decimales
	 */
	public double getLongitud() {
		return longitud;
	}
	
	/**
	 * Cambia la longitud del punto, el argumento tiene que estar entre -180 y 180 GD
	 * @param longitud La nueva longitud proporcionada por el usuario
	 * @throws IllegalArgumentException Si la longitud no esta en el rango [-180,180]
	 */
	public void setLongitud(double longitud) {
		
		if(Math.abs(longitud)>180){
			throw new IllegalArgumentException("La longitud tiene que estar entre -180 y 180 GD");
		}
		
		this.longitud = longitud;
	}
	
}
