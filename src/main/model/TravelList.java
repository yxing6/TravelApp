package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.*;


/* CPSC 210 Term Project Version 2:
 * Travel Record - TravelList
 * Author: Yun Xing
 * Date: January 06, 2022
 *
 * a list of places to hold all placeOfInterest
 * User should be able to add and remove any instance of placeOfInterest from the list
 * TravelList is writable class can be written to external files.
 */

public class TravelList implements Writable {

    private List<PlaceOfInterest> places;

    // EFFECTS: constructs an empty travelList
    public TravelList() {
        places = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a placeOfInterest to the list, maintains resources in the order added to registry
    public boolean addPlace(PlaceOfInterest place) {
        if (!places.contains(place)) {
            return places.add(place);
        } else {
            throw new IllegalArgumentException();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a placeOfInterest from the list
    public boolean removePlace(PlaceOfInterest place) {
        return places.remove(place);
    }

    // EFFECTS: returns places in travelList as an unmodifiable list
    public List<PlaceOfInterest> getPlaces() {
        return Collections.unmodifiableList(places);
    }

    // EFFECTS: returns set of places that has visitingStatus of NotVISITED
    public List<PlaceOfInterest> getBucketList() {
        List<PlaceOfInterest> placeList = new LinkedList<>();
        for (PlaceOfInterest p: places) {
            if (!p.beenTo()) {
                placeList.add(p);
            }
        }
        return placeList;
    }

    // EFFECTS: returns set of places that has visitingStatus of VISITED
    public List<PlaceOfInterest> getVisitedList() {
        List<PlaceOfInterest> placeList = new LinkedList<>();
        for (PlaceOfInterest p: places) {
            if (p.beenTo()) {
                placeList.add(p);
            }
        }
        return placeList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("places", placesToJson());
        return json;
    }

    // EFFECTS: returns places in this travelList as a JSON Array
    private JSONArray placesToJson() {

        JSONArray jsonArray = new JSONArray();
        for (PlaceOfInterest p: places) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
