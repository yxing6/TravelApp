package model;

public enum State {
    VISITED("Visited"),
    NotVISITED("OnBucketList");

    private String displayString;

    State(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return displayString;
    }
}
