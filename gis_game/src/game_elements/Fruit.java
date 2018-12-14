package game_elements;

import Coords.Lat_lon_alt;

public class Fruit {

	private Lat_lon_alt gps_point;
	
	public Fruit(Lat_lon_alt gps) {
		this.gps_point = new Lat_lon_alt(gps);
	}

	public Lat_lon_alt getGps_point() {
		return gps_point;
	}
}
