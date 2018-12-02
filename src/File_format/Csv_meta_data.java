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

public class Csv_meta_data implements Meta_data{


	private List<String[]> data;


	public Csv_meta_data(List<String[]> dt) {
		data = new ArrayList<String[]>();
		Iterator<String[]> it = dt.iterator();
		while(it.hasNext())
			data.add(it.next());
	}
	
	
	public Csv_meta_data(Csv_meta_data ot) {
		data = new ArrayList<String[]>();
		Iterator<String[]> it = ot.getData().iterator();
		while(it.hasNext())
			data.add(it.next());
	}


	/** returns the Universal Time Clock associated with this data (first time in the data)*/
	@Override
	public long getUTC(int timeIndex) throws IOException{
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

	/** return a String representing this data */
	public String toString() {
		String s="";
		Iterator<String[]> it = data.iterator();
		while(it.hasNext())
			s = s + Arrays.toString(it.next()) + "\n";
		return s;
	}

	
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
