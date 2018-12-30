package GIS;

import java.io.IOException;

import Geom.Point3D;

public interface Meta_data {
	/** returns the Universal Time Clock associated with this data
	 * @throws IOException 
	 * @throws ParseException */
	public long getUTC() throws IOException;
	/** return a String representing this data */
	public String toString();
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation();
}
