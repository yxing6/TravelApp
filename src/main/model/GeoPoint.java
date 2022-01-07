package model;

/* CPSC 210 Term Project Version 2:
 * Travel Record - GeoPoint
 * Author:  Yun Xing
 * Date:    January 06, 2022,
 * Note:    This class is adapted from CPSC 210 Lab 8 ResourceFinder
 *
 * a GeoPoint contains two fields of double - latitude and longitude of the location
 * costumed method toString built for the convenience of string parsing in JsonReader
 */
public class GeoPoint {
    private double lat;
    private double lon;

    // EFFECTS: constructs geo-point at given latitude (lat) and longitude (lon)
    public GeoPoint(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return lat + "/" + lon;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }
}
