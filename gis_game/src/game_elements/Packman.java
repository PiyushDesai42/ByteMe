package game_elements;

import Coords.Lat_lon_alt;

/**
 * This class represents a packman.
 * Including the gps point, speed radius and orientation (right or left) of the packman.
 * @author Eitan Lichtman, Netanel Indik
 *
 */
public class Packman {
	
	/**
	 * This constructor receives and updates the gps point, speed and radius of our packman.
	 * @param gps
	 * @param speed
	 * @param r
	 * @throws RuntimeException
	 */
	public Packman(Lat_lon_alt gps, double speed, double r) throws RuntimeException{
		this.gps_point = new Lat_lon_alt(gps);
		this.meters_per_sec = speed;
		this.radius = r;
		this.orientation = "right";
	}
	
	/**
	 * This constructor receives and updates the gps point of our packman.
	 * @param gps
	 * @throws RuntimeException
	 */
	public Packman(Lat_lon_alt gps) throws RuntimeException{
		this.gps_point = new Lat_lon_alt(gps);
		this.meters_per_sec = 1;
		this.radius = 1;
		this.orientation = "right";
		
	}
	
	/**
	 * This constructor receives another packman and updates our packmans data.
	 * @param ot
	 */
	public Packman(Packman ot) {
		this.gps_point = new Lat_lon_alt(ot.gps_point);
		this.meters_per_sec = ot.meters_per_sec;
		this.radius = ot.radius;
		this.orientation = ot.orientation;
	}

	/**
	 * This method returns our packmans gps point.
	 * @return gps_point
	 */
	public Lat_lon_alt getGps_point() {
		return gps_point;
	}

	/**
	 * This method updates our packmans gps point.
	 * @param gps_point
	 */
	public void setGps_point(Lat_lon_alt gps_point) {
		this.gps_point = new Lat_lon_alt(gps_point);
	}

	/**
	 * This method returns our packmans orientation (right or left).
	 * @return orientation
	 */
	public String getOrientation() {
		return orientation;
	}

	/**
	 * This method updates our packmans orientation (right or left).
	 * @param orientation
	 * @throws RuntimeException
	 */
	public void setOrientation(String orientation) throws RuntimeException{
		this.orientation = orientation;
	}

	/**
	 * This method returns our packmans speed.
	 * @return meters_per_sec
	 */
	public double getMeters_per_sec() {
		return meters_per_sec;
	}

	/**
	 * This method returns our packmans radius.
	 * @return radius
	 */
	public double getRadius() {
		return radius;
	}
	
	
	
	
	
	//********************private data and methods********************

	private Lat_lon_alt gps_point;
	private double meters_per_sec;
	private double radius;
	private String orientation;
	
}


