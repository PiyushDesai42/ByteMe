package game_elements;

import java.awt.Image;

import Coords.Lat_lon_alt;

/**
 * This class represents a map.
 * Including an image, and min max gps coordinates.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Map {

	/**
	 * This constructor receives and updates an image
	 * and min max gps coordinates of the image.
	 * @param img
	 * @param min
	 * @param max
	 */
	public Map(Image img, Lat_lon_alt min, Lat_lon_alt max) {
		image = img;
		gps_min = new Lat_lon_alt(min);
		gps_max = new Lat_lon_alt(max);
	}

/**
 * This constructor receives another map and copies it to ours.
 * @param ot
 */
	public Map(Map ot) {
		image = ot.image;
		gps_min = new Lat_lon_alt(ot.gps_min);
		gps_max = new Lat_lon_alt(ot.gps_max);
	}

	/**
	 * This method returns the min gps point of our image.
	 * @return gps_min
	 */
	public Lat_lon_alt getGps_min() {
		return gps_min;
	}

	/**
	 * This method updates the min gps point of our image.
	 * @param gps_min
	 */
	public void setGps_min(Lat_lon_alt gps_min) {
		this.gps_min = gps_min;
	}

	/**
	 * This method returns the max gps point of our image.
	 * @return gps_max
	 */
	public Lat_lon_alt getGps_max() {
		return gps_max;
	}

	/**
	 * This method updates the max gps point of our image.
	 * @param gps_max
	 */
	public void setGps_max(Lat_lon_alt gps_max) {
		this.gps_max = gps_max;
	}

	/**
	 * This method updates our image.
	 * @param img
	 */
	public void setImage(Image img) {
		this.image = img;
	}





	//********************private data and methods********************

	private Lat_lon_alt gps_min, gps_max;
	private Image image;

}
