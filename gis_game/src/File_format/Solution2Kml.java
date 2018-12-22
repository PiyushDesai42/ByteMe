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

public class Solution2Kml {

	private BufferedWriter writer;
	private Game game;
	private Solution solution;

	public Solution2Kml(String output, Solution ot) throws IOException{
		try {
			writer = new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
		solution = new Solution(ot);
		game = new Game();
		Iterator<Path> it = solution.getPaths().iterator();
		while(it.hasNext()) {
			game.addAll_fruit(it.next().getFruits());
		}
	}


	public Solution2Kml(String output, Game game) throws IOException{
		try {
			writer = new BufferedWriter(new FileWriter(output));
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
		solution = new Solution(game);
		this.game = new Game(game);
	}


	private void write_start() throws IOException {
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
		writer.write("<Document>\n");
		writer.write("<Style id=\"packman\">\n<IconStyle>\n<Icon>\n"
				+ "<href>\nhttp://earth.google.com/images/kml-icons/track-directional/track-0.png</href>\n"
				+ "</Icon>\n</IconStyle>\n</Style>\n");
		writer.write("<Style id=\"fruit\">\n<IconStyle>\n<Icon>\n"
				+ "<href>http://maps.google.com/mapfiles/kml/shapes/placemark_circle_highlight.png</href>\n"
				+ "</Icon>\n</IconStyle>\n</Style>\n");
	}



	private void write_end() throws IOException {
		writer.write("</Document>\n");
		writer.write("</kml>\n");
	}


	private void write_fruit_placemark(Fruit current_fruit) throws IOException {
		writer.write("<Placemark>\n");
		writer.write("<description>\n");
		writer.write("Weight: <b>" + current_fruit.getWeight() + "</b><br/>\n");
		writer.write("</description>\n");
		writer.write("<Point>\n");
		writer.write("<coordinates>");
		double lat = current_fruit.getGps_point().y();
		double lon = current_fruit.getGps_point().x();
		double alt = current_fruit.getGps_point().z();
		writer.write(lat + ", " + lon + ", " + alt);        
		writer.write("</coordinates>\n");
		writer.write("<altitudeMode>clampToGround" + "</altitudeMode>");
		writer.write("</Point>\n");
		writer.write("<styleUrl>#fruit</styleUrl>\n");
		writer.write("</Placemark>\n");
	}



	private void write_path_placemark(Path current_path, int counter) throws IOException {
		MyCoords mc = new MyCoords();
		writer.write("<Placemark>\n");
		writer.write("<name>Packman_"+counter+"</name>\n");
		writer.write("<styleUrl>#packman</styleUrl>\n");
		//		writer.write("<name>Packman_"+counter+"</name>\n");
		//		writer.write("<description>\n");
		//		writer.write("Radius: <b>" + packman.getRadius() + "</b><br/>\n");
		//		writer.write("Speed: <b>" + packman.getMeters_per_sec() + " meters per second</b><br/>\n");
		//		writer.write("</description>\n");
		writer.write("<gx:Track>\n");

		Packman packman = current_path.getPackman();

		for(int i = 0; i < 2; i++) {
			double time_counter = 0;
			boolean end = false;
			ArrayList<Fruit> fruits = current_path.getFruits();
			Iterator<Fruit> it_f = fruits.iterator();
			Lat_lon_alt current_gps = packman.getGps_point();
			do{
				if(!it_f.hasNext()) {
					end = true;
				}

				if(i==0) {
					//				writer.write("<TimeStamp>\n");
					writer.write("<when>"+LocalDateTime.now().plusSeconds((long) time_counter)+"</when>\n");
					//				writer.write("</TimeStamp>\n");
				}
				else if(i==1) {
					//				writer.write("<Point>\n");
					writer.write("<gx:coord>");
					double lat = current_gps.y();
					double lon = current_gps.x();
					double alt = current_gps.z();
					writer.write(lat + " " + lon + " " + alt);        
					writer.write("</gx:coord>\n");
					//				writer.write("</Point>\n");
				}

				if(!end) {
					Fruit fruit = it_f.next();
					time_counter += mc.distance3D(current_gps, fruit.getGps_point())/packman.getMeters_per_sec();
					current_gps = fruit.getGps_point();
				}
			}while(!end);

		}
		

		writer.write("</gx:Track>\n");
		writer.write("</Placemark>\n");


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
		writer.write("<Folder><name>packmans</name>\n");
		
		Iterator<Path> it_path = solution.getPaths().iterator();
		int path_counter = 0;
		while(it_path.hasNext()) {
			Path current_path = it_path.next();
			write_path_placemark(current_path, path_counter);
			path_counter++;
		}
		writer.write("</Folder>");
	}



	public void run() throws IOException {
		write_start();
		write_body();
		write_end();
		writer.close();
		System.out.println("Done!");
	}




}
