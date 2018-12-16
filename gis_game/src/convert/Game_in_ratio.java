package convert;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.javafx.geom.Point2D;

import game_elements.Fruit;
import game_elements.Game;
import game_elements.Map;
import game_elements.Packman;

public class Game_in_ratio {

	private ArrayList<Point2D> fruits = new ArrayList<Point2D>();
	private ArrayList<Point2D> packmans = new ArrayList<Point2D>();

	public Game_in_ratio() {
		fruits = new ArrayList<Point2D>();
		packmans = new ArrayList<Point2D>();
	}

	public Game_in_ratio(Game game, Map map){
		Iterator<Packman> it_p = game.getPackmans().iterator();
		while(it_p.hasNext()) {
			Point2D current_p = Ratio.lat_lon2Ratio(map, it_p.next().getGps_point());
			packmans.add(current_p);
		}

		Iterator<Fruit> it_f = game.getFruits().iterator();
		while(it_f.hasNext()) {
			Point2D current_f = Ratio.lat_lon2Ratio(map, it_f.next().getGps_point());
			fruits.add(current_f);
		}
	}

	public boolean add_packman(Point2D p) {
		return packmans.add(p);
	}

	public boolean add_fruit(Point2D f) {
		return fruits.add(f);
	}

	public boolean add_game(Game g, Map map) {
		if(g.getFruits().isEmpty() && g.getPackmans().isEmpty())
			return false;
		Iterator<Packman> it_p = g.getPackmans().iterator();
		while(it_p.hasNext()) {
			Point2D current_p = Ratio.lat_lon2Ratio(map, it_p.next().getGps_point());
			packmans.add(current_p);
		}

		Iterator<Fruit> it_f = g.getFruits().iterator();
		while(it_f.hasNext()) {
			Point2D current_f = Ratio.lat_lon2Ratio(map, it_f.next().getGps_point());
			fruits.add(current_f);
		}
		return true;
	}

	public ArrayList<Point2D> getFruits() {
		return fruits;
	}

	public ArrayList<Point2D> getPackmans() {
		return packmans;
	}

}
