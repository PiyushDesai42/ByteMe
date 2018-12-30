package File_format;

import java.io.IOException;

import CSV.Csv_layer;

/**
 * this class converts a csv file to kml folmat
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv2kml {

	private String input;
	private String output;
	private int latIndex, lonIndex, altIndex;

	/**
	 * constructor that receives an input file and lat lon alt indexes
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public Csv2kml(String input,int latIndex, int lonIndex, int altIndex) throws IOException {
		this.latIndex = latIndex;
		this.lonIndex = lonIndex;
		this.altIndex = altIndex;
		this.input = input;
		this.output = input.substring(0, input.indexOf(".csv")) + ".kml";
	}

	/**
	 * reads a csv file converts it and writes it to a kml file
	 */
	void run() {
		try {
			From_csv csv = new From_csv(input, latIndex, lonIndex, altIndex);
			Csv_layer layer = csv.to_Csv_layer();
			To_kml kml = new To_kml(output, layer);
			kml.run_layer();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @return a layer representing the csv file
	 */
	Csv_layer getLayer() {
		Csv_layer layer = null;
		try {
			From_csv csv = new From_csv(input,latIndex, lonIndex, altIndex);
			layer = csv.to_Csv_layer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return layer;
	}
}
