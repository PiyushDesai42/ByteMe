package Coords;

import Coords.Lat_lon_alt;
import Geom.Point3D;

public class Lat_lon_alt extends Point3D{
	
	public Lat_lon_alt(double lat, double lon, double alt) throws RuntimeException{
		super(lat,lon,alt);
		if(!isValid_GPS_Point()) {
			throw new RuntimeException("invalid gps point!");
		}
	}
	
	
	public boolean isValid_GPS_Point() {
		return (this.x()>=-90 && this.x()<=90
				&& this.y()>=-180 && this.y()<=180
				&& this.z()>=-450);
	}
}