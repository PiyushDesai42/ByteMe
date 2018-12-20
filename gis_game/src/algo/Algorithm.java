package algo;

import java.util.ArrayList;
import java.util.Iterator;

import Coords.MyCoords;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Packman;

public class Algorithm {

	public static void run_algo(Game game, ArrayList<Path> paths) {

		ArrayList<Fruit> fruits = new ArrayList<Fruit>(game.getFruits());
		Iterator<Packman> it_packman = game.getPackmans().iterator();
		while(it_packman.hasNext()) {
			paths.add(new Path(it_packman.next()));
		}
		
		
		
		while(!fruits.isEmpty()) {
			Iterator<Path> it_path = paths.iterator();
			while(!fruits.isEmpty() && it_path.hasNext()) {
				Path current_path = it_path.next();
				Fruit closest_fruit = null;
				double closest_fruit_length = Integer.MAX_VALUE;
				Iterator<Fruit> it_fruit = fruits.iterator();
				Fruit current_fruit=null;
				while(it_fruit.hasNext()) {
					current_fruit = it_fruit.next();
					if(current_path.getTime()+seconds_to_fruit(current_path.getPackman(), current_fruit)
							<closest_fruit_length) {
						closest_fruit = current_fruit;
						closest_fruit_length = current_path.getTime()+seconds_to_fruit(current_path.getPackman(), current_fruit);
					}
				}
				current_path.add_fruit(closest_fruit);
				fruits.remove(closest_fruit);
				current_path.setTime(closest_fruit_length);
			}
		}

		
		
	}

	private static double seconds_to_fruit(Packman p, Fruit f) {
		MyCoords coords = new MyCoords();
		return (coords.distance3D(p.getGps_point(), f.getGps_point())-p.getRadius())/p.getMeters_per_sec();
	}

}
