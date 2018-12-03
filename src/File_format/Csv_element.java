package File_format;

import Coords.MyCoords;
import Coords.Gps_vector;
import Coords.Lat_lon_alt;
import GIS.GIS_element;
import GIS.Meta_data;
import Geom.Geom_element;

/**
 * this class implements GIS_element,
 * and represents a Csv_element that contains a gps (Lat_lon_alt) Point and meta_data
 * @author Eitan Lichtman, Netanel Indik
 */
public class Csv_element implements GIS_element{
	
	private Lat_lon_alt gps_coords;
	private Csv_meta_data meta_data;
	
	/**
	 * constructor given a gps and meta data
	 * @param gps
	 * @param md
	 */
	public Csv_element(Lat_lon_alt gps, Csv_meta_data md) {
		gps_coords = gps;
		meta_data = md;
	}
	
	/**
	 * copy constructor
	 * @param ot
	 */
	public Csv_element(Csv_element ot) {
		gps_coords = new Lat_lon_alt(ot.getGeom().x(), ot.getGeom().y(), ot.getGeom().z());
		meta_data = new Csv_meta_data(ot.getMeta_data());
	}

	@Override
	public Lat_lon_alt getGeom() {
		return gps_coords;
	}

	@Override
	public Meta_data getData() {
		return meta_data;
	}

	@Override
	public void translate(Geom_element vec) {
		MyCoords mc = new MyCoords();
		gps_coords = mc.add(gps_coords, (Gps_vector)vec);
		}

	public Lat_lon_alt getGps_coords() {
		return gps_coords;
	}

	public void setGps_coords(Lat_lon_alt gps_coords) {
		this.gps_coords = gps_coords;
	}

	public Csv_meta_data getMeta_data() {
		return meta_data;
	}

	public void setMeta_data(Csv_meta_data meta_data) {
		this.meta_data = meta_data;
	}
	
	/**
	 * return a string of a csv element
	 */
	public String toString() {
		return gps_coords.toString() + "\n" + meta_data.toString();
	}
	
	/**
	 * returns true iff ot is the same element
	 */
	public boolean equals(Object ot) {
		if (ot instanceof Csv_element) {
			Csv_element element = (Csv_element)ot;
			return element.gps_coords.equals(((Csv_element) ot).gps_coords) &&
					element.getMeta_data().equals(((Csv_element) ot).getMeta_data());
		}
		else{
			return false;
		}
	}
	
}
