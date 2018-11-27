package Coords;
import Geom.gps_vector;
import Geom.lat_lon_alt;
public class Main {
public static void main(String[] args) {
//	Point3D x = new Point3D(-30.103315,35.209039,670);
//	Point3D y = new Point3D(32.106352,37.205225,650);
	lat_lon_alt a = new lat_lon_alt(32.103315,35.209039,670);
	lat_lon_alt b = new lat_lon_alt(32.106352,35.205225,650);
	gps_vector c = new gps_vector(337.6989921,-359.2492069,-20);
	MyCoords r = new MyCoords();
	System.out.println(r.distance3D(a,b));
	//Point3D v = new Point3D (r.vector3D(a,b));
	System.out.println(r.vector3D(a,b));
	//Point3D p = new Point3D (r.add(a,r));
	System.out.println(r.add(a,c));
//	double xRad = x.x()>0 ? (90-x.x())*Math.PI/180 : (-90-x.x())*Math.PI/180;
//	System.out.println(xRad);


}
}
