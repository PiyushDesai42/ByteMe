package game_elements;

import java.awt.Point;

import Coords.Lat_lon_alt;

public class Ratio {

	private static double[] lat_lon_to_ratio(Lat_lon_alt gps_min, Lat_lon_alt gps_max, Lat_lon_alt gps_point) {
		
		double[] width_height = new double[2];
		//finding width ratio of gps_min to gps_point out of the full width between gps_min to gps_max
		double full_width = gps_max.y()-gps_min.y();
		double partial_width = gps_point.y()-gps_min.y();
		width_height[0] = partial_width/full_width;
		//finding height ratio of gps_min to gps_point out of the full height between gps_min to gps_max
		double full_height = gps_max.x()-gps_min.x();
		double partial_height = gps_max.x()-gps_point.x();
		width_height[1] = partial_height/full_height;
		
		return width_height;
	}

	
	public static double lat_lon2Height(Map map, Lat_lon_alt gps_point) {
		return lat_lon_to_ratio(map.getGps_min(), map.getGps_max(), gps_point)[1];
	}
	
	public static double lat_lon2Width(Map map, Lat_lon_alt gps_point) {
		return lat_lon_to_ratio(map.getGps_min(), map.getGps_max(), gps_point)[0];
	}
	
	
	
	
	
	
	
private static double[] pixel_to_ratio(double full_width, double full_height, Point p) {
		
		double[] width_height = new double[2];
		//finding width ratio of pixels
		width_height[0] = p.x/full_width;
		//finding height ratio of pixels
		width_height[1] = p.y/full_height;
		
		return width_height;
	}

	
	public static double pixel2Height(double full_width, double full_height, Point p) {
		return pixel_to_ratio(full_width, full_height, p)[1];
	}
	
	public static double pixel2Width(double full_width, double full_height, Point p) {
		return pixel_to_ratio(full_width, full_height, p)[0];
	}
	
	
	
}
