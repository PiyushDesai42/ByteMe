package File_format;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import GIS.Meta_data;
import Geom.Point3D;

/**
 * This class represents a Csv_meta_data that contains data about a gps point besides for it's coordinates.
 * If the list has only two Arrays:
 * it represents the data of an element with one Array for titles and one for data.
 * If the list has more than two Arrays:
 * it represents the data of a layer with one Array for titles and the rest for data.
 * If the list has more than two Arrays and contains Arrays with "\n":
 * it represents the data of a project with one Array for titles the rest for data and the "\n" Array separates the different layers.
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv_meta_data implements Meta_data{


	private List<String[]> data;

	/**
	 * constructor that initializes the data list to equal a given list
	 * @param dt
	 */
	public Csv_meta_data(List<String[]> dt) {
		data = new ArrayList<String[]>();
		Iterator<String[]> it = dt.iterator();
		while(it.hasNext())
			data.add(it.next());
	}
	
	/**
	 * copy constructor
	 * @param ot
	 */
	public Csv_meta_data(Csv_meta_data ot) {
		data = new ArrayList<String[]>();
		Iterator<String[]> it = ot.getData().iterator();
		while(it.hasNext())
			data.add(it.next());
	}


	/** 
	 * returns the Universal Time Clock associated with this data (first time in the data)
	 */
	@Override
	public long getUTC() throws IOException{
		int timeIndex = -1;
		String [] titles = data.get(0);
		for(int i = 0; i<titles.length; i++) {
			if(titles[i].contains("Time") || titles[i].contains("Seen"))
				timeIndex = i;
		}
		if (data.size()<2)
			throw new IOException();
		Iterator<String[]> it = data.iterator();
		it.next();
		String [] firstData = it.next();
		String currentDate = firstData[timeIndex];
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date d = null;
		try {
			d = f.parse(currentDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long utc = d.getTime();
		return utc;
	}

	/** 
	 *  return a String representing this data
	 */
	public String toString() {
		String s="";
		Iterator<String[]> it = data.iterator();
		while(it.hasNext())
			s = s + Arrays.toString(it.next()) + "\n";
		return s;
	}

	/**
	 * returns true iff the given object equals our Csv_meta_data
	 */
	public boolean equals(Object ot) {
		if (ot instanceof Csv_meta_data) {
			Iterator<String[]> it1 = data.iterator();
			Iterator<String[]> it2 = ((Csv_meta_data) ot).data.iterator();
			while(it1.hasNext() && it2.hasNext()) {
				if(!it1.next().equals(it2.next()))
					return false;
			}
			return !it1.hasNext() && !it2.hasNext();
		}
		else{
			return false;
		}
	}
	
	public List<String[]> getData() {
		return data;
	}


	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}

}
