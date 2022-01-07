package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TravelListTest {

    private TravelList travelList;
    private PlaceOfInterest placeA;
    private PlaceOfInterest placeB;
    private PlaceOfInterest placeC;
    private PlaceOfInterest placeD;
    private State visited;
    private State notVisited;

    @BeforeEach
    void runBefore() {
        visited = State.VISITED;
        notVisited = State.NotVISITED;
        placeA = new PlaceOfInterest(null, null);
        placeB = new PlaceOfInterest(null, null);
        placeC = new PlaceOfInterest(null, null);
        placeD = new PlaceOfInterest(null, null);
        travelList = new TravelList();
    }

    @Test
    void testConstructor() {
        assertTrue(travelList.getPlaces().isEmpty());
        assertTrue(travelList.getVisitedList().isEmpty());
        assertTrue(travelList.getBucketList().isEmpty());
    }


    @Test
    void testAddOnePlace() {
        travelList.addPlace(placeA);
        assertEquals(1, travelList.getPlaces().size());
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
    }


    @Test
    void testAddManyPlace() {
        travelList.addPlace(placeA);
        travelList.addPlace(placeB);
        travelList.addPlace(placeC);
        travelList.addPlace(placeD);
        assertEquals(4, travelList.getPlaces().size());
        assertEquals(4, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
    }


    @Test
    void testVisitedPlace() {
        placeA.setVisitingStatus(visited);
        placeB.setVisitingStatus(visited);
        travelList.addPlace(placeA);
        travelList.addPlace(placeB);
        travelList.addPlace(placeC);
        travelList.addPlace(placeD);
        assertEquals(4, travelList.getPlaces().size());
        assertEquals(2, travelList.getBucketList().size());
        assertEquals(2, travelList.getVisitedList().size());
        placeC.setVisitingStatus(visited);
        placeD.setVisitingStatus(visited);
        assertEquals(0, travelList.getBucketList().size());
        assertEquals(4, travelList.getVisitedList().size());
        placeA.setVisitingStatus(notVisited);
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(3, travelList.getVisitedList().size());
    }
}
