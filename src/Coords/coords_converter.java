package Coords;

import Geom.gps_vector;
import Geom.lat_lon_alt;

/**
 * This interface represents a basic coordinate system converter, including:
 * 1. The 3D vector between two lat, lon, alt points.
 * 2. Adding a 3D vector in meters to a global point.
 * 3. convert a 3D vector from meters to polar coordinates
 * @author Boaz Ben-Moshe
 */
public interface coords_converter {
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public lat_lon_alt add(lat_lon_alt gps, gps_vector local_vector_in_meter);
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3D(lat_lon_alt gps0, lat_lon_alt gps1);
	/** computes the 3D vector (in meters) between two gps like points */
	public gps_vector vector3D(lat_lon_alt gps0, lat_lon_alt gps1);
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	public double[] azimuth_elevation_dist(lat_lon_alt gps0, lat_lon_alt gps1);
	/**
	 * return true iff this point is a valid lat, lon , alt coordinate: [-90,+90],[-180,+180],[-450, +inf]
	 * @param p
	 * @return true iff this point has valid gps coordinates
	 */
	public boolean isValid_GPS_Point(lat_lon_alt p);
	
}
