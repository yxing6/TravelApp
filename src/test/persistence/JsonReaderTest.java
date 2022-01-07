package persistence;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;
import model.TravelList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


// JsonReaderTest.java is built with reference to the JsonSerializationDemo example created by Paul Carter


public class JsonReaderTest {

    @Test
    void testReaderNoneExistFileExpectException() {

        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            TravelList travelListIn = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expecting IOException
        }
    }

    @Test
    void testReaderEmptyTravelListNoException() {

        try {
            TravelList travelListOut = new TravelList();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyTravelList.json");
            writer.open();
            writer.write(travelListOut);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyTravelList.json");

            TravelList travelListIn = reader.read();
            assertEquals(0, travelListIn.getPlaces().size());
            assertTrue(travelListIn.getVisitedList().isEmpty());
            assertTrue(travelListIn.getBucketList().isEmpty());
        } catch (IOException e) {
            fail("Not expecting IOException");
        }
    }


    @Test
    void testReaderGeneralTravelListNoException() {

        try {
            TravelList travelListOut = new TravelList();

            String nameA = "UBC Campus";
            double latA = 49.2606;
            double lonA = -123.2460;
            PlaceOfInterest placeA = new PlaceOfInterest(nameA, new GeoPoint(latA, lonA));
            travelListOut.addPlace(placeA);

            String nameB = "Summer Palace";
            double latB = 40.0000;
            double lonB = 116.2755;
            PlaceOfInterest placeB = new PlaceOfInterest(nameB, new GeoPoint(latB, lonB));
            travelListOut.addPlace(placeB);
            placeB.setVisitingStatus(State.VISITED);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralTravelList.json");
            writer.open();
            writer.write(travelListOut);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralTravelList.json");
            TravelList travelListIn = reader.read();

            assertEquals(2, travelListIn.getPlaces().size());
            assertEquals(1, travelListIn.getBucketList().size());
            assertEquals(1, travelListIn.getVisitedList().size());

            Set<PlaceOfInterest> bucketList = travelListIn.getBucketList();
            for (PlaceOfInterest p: bucketList) {
                assertEquals(nameA, p.getName());
                assertEquals(latA, p.getLocation().getLatitude());
                assertEquals(lonA, p.getLocation().getLongitude());
                assertEquals(State.NotVISITED, p.getVisitingStatus());
            }

            Set<PlaceOfInterest> visitedList = travelListIn.getVisitedList();
            for (PlaceOfInterest p: visitedList) {
                assertEquals(nameB, p.getName());
                assertEquals(latB, p.getLocation().getLatitude());
                assertEquals(lonB, p.getLocation().getLongitude());
                assertEquals(State.VISITED, p.getVisitingStatus());
            }

        } catch (IOException e) {
            fail("Not expecting IOException");
        }
    }


}
