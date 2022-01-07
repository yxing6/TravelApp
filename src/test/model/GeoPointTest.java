package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeoPointTest {

    private GeoPoint point;
    private static final double LAT = 200;
    private static final double LON = 200;

    @BeforeEach
    void testConstructor() {
        point = new GeoPoint(LAT, LON);
        assertEquals(LAT, point.getLatitude());
        assertEquals(LON, point.getLongitude());
    }

    @Test
    void testEqualsAndHashcode() {

        assertEquals(point, point);

        GeoPoint point2 = new GeoPoint(LAT, LON);
        assertEquals(point.hashCode(), point2.hashCode());
        assertEquals(point, point2);

        assertNotEquals(point, null);

        GeoPoint point3 = new GeoPoint(LAT+1, LON+1);
        assertNotEquals(point.hashCode(), point3.hashCode());
        assertNotEquals(point, point3);
    }

    @Test
    void testToString() {
        String str = point.toString();
        assertEquals(LAT + "/" + LON, str);
    }

}
