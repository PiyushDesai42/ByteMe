package Coords;

import Geom.Point3D;

/**
 * this class extends Point3D and represents a Gps (Lat lon alt) Point
 * @author Eitan Lichtman, Netanel Indik
 */
public class Lat_lon_alt extends Point3D{
	
	public Lat_lon_alt(double lat, double lon, double alt) throws RuntimeException{
		super(lat,lon,alt);
		if(!isValid_GPS_Point()) {
			throw new RuntimeException("invalid gps point!");
		}
	}
	
	public Lat_lon_alt(Lat_lon_alt ot) throws RuntimeException{
		super(ot);
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
