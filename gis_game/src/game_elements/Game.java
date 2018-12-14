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



	public void add_packman(Packman p) {

	}

	public void add_fruit(Fruit f) {

	}

}
