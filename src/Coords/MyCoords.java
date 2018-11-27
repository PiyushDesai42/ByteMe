package Coords;

import Geom.gps_vector;
import Geom.lat_lon_alt;

public class MyCoords implements coords_converter{

	private final double earthRadius = 6371*1000;


	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param Point3D gps, Point3D vector
	 * @return point3D gps transformed
	 */
	@Override
	public lat_lon_alt add(lat_lon_alt gps, gps_vector vector){
		
		//change lat lon to polar
		double lat = Math.asin(vector.x()/earthRadius);
		double lon = Math.asin(vector.y()/(earthRadius*Math.cos(gps.x()*Math.PI/180)));
		//converting from radiant to degrees
		lat = lat / Math.PI * 180;
		lon = lon / Math.PI * 180;
		//calculating the new lat lon alt
		lat = gps.x() - lat;
		lon = gps.y() - lon;
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
		double diffX = gps1.x()-gps0.x();
		double diffY = gps1.y()-gps0.y();
		//converts to radiant
		double xRad = diffX*Math.PI/180;
		double yRad = diffY*Math.PI/180;
		//calculates the distance between the two x's y's and z's in meters
		double xMeters = Math.sin(xRad)*earthRadius;
		double yMeters = Math.sin(yRad)*earthRadius*Math.cos(gps0.x()*Math.PI/180);
		double zMeters = gps1.z()-gps0.z();
		//return the vector
		return new gps_vector(xMeters,yMeters,zMeters);
	}


	/**
	 * computes the 3D distance (in meters) between the two gps like points
	 * @param gps0, gps1
	 * @return distance
	 */
	@Override
	public double distance3D(lat_lon_alt gps0, lat_lon_alt gps1){
		
		//creating a vector of the difference in meters
		gps_vector vector = vector3D(gps0,gps1);
		//calculating the distance
		return Math.sqrt(vector.x()*vector.x() + vector.y()*vector.y());
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
		dist = distance3D(gps0,gps1);
		//calculating azimuth
		double diff_lon = gps1.y()-gps0.y();
		double x = Math.sin(diff_lon)*Math.cos(gps1.x());
		double y = Math.cos(gps0.x())*Math.sin(gps1.x())-Math.sin(gps0.x())*Math.cos(gps1.x())*Math.cos(diff_lon);
		azimuth = Math.atan2(x,y);
		//calculating elevation
		double diff_alt = Math.abs(gps1.z()-gps0.z());
		if (gps1.z() == gps0.z())
			elevation = 0;
		else if (gps1.z() > gps0.z())
			elevation = Math.atan(diff_alt/dist);
		else
			elevation = -Math.atan(diff_alt/dist);
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
