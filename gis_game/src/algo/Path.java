package algo;

import java.util.ArrayList;
import java.util.Iterator;

import game_elements.Fruit;
import game_elements.Packman;

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

}
