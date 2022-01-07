package model;

import org.json.JSONArray;
import org.json.JSONObject;
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
        placeA = new PlaceOfInterest("A", new GeoPoint(1, 2));
        placeB = new PlaceOfInterest("B", null);
        placeC = new PlaceOfInterest("C", null);
        placeD = new PlaceOfInterest("D", null);
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
    void testAddDupPlace() {
        travelList.addPlace(placeA);
        PlaceOfInterest samePlace = new PlaceOfInterest("A", new GeoPoint(1, 2));
        travelList.addPlace(samePlace);
        assertEquals(1, travelList.getPlaces().size());
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
    }

    @Test
    void testRemoveOnePlaceInList() {
        travelList.addPlace(placeA);
        travelList.addPlace(placeB);
        assertEquals(2, travelList.getPlaces().size());
        assertEquals(2, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
        travelList.removePlace(placeA);
        assertEquals(1, travelList.getPlaces().size());
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
        travelList.removePlace(placeA);
        assertEquals(1, travelList.getPlaces().size());
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
    }

    @Test
    void testRemoveOnePlaceNotInList() {
        travelList.addPlace(placeA);
        assertEquals(1, travelList.getPlaces().size());
        assertEquals(1, travelList.getBucketList().size());
        assertEquals(0, travelList.getVisitedList().size());
        travelList.removePlace(placeB);
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
    void testRemoveManyPlace() {
        travelList.addPlace(placeA);
        travelList.addPlace(placeB);
        travelList.addPlace(placeC);
        travelList.addPlace(placeD);
        travelList.removePlace(placeA);
        travelList.removePlace(placeB);
        assertEquals(2, travelList.getPlaces().size());
        assertEquals(2, travelList.getBucketList().size());
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


    @Test
    void testToJson() {
        travelList.addPlace(placeA);
        JSONObject json = travelList.toJson();
        JSONArray jArray = json.getJSONArray("places");
        JSONObject jsonPlace = jArray.getJSONObject(0);
        assertEquals(placeA.getName(), jsonPlace.getString("name"));
        assertEquals(placeA.getVisitingStatus(), jsonPlace.get("status"));
        assertEquals(placeA.getLocation().toString(), jsonPlace.get("location"));
    }
}
