package game_elements;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a game.
 * Including an ArrayList of packmans and an ArrayList of fruits.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Game {

	/**
	 * This constructor initiates our Lists.
	 */
	public Game() {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
	}

	/**
	 * This constructor initiates our Lists,
	 * and copies given lists to ours.
	 * @param p
	 * @param f
	 */
	public Game(ArrayList<Packman> p, ArrayList<Fruit> f) {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
		packmans.addAll(p);
		fruits.addAll(f);
	}

	/**
	 * This constructor receives another game and copies it to our game.
	 * @param ot
	 */
	public Game(Game ot) {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
		Iterator<Packman> it_p = ot.packmans.iterator();
		while(it_p.hasNext()) {
			packmans.add(it_p.next());
		}

		Iterator<Fruit> it_f = ot.fruits.iterator();
		while(it_f.hasNext()) {
			fruits.add(it_f.next());
		}
	}

	/**
	 * This method adds a packman to our game.
	 * @param p
	 * @return
	 */
	public boolean add_packman(Packman p) {
		return packmans.add(p);
	}

	/**
	 * This method adds a fruit to our game.
	 * @param f
	 * @return
	 */
	public boolean add_fruit(Fruit f) {
		return fruits.add(f);
	}

	/**
	 * This method adds a list of fruits to our game.
	 * @param f
	 */
	public void addAll_fruit(ArrayList<Fruit> f) {
		Iterator<Fruit> it_f = f.iterator();
		while(it_f.hasNext()) {
			fruits.add(it_f.next());
		}
	}

	/**
	 * This method adds another game to ours.
	 * @param g
	 */
	public void add_game(Game g) {
		packmans.addAll(g.packmans);
		fruits.addAll(g.fruits);
	}

	/**
	 * This method returns our list of fruits.
	 * @return fruits
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * This method returns our list of packmans.
	 * @return packmans
	 */
	public ArrayList<Packman> getPackmans() {
		return packmans;
	}

	/**
	 * This method clears our lists of fruits and packmans.
	 */
	public void clear() {
		packmans.clear();
		fruits.clear();
	}





	//********************private data and methods********************

	private ArrayList<Fruit> fruits;
	private ArrayList<Packman> packmans;	

}
