package game_elements;

import java.util.ArrayList;

import javax.imageio.ImageIO;

import Coords.Lat_lon_alt;

public class Game {

	private ArrayList<Fruit> fruits;
	private ArrayList<Packman> packmans;
	private Map map;


	public Game(ImageIO img, Lat_lon_alt min, Lat_lon_alt max) {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
		map = new Map(img, min, max);
	}
	
	
	public Game(Map map) {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
		map = new Map(map);
	}



	public boolean add_packman(Packman p) {
		return packmans.add(p);
	}

	public boolean add_fruit(Fruit f) {
		return fruits.add(f);
	}

	
	
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}


	public ArrayList<Packman> getPackmans() {
		return packmans;
	}


	public Map getMap() {
		return map;
	}
	
}
