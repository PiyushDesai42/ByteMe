package algo;

import java.util.ArrayList;
import java.util.Iterator;

import game_elements.Game;

public class Solution {

	private ArrayList<Path> paths;
	private double time;
	public Solution(Game game) {
		paths = new ArrayList<Path>();
		Algorithm.run_algo(game, paths);
		longest_time();
}

	public Solution(Solution ot) {
		Iterator<Path> it = ot.paths.iterator();
		paths = new ArrayList<Path>();
		while(it.hasNext()) {
			Path p = new Path(it.next());
			paths.add(p);
		}
	}
	
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
	
	public boolean add_path(Path p) {
		return paths.add(p);
	}


	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public ArrayList<Path> getPaths() {
		return paths;
	}
	
}
