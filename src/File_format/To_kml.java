package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class To_kml {

	private final BufferedWriter writer;
	private Csv_layer layer;

	public To_kml(String output, Csv_layer csv_layer) throws IOException {
		writer = new BufferedWriter(new FileWriter(output));
		layer = new Csv_layer(csv_layer);
	}
	
	private void writeStart() throws IOException {
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">");
		writer.write("<Document>");
		writer.write("<Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style>");
		writer.write("<Folder><name>Wifi Networks</name>\n");
	}
	
	
	private void writeEnd() throws IOException {
		writer.write("</Folder>\n");
		writer.write("</Document>\n");
		writer.write("</kml>");
	}
	
	
	private void writePlacemark(Csv_element element) throws IOException {
		Csv_meta_data md = element.getMeta_data();
		String [] str = md.getData().get(1);
		writer.write("<Placemark>\n");
		writer.write("<description>");
		ArrayList<String[]> data = (ArrayList<String[]>) element.getMeta_data().getData();
		Iterator<String[]> it = data.iterator();
		String [] titles = it.next();
		String [] dt = it.next();
		for(int i = 0; i < titles.length; i++) {
			writer.write(titles[i] + ": <b>" + dt[i] + "</b><br/>");
		}
		writer.write("</description>");
		writer.write("<styleUrl>#red</styleUrl>\n");
		writer.write("<Point>\n");
		writer.write("<coordinates>");
		double lat = element.getGps_coords().x();
		double lon = element.getGps_coords().y();
		double alt = element.getGps_coords().z();
		writer.write(lat + "," + lon + "," + alt);        
		writer.write("</coordinates>");
		writer.write("</Point>\n");
		writer.write("</Placemark>\n");
	}
	
	
	public void run() {
		try {
			writeStart();
			Iterator<Csv_element> it = layer.iterator_csv();
			while(it.hasNext()) {
				writePlacemark(it.next());
			}
			writeEnd();
			writer.close();
			} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
