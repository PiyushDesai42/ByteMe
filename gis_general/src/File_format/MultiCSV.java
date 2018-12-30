package File_format;

import java.io.IOException;

import CSV.Csv_project;

/**
 * this class converts multiple csv files from a given folder to kml folmat
 * @author Eitan Lichtman, Netanel Indik
 */
public class MultiCSV {

	private String input;
	private String output;
	
	private int latIndex, lonIndex, altIndex;
	
	/**
	 * constructor that initializes input and output folders
	 * @param originalFolder
	 * @param outputFolder
	 */
	public MultiCSV(String originalFolder, String outputFolder,int latIndex, int lonIndex, int altIndex){
		this.latIndex = latIndex;
		this.lonIndex = lonIndex;
		this.altIndex = altIndex;
		input = originalFolder;
		output = outputFolder;
	}


	/**
	 * reads multiple csv files, converts and writes them to kml files
	 */
	void run_multi_kml() {
		try {
			From_csv_multy csv = new From_csv_multy(input,latIndex, lonIndex, altIndex);
			Csv_project project = csv.to_Csv_project();
			To_kml_multy kml = new To_kml_multy(output, project);
			kml.run();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * reads multiple csv files, converts and writes them to one kml file
	 */
	void run_single_kml() {
		try {
			From_csv_multy csv = new From_csv_multy(input,latIndex, lonIndex, altIndex);
			Csv_project project = csv.to_Csv_project();
			this.output = output + "\\project.kml";
			To_kml kml = new To_kml(output, project);
			kml.run_project();
			System.out.println("Done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * @return a project representing the csv files in the folder
	 */
	Csv_project getProject() {
		From_csv_multy csv = new From_csv_multy(input,latIndex, lonIndex, altIndex);
		Csv_project project = csv.to_Csv_project();
		return project;
	}


}

