package Geom;

import Geom.lat_lon_alt;

public class lat_lon_alt extends Point3D{
	
	public lat_lon_alt(double lat, double lon, double alt) throws RuntimeException{
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
