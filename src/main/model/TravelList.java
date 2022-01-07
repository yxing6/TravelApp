package model;

import java.util.*;

// a list of places to hold all placeOfInterest
public class TravelList {

    private List<PlaceOfInterest> places;

    // EFFECTS: constructs an empty travelList
    public TravelList() {
        places = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a placeOfInterest to the list, maintains resources in the order added to registry
    public void addPlace(PlaceOfInterest place) {
    }

    // EFFECTS: returns places in travelList as an unmodifiable list
    public List<PlaceOfInterest> getPlaces() {
        return null;
    }

    // EFFECTS: returns set of places that has visitingStatus of NotVISITED
    public Set<PlaceOfInterest> getBucketList() {
        return null;
    }

    // EFFECTS: returns set of places that has visitingStatus of VISITED
    public Set<PlaceOfInterest> getVisitedList() {
        return null;
    }
}
