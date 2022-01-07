package model;

/* CPSC 210 Term Project Version 2:
 * Travel Record - GeoPoint
 * Author:  Yun Xing
 * Date:    January 06, 2022,
 * Note:    This class is adapted from CPSC 210 Lab 8 ResourceFinder
 *
 * a GeoPoint contains two fields of double - latitude and longitude of the location
 * two points are equal if both their latitude and longitude are the same
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

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }


    @Override
    public String toString() {
        return lat + "/" + lon;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GeoPoint geoPoint = (GeoPoint) o;

        if (Double.compare(geoPoint.lat, lat) != 0) {
            return false;
        }
        return Double.compare(geoPoint.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
