package game_elements;

import Coords.Lat_lon_alt;

public class Packman {

	private Lat_lon_alt gps_point;
	private double meters_per_sec;
	private double orientation;
	
	public Packman(Lat_lon_alt gps, double speed,double orien) throws RuntimeException{
		this.gps_point = new Lat_lon_alt(gps);
		this.meters_per_sec = speed;
		this.orientation = orien;
		if(orientation<-180 || orientation>180) {
			throw new RuntimeException("invalid orientation!");
		}
	}
}


