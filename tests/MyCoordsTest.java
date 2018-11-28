
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Coords.MyCoords;
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
		lat_lon_alt gps2 = new lat_lon_alt(46.106352,35.205225,650);

		MyCoords c = new MyCoords();
		double eps=0.01;
		assertTrue(Math.abs(c.distance3D(gps0,gps1)-493.0523318)<eps);
		
		boolean valid = true;
		try {
			double dist = c.distance3D(gps0,gps2);
		}
		catch(RuntimeException e) {	
			valid = false;
		}
		assertTrue(!valid);
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

		@Test
		void testAzimuth_elevation_dist() {
			lat_lon_alt gps0 = new lat_lon_alt(32.103315,35.209039,670);
			lat_lon_alt gps1 = new lat_lon_alt(32.106352,35.205225,650);
			MyCoords c = new MyCoords();
			double [] azi_ele_dist  = c.azimuth_elevation_dist(gps0, gps1);
			double eps=0.01;
			double [] target = {-46.769579, -2.322852, 493.052331};
			assertTrue(Math.abs(azi_ele_dist[0]-target[0])<eps);
			assertTrue(Math.abs(azi_ele_dist[1]-target[1])<eps);
			assertTrue(Math.abs(azi_ele_dist[2]-target[2])<eps);
			}

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
