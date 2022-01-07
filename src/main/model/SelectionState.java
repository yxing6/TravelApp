package model;

import java.util.HashSet;
import java.util.Set;

// Represents the state of user selection
public class SelectionState {

    private TravelList travelList;
    private State visitedStatus;
    private boolean visited;

    // EFFECTS: constructs a selectionState with a travelList and a user selection of visited;
    public SelectionState(TravelList travelList) {
        this.travelList = travelList;
        visitedStatus = State.VISITED;
    //    visited = true;
    }

    // MODIFIES: this
    // EFFECTS: set user selection to be visited;
    public void setVisited() {
        visitedStatus = State.VISITED;
    }

    // MODIFIES: this
    // EFFECTS: set user selection to bucketList;
    public void setBucketed() {
        visitedStatus = State.NotVISITED;
    }


    // EFFECTS: return a set of places corresponding to the current user selection;
    //          if visitList is selected, return places with status VISITED;
    //          if bucketList is selected, return places with status NotVISITED;
    public  Set<PlaceOfInterest> getPlacesWithVisitingStatus() {

        return visitedStatus == State.VISITED ? travelList.getVisitedList() : travelList.getBucketList();

    }
}
