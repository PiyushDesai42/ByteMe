import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import Coords.Lat_lon_alt;
import convert.Ratio;
import game_elements.Map;

class RatioTest {


	@Test
	void testPixel2Lat_lon() {
		
		Lat_lon_alt min = new Lat_lon_alt(32.10190,35.20236,0);
		Lat_lon_alt max = new Lat_lon_alt(32.10582,35.21234,0);
		Map map = new Map(null, min, max);
		
		Lat_lon_alt new_point = Ratio.pixel2Lat_lon(map, 600, 400, new Point(300,200));
		Lat_lon_alt expected = new Lat_lon_alt(32.10386,35.20735,0.0);
		
		assertTrue(new_point.equals(expected));
	}

	@Test
	void testLat_lon2Pixel() {
		
		Lat_lon_alt min = new Lat_lon_alt(32.10190,35.20236,0);
		Lat_lon_alt max = new Lat_lon_alt(32.10582,35.21234,0);
		Map map = new Map(null, min, max);
		
		Point new_point = Ratio.lat_lon2Pixel(map, new Lat_lon_alt(32.10386,35.20735,0.0), 600, 400);
		Point expected = new Point(300,200);
		
		assertTrue(new_point.equals(expected));
		}

}
