
package pingo.mobile.com.api.models;

import com.google.gson.annotations.SerializedName;

public class Address {
    public String suite;
    public String city;
    public String street;
    public String zipcode;
    @SerializedName("geo")
    private Position position ;
    public Position getPosition() {
        return position;
    }
}
