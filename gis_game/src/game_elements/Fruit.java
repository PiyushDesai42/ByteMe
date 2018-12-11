package game_elements;

import Coords.Lat_lon_alt;

public class Fruit {

	private Lat_lon_alt gpsPoint;
	
	public Fruit(Lat_lon_alt gps) {
		this.gpsPoint = new Lat_lon_alt(gps);
	}
}
