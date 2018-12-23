package algo;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Packman;
/**
 * This class has a static method with an algorithm.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Algorithm {

	/**
	 * This is a static method with an algorithm
	 * that tries finding the fastest way for the packmans to eat all of the fruit.
	 * @param game
	 * @param paths
	 * The method creates the closest it can to optimal paths.
	 */
	public static void run_algo(Game game, ArrayList<Path> paths) {

		ArrayList<Fruit> fruits = new ArrayList<Fruit>(game.getFruits());
		ArrayList<Packman> packmans = new ArrayList<Packman>();
		Iterator<Packman> it_packman = game.getPackmans().iterator();
		while(it_packman.hasNext()) {
			Packman p = it_packman.next();
			paths.add(new Path(p));
			packmans.add(new Packman(p));
		}

		while(!fruits.isEmpty()) {
			Fruit closest_fruit = null;
			double closest_fruit_length = Integer.MAX_VALUE;
			Path best_path = null;
			Packman best_packman = null;
			it_packman = packmans.iterator();
			Iterator<Path> it_path = paths.iterator();
			while(it_path.hasNext() && it_packman.hasNext()) {
				Path current_path = it_path.next();
				Packman current_packman = it_packman.next();
				Iterator<Fruit> it_fruit = fruits.iterator();
				Fruit current_fruit=null;
				while(it_fruit.hasNext()) {
					current_fruit = it_fruit.next();
					if(current_path.getTime()+seconds_to_fruit(current_packman, current_fruit)
					<closest_fruit_length) {
						best_packman = current_packman;
						best_path = current_path;
						closest_fruit = current_fruit;
						closest_fruit_length = current_path.getTime()+seconds_to_fruit(current_packman, current_fruit);
					}
				}
			}
			best_path.add_fruit(closest_fruit);
			fruits.remove(closest_fruit);
			best_path.setTime(closest_fruit_length);
			best_packman.setGps_point(closest_fruit.getGps_point());
		}
		
	}



	//********************private data and methods********************
	
	private static double seconds_to_fruit(Packman p, Fruit f) {
		MyCoords coords = new MyCoords();
		return (coords.distance3D(p.getGps_point(), f.getGps_point())-p.getRadius())/p.getMeters_per_sec();
	}

}
