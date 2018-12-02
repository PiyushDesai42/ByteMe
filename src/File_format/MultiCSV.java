package File_format;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;
public class MultiCSV {

	private List<String> filenames = new LinkedList<String>();
	public void listFilesForFolder(File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if(fileEntry.getName().contains(".csv")) {
					System.out.println(fileEntry.getPath());
					filenames.add(fileEntry.getPath());
				}
			}
		}
	}


	void multiKml(String outputFile) {
		try {
			Iterator<String> it = filenames.iterator();
			int counter = 1;
			while(it.hasNext()) {
				String input = it.next();
				Csv2kml csv2kml= new Csv2kml(input, outputFile + "//" + counter + ".kml");
				csv2kml.run();
				counter++;
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

