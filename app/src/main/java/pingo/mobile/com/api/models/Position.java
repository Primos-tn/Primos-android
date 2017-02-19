
package pingo.mobile.com.api.models;

public class Position {
    private String lat;
    private String lng;
    public double getLatitude() {
        return Double.parseDouble(lat);
    }
    public double getLongitude() {
        return Double.parseDouble(lng);
    }
}
