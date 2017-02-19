
package pingo.mobile.com.api.models;


import com.google.gson.annotations.SerializedName;

public class Location {
    public String name = "test";
    private Address address ;
    @SerializedName("brand_id")
    private int brandId ;

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getBrandId() {
        return brandId;
    }
}
