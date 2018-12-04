package File_format;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import GIS.GIS_element;

public class Main {
	public static void main(String[] args) throws Exception {


//		String csv = "C:\\Users\\ACER\\Documents\\מדעי המחשב\\מונחה עצמים\\מטלות\\מטלה 2\\Ex2\\Ex2\\data\\WigleWifi_20171203085618.csv";
//		String kml = "C:\\Users\\ACER\\Documents\\מדעי המחשב\\מונחה עצמים\\מטלות\\מטלה 2\\Ex2\\Ex2\\data\\WigleWifi.kml";
//		try {
//			Csv2kml c2k = new Csv2kml(csv);
//			c2k.run();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		
		
		
				MultiCSV m = new MultiCSV("C:\\Users\\ACER\\Documents\\מדעי המחשב\\מונחה עצמים\\מטלות\\מטלה 2\\Ex2\\Ex2","C:\\Users\\ACER\\Documents\\מדעי המחשב\\מונחה עצמים\\מטלות\\מטלה 2\\Ex2\\Ex2");
					m.run_single_kml();

					
					
					
					
		//		String currentDate = "2017-12-01 10:49:08";
		//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//		java.util.Date d = null;
		//		try {
		//			d = f.parse(currentDate);
		//		} catch (ParseException e) {
		//			e.printStackTrace();
		//		}
		//		long utc = d.getTime();
		//		System.out.println(utc);
		//		
		//		System.out.println(new Date(utc));
		//	}


	}
}
