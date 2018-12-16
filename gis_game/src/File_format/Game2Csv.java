package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import Coords.Lat_lon_alt;
import game_elements.Fruit;
import game_elements.Game;
import game_elements.Packman;

public class Game2Csv {

	private BufferedWriter writer;
	private Game game = null;

	public Game2Csv(String output, Game g) {
		try {
			writer = new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
		game = new Game(g);
	}

	private void write_start() throws IOException {
		int p_size = game.getPackmans().size();
		int f_size = game.getFruits().size();
		writer.write("Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+ p_size +","+ f_size +"\r\n");
	}

	private void write_body() throws IOException {
		ArrayList<Packman> packmans = new ArrayList<Packman>(game.getPackmans());
		ArrayList<Fruit> fruits = new ArrayList<Fruit>(game.getFruits());

		Iterator<Packman> it_p = packmans.iterator();
		int counter = 0;
		while (it_p.hasNext()) {
			writer.write("P," + counter +",");
			counter++;

			Packman current_p = it_p.next();

			Lat_lon_alt current_gps_point = new Lat_lon_alt(current_p.getGps_point());
			double lat = current_gps_point.x();
			double lon = current_gps_point.y();
			double alt = current_gps_point.z();
			writer.write(lat + "," + lon + "," + alt + ",");

			double weight = current_p.getMeters_per_sec();
			double radius = current_p.getRadius();
			writer.write(weight + "," + radius + ",,\n");
		}

		Iterator<Fruit> it_f = fruits.iterator();
		counter = 0;
		while (it_f.hasNext()) {
			writer.write("F," + counter +",");
			counter++;

			Fruit current_f = it_f.next();

			Lat_lon_alt current_gps_point = new Lat_lon_alt(current_f.getGps_point());
			double lat = current_gps_point.x();
			double lon = current_gps_point.y();
			double alt = current_gps_point.z();
			writer.write(lat + "," + lon + "," + alt + ",");

			writer.write("1,,,\n");
		}

	}


	public void run() {
		try {
			write_start();
			write_body();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
