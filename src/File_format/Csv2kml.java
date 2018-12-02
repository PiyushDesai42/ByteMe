package File_format;

import java.io.IOException;

public class Csv2kml {

	String input;
	String output;
	
	public Csv2kml(String input, String output) throws IOException {
		this.input = input;
		this.output = output;
	}
	
	void run() {
		try {
			From_csv csv = new From_csv(input);
			Csv_layer layer = csv.to_Csv_layer();
			To_kml kml = new To_kml(output, layer);
			kml.run();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
