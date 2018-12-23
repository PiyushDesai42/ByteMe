package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Coords.Lat_lon_alt;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Packman;

/**
 * This class converts a csv file to a game.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv2Game {
	
	/**
	 * This constructor initiates the BufferedReader by a given input file path.
	 * @param input
	 */
	public Csv2Game(String input) {
		try {
			reader = new BufferedReader(new FileReader(input));
		} catch (IOException e) {
			System.out.println("invalid input folder!");
		}
	}

	/**
	 * This method reads the csv file, creates and returns a game.
	 * @return game
	 * @throws IOException
	 */
	public Game run()  throws IOException{
		try {
			String tl = reader.readLine();
			String[] titles = tl.split(",");
			Game game = to_game(titles);
			reader.close();
			return game;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	//********************private data and methods********************

	private BufferedReader reader;

	private Game to_game(String[] titles) {

		int sum_p = Integer.parseInt(titles[7]);
		int sum_f = Integer.parseInt(titles[8]);

		ArrayList<Packman> packmans = new ArrayList<Packman>();
		ArrayList<Fruit> fruits = new ArrayList<Fruit>();

		String thisLine;
		try {
			for(int i = 0; i < sum_p; i++) {
				thisLine = reader.readLine();
				String [] current = thisLine.split(",");

				double lat=Double.parseDouble(current[2]);
				double lon=Double.parseDouble(current[3]);
				double alt=Double.parseDouble(current[4]);

				Lat_lon_alt gps_point = new Lat_lon_alt(lat,lon,alt);

				double weight=Double.parseDouble(current[5]);
				double radius=Double.parseDouble(current[6]);

				Packman p = new Packman(gps_point, weight, radius);
				packmans.add(p);
			}

			for(int i = 0; i < sum_f; i++) {
				thisLine = reader.readLine();
				String [] current = thisLine.split(",");

				double lat=Double.parseDouble(current[2]);
				double lon=Double.parseDouble(current[3]);
				double alt=Double.parseDouble(current[4]);

				Lat_lon_alt gps_point = new Lat_lon_alt(lat,lon,alt);

				Fruit f = new Fruit(gps_point);
				fruits.add(f);
			}

			Game game = new Game(packmans, fruits);
			return game;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
