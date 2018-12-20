package Coords;
/**
 * this class implements the methods from the coords_converter interface
 * @author Eitan Lichtman, Netanel Indik
 */
public class MyCoords implements coords_converter{

	private final double earthRadius = 6371*1000;


	private Lat_lon_alt toRad (Lat_lon_alt gpsDeg) {
		return new Lat_lon_alt (gpsDeg.x()*Math.PI/180, gpsDeg.y()*Math.PI/180,gpsDeg.z());
	}

	private double toDeg(double rad) {
		return rad/Math.PI*180;
	}

	private double toRad(double deg) {
		return deg*Math.PI/180;
	}


	/**
	 * computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param Point3D gps, Point3D vector
	 * @return point3D gps transformed
	 */
	@Override
	public Lat_lon_alt add(Lat_lon_alt gps, Gps_vector vector){

		
		double diffLat = Math.asin(vector.x()/earthRadius);
		double lat = gps.x() + toDeg(diffLat);
		
		double lon_nor = Math.cos(toRad(gps.x()));
		double diffLon = Math.asin(vector.y()/(earthRadius*lon_nor));
		double lon = gps.y() + toDeg(diffLon);
		
		double alt = gps.z() + vector.z();
		
		return new Lat_lon_alt(lat, lon, alt);
//		//calculating the new lat lon alt
//		double lat = gps.x() + toDeg(vector.y()/earthRadius);
//		double lon = gps.y() + toDeg(vector.x()/earthRadius)/Math.cos(toRad(gps.x()));
//		double alt = gps.z() + vector.z();
//		//return new gps point
//		return new Lat_lon_alt(lat,lon,alt);
	}


	/**
	 * computes the 3D vector (in meters) between two gps like points
	 * @param gps0, gps1
	 * @return vector
	 */
	@Override
	public Gps_vector vector3D(Lat_lon_alt gps0, Lat_lon_alt gps1){

		//calculate the difference between the lat and lon of gps0 and gps1
		Lat_lon_alt diff = new Lat_lon_alt(gps1.x()-gps0.x(), gps1.y()-gps0.y(), gps1.z()-gps0.z());
		//converts to radiant
		Lat_lon_alt rad = toRad(diff);
		//calculates the distance between the two x's y's and z's in meters
		double xMeters = Math.sin(rad.x())*earthRadius;
		double yMeters = Math.sin(rad.y())*earthRadius*Math.cos(gps0.x()*Math.PI/180);
		double zMeters = rad.z();
		//return the vector
		return new Gps_vector(xMeters,yMeters,zMeters);
	}


	/**
	 * computes the 3D distance (in meters) between the two gps like points
	 * @param gps0, gps1
	 * @return distance
	 */
	@Override
	public double distance3D(Lat_lon_alt gps0, Lat_lon_alt gps1) throws RuntimeException{

		//creating a vector of the difference in meters
		Gps_vector vector = vector3D(gps0,gps1);
		//calculating the distance
		double dist = Math.sqrt(vector.x()*vector.x() + vector.y()*vector.y());
		if (dist > 100000) {
			throw new RuntimeException("these gps points are too far from each other!");
		}
		return dist;
	}

	public double azimuth(Lat_lon_alt gps0, Lat_lon_alt gps1) {
		double azimuth;
		//converting to radiant
		Lat_lon_alt gps0Rad = toRad (gps0);
		Lat_lon_alt gps1Rad = toRad (gps1);
		//calculating azimuth
		double diff_lon = gps1Rad.y()-gps0Rad.y();
		double x = Math.sin(diff_lon)*Math.cos(gps1Rad.x());
		double y = Math.cos(gps0Rad.x())*Math.sin(gps1Rad.x())-Math.sin(gps0Rad.x())*Math.cos(gps1Rad.x())*Math.cos(diff_lon);
		azimuth = Math.atan2(x,y);
		azimuth = toDeg(azimuth);
		return azimuth;
	}


	public double elevation(Lat_lon_alt gps0, Lat_lon_alt gps1) {
		double elevation;
		double dist = distance3D(gps0, gps1);
		//converting to radiant
		Lat_lon_alt gps0Rad = toRad (gps0);
		Lat_lon_alt gps1Rad = toRad (gps1);
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
		return elevation;
	}

	/**
	 * computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance
	 * @param gps0, gps1
	 * @return array with azimuth, elevation and distance
	 */
	@Override
	public double[] azimuth_elevation_dist(Lat_lon_alt gps0, Lat_lon_alt gps1){

		double azimuth, elevation, dist;
		//calculating distance
		dist = distance3D(gps0, gps1);
		//calculating azimuth
		azimuth = azimuth(gps0, gps1);
		//calculating elevation
		elevation = elevation(gps0,gps1);
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
	public boolean isValid_GPS_Point(Lat_lon_alt p) {

		//calling the function from lat_lon_alt that checks if it's a valid gps point
		return p.isValid_GPS_Point();
	}


}
