package Coords;

import Geom.gps_vector;
import Geom.lat_lon_alt;

public class MyCoords implements coords_converter{

	private final double earthRadius = 6371*1000;

 
	private lat_lon_alt toRad (lat_lon_alt gpsDeg) {
		return new lat_lon_alt (gpsDeg.x()*Math.PI/180, gpsDeg.y()*Math.PI/180,gpsDeg.z());
	}
	
	private double toRad(double deg) {
		return deg*Math.PI/180;
	}

	private lat_lon_alt toDeg (lat_lon_alt gpsRad) {
		return new lat_lon_alt (gpsRad.x()/Math.PI*180, gpsRad.y()/Math.PI*180, gpsRad.z());
	}
	
	private double toDeg(double rad) {
		return rad/Math.PI*180;
	}


	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param Point3D gps, Point3D vector
	 * @return point3D gps transformed
	 */
	@Override
	public lat_lon_alt add(lat_lon_alt gps, gps_vector vector){

		//change lat lon to polar
		lat_lon_alt polar = new lat_lon_alt (Math.asin(vector.x()/earthRadius),
				Math.asin(vector.y()/(earthRadius*Math.cos(gps.x()))),0);
		//calculating the new lat lon alt
		double lat = gps.x() - polar.x();
		double lon = gps.y() - polar.y();
		double alt = gps.z() + vector.z();
		//return new gps point
		return new lat_lon_alt(lat,lon,alt);
	}


	/**
	 * computes the 3D vector (in meters) between two gps like points
	 * @param gps0, gps1
	 * @return vector
	 */
	@Override
	public gps_vector vector3D(lat_lon_alt gps0, lat_lon_alt gps1){

		//calculate the difference between the lat and lon of gps0 and gps1
		lat_lon_alt diff = new lat_lon_alt(gps1.x()-gps0.x(), gps1.y()-gps0.y(), gps1.z()-gps0.z());
		//converts to radiant
		lat_lon_alt rad = toRad(diff);
		//calculates the distance between the two x's y's and z's in meters
		double xMeters = Math.sin(rad.x())*earthRadius;
		double yMeters = Math.sin(rad.y())*earthRadius*Math.cos(gps0.x()*Math.PI/180);
		double zMeters = rad.z();
		//return the vector
		return new gps_vector(xMeters,yMeters,zMeters);
	}


	/**
	 * computes the 3D distance (in meters) between the two gps like points
	 * @param gps0, gps1
	 * @return distance
	 */
	@Override
	public double distance3D(lat_lon_alt gps0, lat_lon_alt gps1) throws RuntimeException{

		//creating a vector of the difference in meters
		gps_vector vector = vector3D(gps0,gps1);
		//calculating the distance
		double dist = Math.sqrt(vector.x()*vector.x() + vector.y()*vector.y());
		if (dist > 100000) {
			throw new RuntimeException("these gps points are too far from each other!");
		}
		return dist;
	}


	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @param gps0, gps1
	 * @return array with azimuth, elevation and distance
	 */
	@Override
	public double[] azimuth_elevation_dist(lat_lon_alt gps0, lat_lon_alt gps1){

		double azimuth, elevation, dist;
		//calculating distance
		dist = distance3D(gps0, gps1);
		//converting to radiant
		lat_lon_alt gps0Rad = toRad (gps0);
		lat_lon_alt gps1Rad = toRad (gps1);
		//calculating azimuth
		double diff_lon = gps1Rad.y()-gps0Rad.y();
		double x = Math.sin(diff_lon)*Math.cos(gps1Rad.x());
		double y = Math.cos(gps0Rad.x())*Math.sin(gps1Rad.x())-Math.sin(gps0Rad.x())*Math.cos(gps1Rad.x())*Math.cos(diff_lon);
		azimuth = Math.atan2(x,y);
		azimuth = toDeg(azimuth);
		//calculating elevation
		double diff_alt = Math.abs(gps1Rad.z()-gps0Rad.z());
		if (gps1Rad.z() == gps0Rad.z())
			elevation = 0;
		else {
		elevation = Math.atan(diff_alt/dist);
		elevation = toDeg(elevation);
		if (gps1.z() < gps0.z())
			elevation = -elevation;
		}
		//creating array with azimuth, elevation and distance
		double [] azimuth_elevation_dist = {azimuth, elevation, dist};
		return azimuth_elevation_dist;
	}

	/**
	 * return true iff this point is a valid lat, lon , alt coordinate: [-90,+90],[-180,+180],[-450, +inf]
	 * @param p
	 * @return true iff this point has valid gps coordinates
	 */
	@Override
	public boolean isValid_GPS_Point(lat_lon_alt p) {

		//calling the function from lat_lon_alt that checks if it's a valid gps point
		return p.isValid_GPS_Point();
	}


}
