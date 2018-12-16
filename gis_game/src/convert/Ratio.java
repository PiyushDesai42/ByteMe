package convert;

import java.awt.Point;

import com.sun.javafx.geom.Point2D;

import Coords.Lat_lon_alt;
import game_elements.Map;

public class Ratio {

	private static double[] lat_lon_to_ratio(Lat_lon_alt gps_min, Lat_lon_alt gps_max, Lat_lon_alt gps_point) {
		
		double[] width_height = new double[2];
		//finding width ratio of gps_min to gps_point out of the full width between gps_min to gps_max
		double full_width = gps_max.y()-gps_min.y();
		double partial_width = gps_max.y()-gps_point.y();
		width_height[0] = partial_width/full_width;
		//finding height ratio of gps_min to gps_point out of the full height between gps_min to gps_max
		double full_height = gps_max.x()-gps_min.x();
		double partial_height = gps_max.x()-gps_point.x();
		width_height[1] = partial_height/full_height;
		
		return width_height;
	}

	
	

	public static Point2D lat_lon2Ratio(Map map, Lat_lon_alt gps_point) {
		float width = (float)lat_lon_to_ratio(map.getGps_min(), map.getGps_max(), gps_point)[0];
		float height = (float)lat_lon_to_ratio(map.getGps_min(), map.getGps_max(), gps_point)[1];
		return new Point2D(width, height);
	}
	
	
	
	public static Point2D pixel2Ratio(double full_width, double full_height, Point p) {
		return new Point2D((float)(p.x/full_width), (float)(p.y/full_height));
	}
	
	
	
	public static Lat_lon_alt ratio2Lat_lon(Map map, Point2D ratio) {
		double min_lat = map.getGps_min().x();
		double min_lon = map.getGps_min().y();
		double max_lat = map.getGps_max().x();
		double max_lon = map.getGps_max().y();
		
		double new_lat = min_lat + (max_lat-min_lat)*ratio.y;
		double new_lon = min_lon + (max_lon-min_lon)*(ratio.x);
		
		return new Lat_lon_alt(new_lat, new_lon, 0);
	}
	
	
	
	
	
	public static Point ratio2Pixel(int full_width, int full_height, Point2D ratio) {
		int new_width = (int)(full_width * ratio.x);
		int new_height = (int)(full_height * ratio.y);

		return new Point(new_width, new_height);
	}
	
	
	
	
	
}
