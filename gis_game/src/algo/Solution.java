package algo;

import java.util.ArrayList;
import java.util.Iterator;

import game_elements.Game;
import java.io.IOException;

/**
 * This class represents a full solution for a game using the algorithm method.
 * Includes multiple paths one for each packman, and the time of the slowest path.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Solution {
	
	/**
	 * This constructor initiates the ArrayList of paths.
	 */
	public Solution() {
		paths = new ArrayList<Path>();
	}
	
	/**
	 * This constructor receives a game.
	 * Creates a solution, and updates the time of the slowest path.
	 * @param game
	 */
	public Solution(Game game) {
		paths = new ArrayList<Path>();
		NavMesh navmesh = new NavMesh("gis_game\\Images\\layers.jpg");
		Algorithm.run_algo(game, paths, navmesh);
		longest_time();
	}

	/**
	 * This constructor receives another solution and copies it to this one.
	 * @param ot
	 */
	public Solution(Solution ot) {
		Iterator<Path> it = ot.paths.iterator();
		paths = new ArrayList<Path>();
		while(it.hasNext()) {
			Path p = new Path(it.next());
			paths.add(p);
		}
		time = ot.time;
	}

	/**
	 * This method receives a path and adds it to our ArrayList of paths.
	 * @param p
	 */
	public boolean add_path(Path p) {
		return paths.add(p);
	}

	/**
	 * This method returns the time it takes for all of the packmans to eat all of the fruits.
	 * @return time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * This method updates the time of our solution.
	 * @param time
	 */
	public void setTime(double time) {
		this.time = time;
	}

	/**
	 * This method returns an ArrayList of our paths.
	 * @return paths
	 */
	public ArrayList<Path> getPaths() {
		return paths;
	}



	//********************private data and methods********************

	private ArrayList<Path> paths;
	private double time;

	private void longest_time() {
		Iterator<Path> it = paths.iterator();
		double current_time, max_time = Double.MIN_VALUE;
		while(it.hasNext()) {
			current_time = it.next().getTime();
			if(current_time>max_time)
				max_time = current_time;
		}
		this.time=max_time;
	}
}
