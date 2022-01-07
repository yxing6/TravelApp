package model;

import org.json.JSONObject;
import persistence.Writable;
import java.util.Objects;

/* CPSC 210 Term Project Version 2:
 * Travel Record - PlaceOfInterest Class
 * Author: Yun Xing
 * Date: January 06, 2022
 *
 * Represent a point of interest that has a name, location, and visiting status.
 * User should be able to construct an instance with name and location.
 * PlaceOfInterest is writable class can be written to external files.
 */
public class PlaceOfInterest implements Writable {

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


    // two places are equal if both their name and geoPoint are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlaceOfInterest that = (PlaceOfInterest) o;

        if (!Objects.equals(name, that.name)) {
            return false;
        }
        return Objects.equals(location, that.location);
    }


    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "{ " + "name: " + name
                + ", location: latitude = " + location.getLatitude() + " & longitude = " + location.getLongitude()
                + ", visiting status: " + visitingStatus + "}";
    }


    // EFFECTS: return a JSONObject representing a PlaceOfInterest
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("location", location.toString());
        json.put("status", visitingStatus);
        return json;
    }
}
