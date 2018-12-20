package algo;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.Lat_lon_alt;
import Coords.MyCoords;
import convert.Ratio;
import game_elements.Fruit;
import game_elements.Packman;
import com.sun.javafx.geom.Point2D;

public class Path {

	private Packman packman;
	private ArrayList<Fruit> fruits;
	private double time;
	
	
	
	public Path(Packman p) {
		time = 0;
		packman = p;
		fruits = new ArrayList<Fruit>();
	}
	
	public Path(Packman p, ArrayList<Fruit> f) {
		time = 0;
		packman = p;
		fruits = new ArrayList<Fruit>();
		Iterator<Fruit> it = f.iterator();
		while(it.hasNext()) {
			fruits.add(it.next());
		}
	}
	
	public Path(Path ot) {
		time = ot.time;
		packman = ot.packman;
		fruits = new ArrayList<Fruit>();
		Iterator<Fruit> it = ot.fruits.iterator();
		while(it.hasNext()) {
			fruits.add(it.next());
		}
	}
	
	public boolean add_fruit(Fruit f) {
		return fruits.add(f);
	}
	
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Packman getPackman() {
		return packman;
	}

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	
//	public Lat_lon_alt place_at_time(double given_time) {
//		if(given_time==0) {
//			return packman.getGps_point();
//		}
//		if(given_time>time) {
//			return null;
//		}
//		MyCoords mc = new MyCoords();
//		double time_counter = 0;
//		Lat_lon_alt first_point = packman.getGps_point();
//		double last_time = 0;
//		Iterator<Fruit> it_f = fruits.iterator();
//		Lat_lon_alt	second_point = it_f.next().getGps_point();
//		time_counter += mc.distance3D(first_point, second_point)/packman.getMeters_per_sec();
//		while(it_f.hasNext() && given_time >= time_counter) {
//			if (given_time == time_counter)
//				return second_point;
//			else {
//				first_point = second_point;
//				second_point = it_f.next().getGps_point();
//				last_time = time_counter;
//				time_counter += mc.distance3D(first_point, second_point)/packman.getMeters_per_sec();
//			}
//		}
//		double diagnel_ratio = (time_counter - given_time)/(time_counter-last_time);
//		double lat,lon;
//		
//		if(first_point.x()<= second_point.x()) {
//			 lat = first_point.x()+(1-diagnel_ratio)*(second_point.x()-first_point.x());
//		}
//		else {
//			 lat = second_point.x()+diagnel_ratio*(first_point.x()-second_point.x());
//		}
//		if(first_point.x()<= second_point.x()) {
//			 lon = first_point.y()+(1-diagnel_ratio)*(second_point.y()-first_point.y());
//		}
//		else {
//			 lon = second_point.y()+diagnel_ratio*(first_point.y()-second_point.y());
//		}
//		
//		return new Lat_lon_alt(lat,lon,0);
//	}

}
