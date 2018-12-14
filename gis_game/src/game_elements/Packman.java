package game_elements;

import Coords.Lat_lon_alt;

public class Packman {

	private Lat_lon_alt gps_point;
	private double meters_per_sec;
	private double radius;
	private double orientation;
	
	public Packman(Lat_lon_alt gps, double speed, double r, double orien) throws RuntimeException{
		this.gps_point = new Lat_lon_alt(gps);
		this.meters_per_sec = speed;
		this.radius = r;
		this.orientation = orien;
		if(orientation<-180 || orientation>180) {
			throw new RuntimeException("invalid orientation!");
		}
	}
	
	public Packman(Lat_lon_alt gps) throws RuntimeException{
		this.gps_point = new Lat_lon_alt(gps);
		this.meters_per_sec = 1;
		this.radius = 1;
		this.orientation = 0;
		
	}
	
	public Packman(Packman ot) {
		this.gps_point = new Lat_lon_alt(ot.gps_point);
		this.meters_per_sec = ot.meters_per_sec;
		this.radius = ot.radius;
		this.orientation = ot.orientation;
	}

	public Lat_lon_alt getGps_point() {
		return gps_point;
	}

	public void setGps_point(Lat_lon_alt gps_point) {
		this.gps_point = gps_point;
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) throws RuntimeException{
		this.orientation = orientation;
		if(orientation<-180 || orientation>180) {
			throw new RuntimeException("invalid orientation!");
		}
	}

	public double getMeters_per_sec() {
		return meters_per_sec;
	}

	public double getRadius() {
		return radius;
	}
	
	
	
	
}


