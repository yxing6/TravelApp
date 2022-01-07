package persistence;

import model.GeoPoint;
import model.PlaceOfInterest;
import model.State;
import model.TravelList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;


// Represents a reader that reads a JSON representation of travel list from a file to program
// JsonRead.java is built with reference to the JsonSerializationDemo example created by Paul Carter

public class JsonReader {

    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads travelList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TravelList read() throws IOException {

        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseTravelList(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it;
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses travel list from JSON object and returns it
    private TravelList parseTravelList(JSONObject jsonObject) {

        TravelList travelList = new TravelList();
        addPlaces(travelList, jsonObject);

        return travelList;
    }


    // MODIFIES: travelList
    // EFFECTS: parses placeOfInterest from JSON object and adds them on to travelList
    private void addPlaces(TravelList travelList, JSONObject jsonObject) {

        JSONArray jsonArray = jsonObject.getJSONArray("places");

        for (Object json : jsonArray) {
            JSONObject nextPlace = (JSONObject) json;
            addOnePlace(travelList, nextPlace);
        }
    }

    // MODIFIES: travelList
    // EFFECTS: parses a PlaceOfInterest from JSON object and adds it to travelList
    private void addOnePlace(TravelList travelList, JSONObject jsonObject) {

        String name = jsonObject.getString("name");
        State status = State.valueOf(jsonObject.getString("status"));

        String locationString = jsonObject.getString("location");
        String[] locations = locationString.split("/");
        double lat = Double.parseDouble(locations[0]);
        double lon = Double.parseDouble(locations[1]);
        PlaceOfInterest poi = new PlaceOfInterest(name, new GeoPoint(lat, lon));
        poi.setVisitingStatus(status);
        travelList.addPlace(poi);
    }
}
