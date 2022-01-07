package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfInterestTest {

    private PlaceOfInterest placeOfInterest;
    private GeoPoint location;
    private State visited;

    private static final String NAME = "UBC Campus";
    private static final double LAT = 49.2606;
    private static final double LON = -123.2460;
    private static final String VISITED = "Visited";
    private static final String NotVISITED = "OnBucketList";


    @BeforeEach
    void runBefore() {
        location = new GeoPoint(LAT, LON);
        placeOfInterest = new PlaceOfInterest(NAME, location);
        visited = State.VISITED;
    }

    @Test
    void testConstructor() {
        assertEquals(NAME, placeOfInterest.getName());
        assertEquals(LAT, placeOfInterest.getLocation().getLatitude());
        assertEquals(LON, placeOfInterest.getLocation().getLongitude());
        assertEquals(State.NotVISITED, placeOfInterest.getVisitingStatus());
        assertEquals(NotVISITED, placeOfInterest.getVisitingStatus().getDisplayString());
        assertNotEquals(VISITED, placeOfInterest.getVisitingStatus().getDisplayString());
    }

    @Test
    void testSetVisitingStatus() {
        assertEquals(State.NotVISITED, placeOfInterest.getVisitingStatus());
        placeOfInterest.setVisitingStatus(visited);
        assertEquals(State.VISITED, placeOfInterest.getVisitingStatus());
    }

    @Test
    void testBeenTo() {
        assertFalse(placeOfInterest.beenTo());
        placeOfInterest.setVisitingStatus(visited);
        assertTrue(placeOfInterest.beenTo());
    }

}