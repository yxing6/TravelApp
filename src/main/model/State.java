package model;

/* CPSC 210 Term Project Version 2:
 * Travel Record - State
 * Author:  Yun Xing
 * Date:    January 06, 2022,
 *
 * a State represents the visiting status of a place, to be used as a field of PlaceOfInterest;
 * Possible alternative: a field of boolean in PlaceOfInterest;
 */
public enum State {
    VISITED("Visited"),
    NotVISITED("NotVisited");

    private String displayString;

    State(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return displayString;
    }
}
