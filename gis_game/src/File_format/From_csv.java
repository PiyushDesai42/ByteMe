package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import CSV.Csv_element;
import CSV.Csv_layer;
import CSV.Csv_meta_data;
import Coords.Lat_lon_alt;

/**
 * this class converts a csv file to a Csv_layer
 * @author Eitan Lichtman, Netanel Indik
 */
public class From_csv {

	private BufferedReader reader;
	private int latIndex, lonIndex, altIndex;

	/**
	 * initializes BufferedReader
	 * @param input
	 * @throws IOException
	 */
	public From_csv(String input,int latIndex, int lonIndex, int altIndex) throws IOException {
		this.latIndex = latIndex;
		this.lonIndex = lonIndex;
		this.altIndex = altIndex;
		try {
			reader = new BufferedReader(new FileReader(input));
			} catch (IOException e) {
			System.out.println("invalid input folder!");
		}
		
	}


	/**
	 * creates a layer of elements, each element includes a lat_lon_alt Point and meta data
	 * @param titles
	 * @param latIndex
	 * @param lonIndex
	 * @param altIndex
	 * @return layer
	 * @throws IOException
	 */
	private Csv_layer lines (String[] titles) throws IOException{
		Csv_layer layer = new Csv_layer();
		String thisLine;
		try {
			while ((thisLine = reader.readLine()) != null) {
				String [] current = thisLine.split(",");
				double lat=Double.parseDouble(current[latIndex]);
				double lon=Double.parseDouble(current[lonIndex]);
				double alt=Double.parseDouble(current[altIndex]);
				Lat_lon_alt gpsCoords  = new Lat_lon_alt(lat, lon, alt);
				String [] newTitles = new String[titles.length-3];
				String [] data = new String [current.length-3]; 
				int pointer = 0;
				for (int i = 0; i < current.length; i++) {
					if(i!=latIndex && i!=lonIndex && i!=altIndex) {
						newTitles[pointer] = titles[i];
						data[pointer] = current[i];
						pointer++;
					}
				}
					ArrayList<String[]> meta_data = new ArrayList<String[]>();
					meta_data.add(newTitles);
					meta_data.add(data);
					Csv_meta_data md = new Csv_meta_data(meta_data);
					Csv_element element = new Csv_element(gpsCoords, md);
					layer.add(element);
				}
			return layer;
		} 
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	/** 
	 * @return Csv_layer representing the csv file
	 * @throws IOException
	 */
	public Csv_layer to_Csv_layer() throws IOException{
		try {
			reader.readLine();
			String tl = reader.readLine();
			String[] titles = tl.split(",");
			Csv_layer layer = lines(titles);
			reader.close();
			return layer;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
