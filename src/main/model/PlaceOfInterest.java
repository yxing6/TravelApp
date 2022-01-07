package model;

// Represent a point of interest that has a name, location, and visiting status.
public class PlaceOfInterest {

    private String name;
    private GeoPoint location;
    private State visitingStatus;

    // EFFECTS: to construct an instance with name, location, and assign visiting status to not visited.
    public PlaceOfInterest(String name, GeoPoint location) {
        this.name = name;
        this.location = location;
        this.visitingStatus = State.NotVISITED;
    }

    public String getName() {
        return name;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public State getVisitingStatus() {
        return visitingStatus;
    }

    // MODIFIES: this
    // EFFECTS: change the visiting status
    public void setVisitingStatus(State visitingStatus) {
        this.visitingStatus = visitingStatus;
    }

    // EFFECTS: to return true if the placeOfInterest has a VISITED status
    //          false if NotVISITED
    public boolean beenTo() {
        return visitingStatus == State.VISITED;
    }
}
