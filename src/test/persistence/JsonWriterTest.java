package persistence;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;
import model.TravelList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


// JsonWriteTest.java is built with reference to the JsonSerializationDemo example created by Paul Carter

class JsonWriterTest {

    @Test
    void testWriterInvalidFileExpectException() {
        try {
            TravelList travelList = new TravelList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expecting IOException
        }
    }


    @Test
    void testWriterEmptyTravelListNoException() {
        try {
            TravelList travelListOut = new TravelList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTravelList.json");
            writer.open();
            writer.write(travelListOut);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTravelList.json");
            TravelList travelListIn = reader.read();
            assertTrue(travelListIn.getPlaces().isEmpty());
            assertTrue(travelListIn.getBucketList().isEmpty());
            assertTrue(travelListIn.getVisitedList().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralTravelListNoException() {

        try {
            TravelList travelListOut = new TravelList();

            String nameA = "UBC Campus";
            double latA = 49.2606;
            double lonA = -123.2460;
            PlaceOfInterest placeA = new PlaceOfInterest(nameA, new GeoPoint(latA, lonA));
            travelListOut.addPlace(placeA);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTravelList.json");
            writer.open();
            writer.write(travelListOut);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTravelList.json");

            TravelList travelListIn = reader.read();

            assertEquals(1, travelListIn.getPlaces().size());
            assertEquals(1, travelListIn.getBucketList().size());
            assertEquals(0, travelListIn.getVisitedList().size());

            List<PlaceOfInterest> bucketList = travelListIn.getBucketList();
            for (PlaceOfInterest p: bucketList) {
                assertEquals(nameA, p.getName());
                assertEquals(latA, p.getLocation().getLatitude());
                assertEquals(lonA, p.getLocation().getLongitude());
                assertEquals(State.NotVISITED, p.getVisitingStatus());
            }

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
}