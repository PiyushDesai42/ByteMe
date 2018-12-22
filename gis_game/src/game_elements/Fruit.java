package game_elements;

import Coords.Lat_lon_alt;

public class Fruit {

	private Lat_lon_alt gps_point;
	private double weight;
	
	public Fruit(Lat_lon_alt gps) {
		this.gps_point = new Lat_lon_alt(gps);
		weight = 1;
	}
	
	public Fruit(Lat_lon_alt gps, double weight) {
		this.gps_point = new Lat_lon_alt(gps);
		this.weight = weight;
	}

	public Lat_lon_alt getGps_point() {
		return gps_point;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
