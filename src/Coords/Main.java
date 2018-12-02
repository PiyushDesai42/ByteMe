package Coords;

public class Main {
public static void main(String[] args) {
//	Point3D x = new Point3D(-30.103315,35.209039,670);
//	Point3D y = new Point3D(32.106352,37.205225,650);
	Lat_lon_alt a = new Lat_lon_alt(32.103315,35.209039,670);
	Lat_lon_alt b = new Lat_lon_alt(32.106352,35.205225,650);
	Gps_vector c = new Gps_vector(337.6989921,-359.2492069,-20);
	MyCoords r = new MyCoords();
	System.out.println(r.distance3D(a,b));
	//Point3D v = new Point3D (r.vector3D(a,b));
	System.out.println(r.vector3D(a,b));
	//Point3D p = new Point3D (r.add(a,r));
	System.out.println(r.add(a,c));
	System.out.println(r.azimuth_elevation_dist(a, b)[0]);
	System.out.println(r.azimuth_elevation_dist(a, b)[1]);
	System.out.println(r.azimuth_elevation_dist(a, b)[2]);

//	double xRad = x.x()>0 ? (90-x.x())*Math.PI/180 : (-90-x.x())*Math.PI/180;
//	System.out.println(xRad);


}
}
