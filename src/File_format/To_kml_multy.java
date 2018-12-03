package File_format;

import java.io.IOException;
import java.util.Iterator;

/**
 * this class converts a Csv_project to multiple kml folmat files
 * @author Eitan Lichtman, Netanel Indik
 */
public class To_kml_multy {

	private Csv_project csv_project;
	private String outputFolder;

	/**
	 * constructor that initializes output and project
	 * @param output
	 * @param project
	 * @throws IOException
	 */
	public To_kml_multy(String output, Csv_project project) throws IOException{
		outputFolder = output;
		csv_project = new Csv_project(project);
	}

	/**
	 * converts multi csv files to kml
	 */
	void run() {
		Iterator<Csv_layer> it = csv_project.iterator_csv();
		int counter = 1;
		try {
			while(it.hasNext()) {
				To_kml kml = new To_kml(outputFolder + "\\fileNumber" + counter +".kml", it.next());
				kml.run();
				counter++;
			}
		} catch (IOException e) {
			System.out.println("invalid output folder!");
		}
	}
}
