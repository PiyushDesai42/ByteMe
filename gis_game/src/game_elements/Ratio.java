package game_elements;

import Coords.Lat_lon_alt;

public class Ratio {

	private double width;
	private double height;
	
	
	public Ratio(Map map, Lat_lon_alt gps_point) {
		double[] width_height = to_ratio(map.getGps_min(), map.getGps_max(), gps_point);
		width = width_height[0];
		height = width_height[1];
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	private double[] to_ratio(Lat_lon_alt gps_min, Lat_lon_alt gps_max, Lat_lon_alt gps_point) {
		
		double[] width_height = new double[2];
		//finding width ratio of gps_min to gps_point out of the full width between gps_min to gps_max
		double full_width = gps_max.y()-gps_min.y();
		double partial_width = gps_point.y()-gps_min.y();
		width_height[0] = partial_width/full_width;
		//finding height ratio of gps_min to gps_point out of the full height between gps_min to gps_max
		double full_height = gps_max.x()-gps_min.x();
		double partial_height = gps_point.x()-gps_min.x();
		width_height[1] = partial_height/full_height;
		
		return width_height;
	}

	
	
	
}
