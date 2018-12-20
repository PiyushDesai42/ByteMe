package convert;

import java.awt.Point;

import com.sun.javafx.geom.Point2D;

import Coords.Gps_vector;
import Coords.Lat_lon_alt;
import Coords.MyCoords;
import game_elements.Map;

public class Ratio {
	

	public static Point2D lat_lon2Ratio(Map map, Lat_lon_alt gps_point) {
		Lat_lon_alt gps_min = map.getGps_min();
		Lat_lon_alt gps_max = map.getGps_max();
		//finding width ratio of gps_min to gps_point out of the full width between gps_min to gps_max
		double full_width = gps_max.y()-gps_min.y();
		double partial_width = gps_point.y()-gps_min.y();
		float width = (float)(partial_width/full_width);
		//finding height ratio of gps_min to gps_point out of the full height between gps_min to gps_max
		double full_height = gps_max.x()-gps_min.x();
		double partial_height = gps_max.x()-gps_point.x();
		float height = (float)(partial_height/full_height);

		return new Point2D(width, height);
	}



	public static Point2D pixel2Ratio(int full_width, int full_height, Point p) {
		return new Point2D((float)p.x/full_width, (float)p.y/full_height);
	}



	public static Lat_lon_alt ratio2Lat_lon(Map map, Point2D ratio) {
		double min_lat = map.getGps_min().x();
		double min_lon = map.getGps_min().y();
		double max_lat = map.getGps_max().x();
		double max_lon = map.getGps_max().y();

		double new_lat = min_lat + (max_lat-min_lat)*(1-ratio.y);
		double new_lon = min_lon + (max_lon-min_lon)*ratio.x;

		return new Lat_lon_alt(new_lat, new_lon, 0);
	}





	public static Point ratio2Pixel(int full_width, int full_height, Point2D ratio) {
		int new_width = (int)(full_width * ratio.x);
		int new_height = (int)(full_height * ratio.y);

		return new Point(new_width, new_height);
	}

	public static Lat_lon_alt pixel2Lat_lon(Map map, int full_width, int full_height, Point pixels) {
		Point2D ratio = pixel2Ratio(full_width,full_height,pixels);
		return ratio2Lat_lon(map, ratio);
	}

	public static Point lat_lon2Pixel(Map map, Lat_lon_alt gps_point, int full_width, int full_height) {
		Point2D ratio = lat_lon2Ratio(map,gps_point);
		return ratio2Pixel(full_width, full_height, ratio);
	}

	public static int meter2Pixel(Map map, Lat_lon_alt gps_point, int full_width, int full_height) {
		Point pixel = lat_lon2Pixel(map, gps_point, full_width, full_height);
		MyCoords coords = new MyCoords();
		Lat_lon_alt new_point = coords.add(gps_point, new Gps_vector(1, 0, 0));
		Point new_pixel = lat_lon2Pixel(map, new_point, full_width, full_height);
		return (int) Math.abs(pixel.getY()-new_pixel.getY());
		
	}

}
