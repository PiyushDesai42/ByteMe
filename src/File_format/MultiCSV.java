package File_format;

import java.io.IOException;

/**
 * this class converts multiple csv files from a given folder to kml folmat
 * @author Eitan Lichtman, Netanel Indik
 */
public class MultiCSV {

	private String input;
	private String output;
	
	/**
	 * constructor that initializes input and output folders
	 * @param originalFolder
	 * @param outputFolder
	 */
	public MultiCSV(String originalFolder, String outputFolder){
		input = originalFolder;
		output = outputFolder;
	}


	/**
	 * reads multiple csv files, converts and writes them to a kml file
	 */
	void run() {
		try {
			From_csv_multy csv = new From_csv_multy(input);
			Csv_project project = csv.to_Csv_project();
			To_kml_multy kml = new To_kml_multy(output, project);
			kml.run();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @return a project representing the csv files in the folder
	 */
	Csv_project getProject() {
		From_csv_multy csv = new From_csv_multy(input);
		Csv_project project = csv.to_Csv_project();
		return project;
	}


}

