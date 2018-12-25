package convert;

import java.awt.Point;
import java.awt.geom.Point2D;

import Coords.Lat_lon_alt;
import game_elements.Map;

/**
 * This class contains static methods for converting coordinates
 * from pixels to lat_lon_alt points and vice versa.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Ratio {
	
	/**
	 * This is a static method that receives a map that has min and max gps points,
	 * full width and height of the map in pixels and a point in pixels.
	 * The method returns a gps point that represents the point in pixels.
	 * @param map
	 * @param full_width
	 * @param full_height
	 * @param pixels
	 * @return gps point
	 */
	public static Lat_lon_alt pixel2Lat_lon(Map map, int full_width, int full_height, Point pixels) {
		Point2D ratio = pixel2Ratio(full_width,full_height,pixels);
		return ratio2Lat_lon(map.getGps_min(),map.getGps_max(), ratio);
	}
	
	/**
	 * This is a static method that receives a map that has min and max gps points,
	 * full width and height of the map in pixels and a gps point.
	 * The method returns a point in pixels that represents the gps point.
	 * @param map
	 * @param gps_point
	 * @param full_width
	 * @param full_height
	 * @return point in pixels
	 */
	public static Point lat_lon2Pixel(Map map, Lat_lon_alt gps_point, int full_width, int full_height) {
		Point2D ratio = lat_lon2Ratio(map.getGps_min(),map.getGps_max(),gps_point);
		return ratio2Pixel(full_width, full_height, ratio);
	}

	
	
	//********************private data and methods********************

	private static Point2D lat_lon2Ratio(Lat_lon_alt gps_min, Lat_lon_alt gps_max, Lat_lon_alt gps_point) {
		//finding width ratio of gps_min to gps_point out of the full width between gps_min to gps_max
		double full_width = gps_max.y()-gps_min.y();
		double partial_width = gps_point.y()-gps_min.y();
		float width = (float)(partial_width/full_width);
		//finding height ratio of gps_min to gps_point out of the full height between gps_min to gps_max
		double full_height = gps_max.x()-gps_min.x();
		double partial_height = gps_max.x()-gps_point.x();
		float height = (float)(partial_height/full_height);

		return new Point2D.Float(width, height);
	}
	

	private static Point2D pixel2Ratio(int full_width, int full_height, Point p) {
		return new Point2D.Float((float)p.x/full_width, (float)p.y/full_height);
	}
	

	private static Lat_lon_alt ratio2Lat_lon(Lat_lon_alt gps_min, Lat_lon_alt gps_max, Point2D ratio) {
		double min_lat = gps_min.x();
		double min_lon = gps_min.y();
		double max_lat = gps_max.x();
		double max_lon = gps_max.y();

		double new_lat = min_lat + (max_lat-min_lat)*(1-ratio.getY());
		double new_lon = min_lon + (max_lon-min_lon)*ratio.getX();

		return new Lat_lon_alt(new_lat, new_lon, 0);
	}

	
	private static Point ratio2Pixel(int full_width, int full_height, Point2D ratio) {
		int new_width = (int)(full_width * ratio.getX());
		int new_height = (int)(full_height * ratio.getY());

		return new Point(new_width, new_height);
	}
}
