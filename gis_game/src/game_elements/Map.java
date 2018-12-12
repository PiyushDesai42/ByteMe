package game_elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Coords.Lat_lon_alt;

public class Map {

	BufferedImage image = null;
	private Lat_lon_alt gps_min, gps_max;

	public Map(String img, Lat_lon_alt min, Lat_lon_alt max) {
		try {
		    image = ImageIO.read(new File(img));
		} catch (IOException e) {
			System.out.println("could not load image!");
		}
		gps_min = new Lat_lon_alt(min);
		gps_max = new Lat_lon_alt(max);
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

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	

}
