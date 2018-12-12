package game_elements;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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

//	public Point gps_to_pixel(Lat_lon_alt gps) {
//
//	}
//
//	public Lat_lon_alt pixel_to_gps(Point pixel) {
//
//	}
}
