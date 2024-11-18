package Coords;

import Geom.Point3D;
/**
 * this class extends Point3D and represents a Gps vector in meters
 * @author Eitan Lichtman, Netanel Indik
 */
public class Gps_vector extends Point3D{
	
	public Gps_vector(double lat_in_meters, double lon_in_meters, double alt_in_meters) {
		super(lat_in_meters,lon_in_meters,alt_in_meters);
	}
}
