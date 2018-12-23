package game_elements;

import Coords.Lat_lon_alt;

/**
 * This class represents a fruit.
 * Including the gps point and weight of the fruit.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Fruit {

	/**
	 * This constructor receives a gps point and updates the gps point of our fruit.
	 * The default weight is 1.
	 * @param gps
	 */
	public Fruit(Lat_lon_alt gps) {
		this.gps_point = new Lat_lon_alt(gps);
		weight = 1;
	}
	
	/**
	 * This constructor receives a gps point and updates the gps point of our fruit.
	 * Also receives and updates the weight of our fruit.
	 * @param gps
	 * @param weight
	 */
	public Fruit(Lat_lon_alt gps, double weight) {
		this.gps_point = new Lat_lon_alt(gps);
		this.weight = weight;
	}
	
	/**
	 * This method returns the gps point of our fruit.
	 * @return gps_point
	 */
	public Lat_lon_alt getGps_point() {
		return gps_point;
	}
	
	/**
	 * This method returns the weight of our fruit.
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}
	
	/**
	 * This method updates the weight of our fruit.
	 * @param weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
	
	
	//********************private data and methods********************

	private Lat_lon_alt gps_point;
	private double weight;
}
