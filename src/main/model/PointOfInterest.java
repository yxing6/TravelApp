package model;

// Represent a point of interest that has a name, location, and visiting status.
public class PointOfInterest {

    private String name;
    private GeoPoint location;
    private State visitingStatus;

    public PointOfInterest(String name, GeoPoint location) {
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

    public void setVisitingStatus(State visitingStatus) {
        this.visitingStatus = visitingStatus;
    }

    public boolean beenTo() {
        return visitingStatus == State.VISITED;
    }
}
