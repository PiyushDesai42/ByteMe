package algo;

import java.util.ArrayList;
import java.util.Iterator;

import game_elements.Fruit;
import game_elements.Packman;

/**
 * This class represents a single path for one packman.
 * Including a single packman, an ArrayList of fruit,
 * and the time it takes for the packman to eat all of these fruit.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Path {

	
	
	/**
	 * This constructor receives a packman from a game,
	 * (our packman is pointing to the one from the game).
	 * @param p
	 */
	public Path(Packman p) {
		time = 0;
		packman = p;
		fruits = new ArrayList<Fruit>();
	}
	
	/**
	 * This constructor receives a packman from a game,
	 * (our packman is pointing to the one from the game).
	 * And receives an ArrayList of fruits that are copied to our path.
	 * @param p
	 * @param f
	 */
	public Path(Packman p, ArrayList<Fruit> f) {
		time = 0;
		packman = p;
		fruits = new ArrayList<Fruit>();
		Iterator<Fruit> it = f.iterator();
		while(it.hasNext()) {
			fruits.add(it.next());
		}
	}
	
	/**
	 * This constructor receives another path,
	 * and copies it to our path.
	 * @param ot
	 */
	public Path(Path ot) {
		time = ot.time;
		packman = ot.packman;
		fruits = new ArrayList<Fruit>();
		Iterator<Fruit> it = ot.fruits.iterator();
		while(it.hasNext()) {
			fruits.add(it.next());
		}
	}
	
	/**
	 * This method adds a single fruit to our list of fruits. 
	 * @param f
	 */
	public boolean add_fruit(Fruit f) {
		return fruits.add(f);
	}
	
	/**
	 * This method returns the time it takes for the packman to eat all of the fruit in the path.
	 * @return time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * This method allows to update the time for this path.
	 * @param time
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * This method returns the paths packman.
	 * @return packman
	 */
	public Packman getPackman() {
		return packman;
	}

	/**
	 * This method returns the paths ArrayList of fruit.
	 * @return fruits
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	
	//********************private data and methods********************
	
	private Packman packman;
	private ArrayList<Fruit> fruits;
	private double time;
	

}
