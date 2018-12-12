package game_elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.InputStream;

import Coords.Lat_lon_alt;

public class Map {

//	private Image image;
	private Lat_lon_alt gps_min, gps_max;

	public Map(String input, Lat_lon_alt min, Lat_lon_alt max) {
//		image = new Image();
		gps_min = new Lat_lon_alt(min);
		gps_max = new Lat_lon_alt(max);
	}

//	public static Point gps_to_pixel(Lat_lon_alt gps) {
//
//	}
//
//	public static Lat_lon_alt pixel_to_gps(Point pixel) {
//
//	}
}
