package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.Lat_lon_alt;
import Coords.MyCoords;
import algo.Path;
import algo.Solution;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Packman;

/**
 * This class converts a solution to a kml file.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Solution2Kml {
	
	/**
	 * This constructor initiates the BufferedWriter by a given output folder path.
	 * Receives a solution, and initiates our game and solution.
	 * @param output
	 * @param ot
	 * @throws IOException
	 */
	public Solution2Kml(String output, Solution s) throws IOException{
		try {
			writer = new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
		solution = new Solution(s);
		game = new Game();
		Iterator<Path> it = solution.getPaths().iterator();
		while(it.hasNext()) {
			game.addAll_fruit(it.next().getFruits());
		}
	}

	/**
	 * This constructor initiates the BufferedWriter by a given output folder path.
	 * Receives a game, and initiates our game and solution.
	 * @param output
	 * @param game
	 * @throws IOException
	 */
	public Solution2Kml(String output, Game game) throws IOException{
		try {
			writer = new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
		solution = new Solution(game);
		this.game = new Game(game);
	}

	/**
	 * This method converts our solution and writes it in kml format to a file.
	 * @throws IOException
	 */
	public void run() throws IOException {
		write_start();
		write_body();
		write_end();
		writer.close();
		System.out.println("Done!");
	}
	
	
	
	
	
	//********************private data and methods********************
	
	private BufferedWriter writer;
	private Game game;
	private Solution solution;
	
	
	private void write_start() throws IOException {
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
		writer.write("<Document>\n");
		writer.write("<Style id=\"packman\">\n<IconStyle>\n<Icon>\n"
				+ "<href>\nhttp://maps.google.com/mapfiles/kml/pal4/icon47.png</href>\n"
				+ "</Icon>\n</IconStyle>\n</Style>\n");
		writer.write("<Style id=\"fruit\">\n<IconStyle>\n<Icon>\n"
				+ "<href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>\n"
				+ "</Icon>\n</IconStyle>\n</Style>\n");
	}

	
	private void write_end() throws IOException {
		writer.write("</Document>\n");
		writer.write("</kml>");
	}


	private void write_fruit_placemark(Fruit current_fruit) throws IOException {
		writer.write("<Placemark>\n");
		writer.write("<description>");
		writer.write("Weight: <b>" + current_fruit.getWeight() + "</b><br/>");
		writer.write("</description>");
		writer.write("<Point>\n");
		writer.write("<coordinates>");
		double lat = current_fruit.getGps_point().y();
		double lon = current_fruit.getGps_point().x();
		double alt = current_fruit.getGps_point().z();
		writer.write(lat + "," + lon + "," + alt);        
		writer.write("</coordinates>");
		writer.write("</Point>\n");
		writer.write("<styleUrl>#fruit</styleUrl>");
		writer.write("</Placemark>\n");
	}


	private void write_path_placemark(Path current_path, int counter) throws IOException {
		MyCoords mc = new MyCoords();
		writer.write("<Folder>\n"
				+ "<name>Packman_"+counter+"</name>\n");
		Packman packman = current_path.getPackman();
		double time_counter = 0;
		boolean end = false;
		ArrayList<Fruit> fruits = current_path.getFruits();
		Iterator<Fruit> it_f = fruits.iterator();
		Lat_lon_alt current_gps = packman.getGps_point();

		writer.write("<description>");
		writer.write("Radius: <b>" + packman.getRadius() + "</b><br/>");
		writer.write("Speed: <b>" + packman.getMeters_per_sec() + " meters per second</b><br/>");
		writer.write("</description>\n");
		writer.write("<styleUrl>#packman</styleUrl>");


		do{
			if(!it_f.hasNext()) {
				end = true;
			}

			writer.write("<Placemark>\n");

			writer.write("<TimeStamp>\n");
			writer.write("<when>"+LocalDateTime.now().plusSeconds((long) time_counter)+"Z</when>\n");
			writer.write("</TimeStamp>\n");

			writer.write("<Point>\n");
			writer.write("<coordinates>");
			double lat = current_gps.y();
			double lon = current_gps.x();
			double alt = current_gps.z();
			writer.write(lat + "," + lon + "," + alt);        
			writer.write("</coordinates>\n");
			writer.write("</Point>\n");

			writer.write("</Placemark>\n");

			if(!end) {
			Fruit fruit = it_f.next();
			time_counter += mc.distance3D(current_gps, fruit.getGps_point())/packman.getMeters_per_sec();
			current_gps = fruit.getGps_point();
			}
		}while(!end);

		writer.write("</Folder>\n");
	}


	private void write_body() throws IOException{

		writer.write("<Folder>\n"
				+ "<name>Fruits</name>\n");
		Iterator<Fruit> it_fruit = game.getFruits().iterator();
		while(it_fruit.hasNext()) {
			Fruit current_fruit = it_fruit.next();
			write_fruit_placemark(current_fruit);
		}
		writer.write("</Folder>\n");

		Iterator<Path> it_path = solution.getPaths().iterator();
		int path_counter = 0;
		while(it_path.hasNext()) {
			Path current_path = it_path.next();
			write_path_placemark(current_path, path_counter);
			path_counter++;
		}
	}

}