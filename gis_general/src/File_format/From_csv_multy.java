package File_format;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import CSV.Csv_layer;
import CSV.Csv_project;

/**
 * this class converts multiple csv files from a given folder to a Csv_project
 * @author Eitan Lichtman, Netanel Indik
 */
public class From_csv_multy {

	private List<String> filenames = new LinkedList<String>();

	private File originalFolder;
	
	private int latIndex, lonIndex, altIndex;

	public From_csv_multy(String folderName,int latIndex, int lonIndex, int altIndex) {
		this.latIndex = latIndex;
		this.lonIndex = lonIndex;
		this.altIndex = altIndex;
		originalFolder = new File(folderName);
		listFilesForFolder(originalFolder);
	}


	/**
	 * adds csv files in a given folder to our list
	 * @param folder
	 */
	public void listFilesForFolder(File folder) {
		try {
			for (final File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					listFilesForFolder(fileEntry);
				} else {
					if(fileEntry.getName().contains(".csv")) {
						filenames.add(fileEntry.getPath());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("invalid input folder!");
		}
	}

	/**
	 * @return list of csv file names in the folder
	 */
	public List<String> getFilenames() {
		return filenames;
	}


	/**
	 * @return a project representing all csv files in our folder
	 */
	public Csv_project to_Csv_project() {
		Iterator<String> it = filenames.iterator();
		Csv_project project = new Csv_project();
		try {
			while(it.hasNext()) {
				From_csv csv = new From_csv(it.next(),latIndex, lonIndex, altIndex);
				Csv_layer layer = csv.to_Csv_layer();
				project.add(layer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return project;

	}

}
