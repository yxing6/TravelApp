package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOfInterestTest {

    private PlaceOfInterest placeOfInterest;
    private String name;
    private GeoPoint location;

    private static final String NAME = "UBC Campus";
    private static final double LAT = 49.2606;
    private static final double LON = -123.2460;


    @BeforeEach
    void runBefore() {
        location = new GeoPoint(LAT, LON);
        placeOfInterest = new PlaceOfInterest(NAME, location);
    }

    @Test
    void testConstructor() {
        assertEquals(NAME, placeOfInterest.getName());
        assertEquals(LAT, placeOfInterest.getLocation().getLatitude());
        assertEquals(LON, placeOfInterest.getLocation().getLongitude());
        assertEquals(State.NotVISITED, placeOfInterest.getVisitingStatus());
        assertEquals(State.NotVISITED.getVisitingStatus(), placeOfInterest.getVisitingStatus().getVisitingStatus());
    }

    @Test
    void testSetVisitingStatus() {
        assertEquals(State.NotVISITED, placeOfInterest.getVisitingStatus());
        placeOfInterest.setVisitingStatus(State.VISITED);
        assertEquals(State.VISITED, placeOfInterest.getVisitingStatus());
    }

    @Test
    void testBeenTo() {
        assertFalse(placeOfInterest.beenTo());
        placeOfInterest.setVisitingStatus(State.VISITED);
        assertTrue(placeOfInterest.beenTo());
    }

}