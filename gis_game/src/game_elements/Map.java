package game_elements;

import javax.imageio.ImageIO;

import Coords.Lat_lon_alt;

public class Map {

	private Lat_lon_alt gps_min, gps_max;
	private ImageIO image;

	public Map(ImageIO img, Lat_lon_alt min, Lat_lon_alt max) {
		image = img;
		gps_min = new Lat_lon_alt(min);
		gps_max = new Lat_lon_alt(max);
	}
	

	public Map(Map ot) {
		image = ot.image;
		gps_min = new Lat_lon_alt(ot.gps_min);
		gps_max = new Lat_lon_alt(ot.gps_max);
	}
	

	public Lat_lon_alt getGps_min() {
		return gps_min;
	}

	public void setGps_min(Lat_lon_alt gps_min) {
		this.gps_min = gps_min;
	}

	public Lat_lon_alt getGps_max() {
		return gps_max;
	}

	public void setGps_max(Lat_lon_alt gps_max) {
		this.gps_max = gps_max;
	}

	public void setImage(ImageIO img) {
		this.image = img;
	}
	
	
	

}
