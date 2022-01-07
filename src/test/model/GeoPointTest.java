package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeoPointTest {

    private GeoPoint gp;
    private static final double LAT = 200;
    private static final double LON = 200;

    @BeforeEach
    void testConstructor() {
        gp = new GeoPoint(LAT, LON);
        assertEquals(LAT, gp.getLatitude());
        assertEquals(LON, gp.getLongitude());
    }

    @Test
    void testEqualsAndHashcode() {

        assertEquals(gp, gp);

        GeoPoint gp2 = new GeoPoint(LAT, LON);
        assertEquals(gp.hashCode(), gp2.hashCode());
        assertEquals(gp, gp2);

        assertNotEquals(gp, null);

        GeoPoint gp3 = new GeoPoint(LAT+1, LON+1);
        assertNotEquals(gp.hashCode(), gp3.hashCode());
        assertNotEquals(gp, gp3);
    }

    @Test
    void testToString() {
        String str = gp.toString();
        assertEquals(LAT + "/" + LON, str);
    }

}
