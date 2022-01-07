package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfInterestTest {

    private PlaceOfInterest place;
    private GeoPoint point;
    private State visited;

    private static final String NAME = "UBC Campus";
    private static final double LAT = 49.2606;
    private static final double LON = -123.2460;
    private static final String VISITED = "Visited";
    private static final String NotVISITED = "OnBucketList";


    @BeforeEach
    void runBefore() {
        point = new GeoPoint(LAT, LON);
        place = new PlaceOfInterest(NAME, point);
        visited = State.VISITED;
    }

    @Test
    void testConstructor() {
        assertEquals(NAME, place.getName());
        assertEquals(LAT, place.getLocation().getLatitude());
        assertEquals(LON, place.getLocation().getLongitude());
        assertEquals(State.NotVISITED, place.getVisitingStatus());
        assertEquals(NotVISITED, place.getVisitingStatus().getDisplayString());
        assertNotEquals(VISITED, place.getVisitingStatus().getDisplayString());
    }

    @Test
    void testSetVisitingStatus() {
        assertEquals(State.NotVISITED, place.getVisitingStatus());
        place.setVisitingStatus(visited);
        assertEquals(State.VISITED, place.getVisitingStatus());
    }

    @Test
    void testBeenTo() {
        assertFalse(place.beenTo());
        place.setVisitingStatus(visited);
        assertTrue(place.beenTo());
    }

    @Test
    void testEqualsHashCode() {

        assertEquals(place, place);

        PlaceOfInterest place2 = new PlaceOfInterest(NAME, point);
        assertEquals(place.hashCode(), place2.hashCode());
        assertEquals(place, place2);

        assertNotEquals(place, null);

        PlaceOfInterest place3 = new PlaceOfInterest(null, null);
        assertNotEquals(place.hashCode(), place3.hashCode());
        assertNotEquals(place, place3);
    }



}