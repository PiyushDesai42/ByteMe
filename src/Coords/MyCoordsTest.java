package Coords;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Geom.gps_vector;
import Geom.lat_lon_alt;

class MyCoordsTest {


	@Test
	void testAdd() {
		lat_lon_alt gps0 = new lat_lon_alt(32.103315,35.209039,670);
		gps_vector vector = new gps_vector(337.6989921,-359.2492069,-20);
		MyCoords c = new MyCoords();
		lat_lon_alt gps1 = c.add(gps0,vector);
		lat_lon_alt gps_target = new lat_lon_alt(32.106352,35.205225,650);
		double eps=0.01;
		assertTrue(Math.abs(gps1.x()-gps_target.x())<eps);
		assertTrue(Math.abs(gps1.y()-gps_target.y())<eps);
		assertTrue(Math.abs(gps1.z()-gps_target.z())<eps);
	}

	@Test
	void testDistance3d() {
		lat_lon_alt gps0 = new lat_lon_alt(32.103315,35.209039,670);
		lat_lon_alt gps1 = new lat_lon_alt(32.106352,35.205225,650);
		MyCoords c = new MyCoords();
		double eps=0.01;
		assertTrue(Math.abs(c.distance3D(gps0,gps1)-493.0523318)<eps);
	}

	@Test
	void testVector3D() {
		lat_lon_alt gps0 = new lat_lon_alt(32.103315,35.209039,670);
		lat_lon_alt gps1 = new lat_lon_alt(32.106352,35.205225,650);
		MyCoords c = new MyCoords();
		gps_vector vector = c.vector3D(gps0,gps1);
		gps_vector vector_target = new gps_vector(337.6989921,-359.2492069,-20);
		double eps=0.01;
		assertTrue(Math.abs(vector.x()-vector_target.x())<eps);
		assertTrue(Math.abs(vector.y()-vector_target.y())<eps);
		assertTrue(Math.abs(vector.z()-vector_target.z())<eps);
	}

	//	@Test
	//	void testAzimuth_elevation_dist() {
	//		fail("Not yet implemented");
	//	}

	@Test
	void testIsValid_GPS_Point() {
		boolean valid;

		//bad gps points


		valid = true;
		try {
			lat_lon_alt gps0 = new lat_lon_alt(-95,50,50);
		}
		catch(RuntimeException e){
			valid = false;
		}
		assertTrue(!valid);



		valid = true;
		try {
			lat_lon_alt gps1 = new lat_lon_alt(95,50,50);
		}
		catch(RuntimeException e){
			valid = false;
		}
		assertTrue(!valid);



		valid = true;
		try {
			lat_lon_alt gps2 = new lat_lon_alt(50,-185,50);
		}
		catch(RuntimeException e){
			valid = false;
		}
		assertTrue(!valid);



		valid = true;
		try {
			lat_lon_alt gps3 = new lat_lon_alt(50,185,50);
		}
		catch(RuntimeException e){
			valid = false;
		}
		assertTrue(!valid);



		valid = true;
		try {
			lat_lon_alt gps4 = new lat_lon_alt(50,50,-455);
		}
		catch(RuntimeException e){
			valid = false;
		}
		assertTrue(!valid);


		//good gps point
		lat_lon_alt gps5 = new lat_lon_alt(50,50,50);
		gps_vector gps6 = new gps_vector(50,50,50);
	}
}
