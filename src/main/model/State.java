package model;

public enum State {
    VISITED("Visited"),
    NotVISITED("OnBucketList");

    private String visitingStatus;

    State(String visitingStatus) {
        this.visitingStatus = visitingStatus;
    }

    public String getVisitingStatus() {
        return visitingStatus;
    }
}
