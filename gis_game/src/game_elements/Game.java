package game_elements;

import java.util.ArrayList;
import java.util.Iterator;


public class Game {

	private ArrayList<Fruit> fruits;
	private ArrayList<Packman> packmans;	

	public Game() {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
	}

	public Game(ArrayList<Packman> p, ArrayList<Fruit> f) {
		fruits = new ArrayList<Fruit>();
		packmans = new ArrayList<Packman>();
		packmans.addAll(p);
		fruits.addAll(f);
//		Iterator<Packman> it_p = p.iterator();
//		while(it_p.hasNext()) {
//			packmans.add(it_p.next());
//		}
//
//		Iterator<Fruit> it_f = f.iterator();
//		while(it_f.hasNext()) {
//			fruits.add(it_f.next());
//		}
	}

	public Game(ArrayList<Object> arr) {
		if (arr.get(0) instanceof Packman) {
			Iterator<Object> it_p = arr.iterator();
			while(it_p.hasNext()) {
				packmans.add((Packman) it_p.next());
			}

			fruits = new ArrayList<Fruit>();
		}

		else {
			packmans = new ArrayList<Packman>();

			Iterator<Object> it_f = arr.iterator();
			while(it_f.hasNext()) {
				fruits.add((Fruit) it_f.next());
			}
		}
	}

	public Game(Game ot) {
		Iterator<Packman> it_p = ot.packmans.iterator();
		while(it_p.hasNext()) {
			packmans.add(it_p.next());
		}

		Iterator<Fruit> it_f = ot.fruits.iterator();
		while(it_f.hasNext()) {
			fruits.add(it_f.next());
		}
	}
	
	
	public boolean add_packman(Packman p) {
		return packmans.add(p);
	}

	public boolean add_fruit(Fruit f) {
		return fruits.add(f);
	}

	public boolean add_game(Game g) {
		return packmans.addAll(g.packmans) || fruits.addAll(g.fruits);
	}
	

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}


	public ArrayList<Packman> getPackmans() {
		return packmans;
	}

}
