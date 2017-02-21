package pingo.mobile.com.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.models.Location;

/**
 * Created by houssem.fathallah on 21/02/2017.
 */
public class BrandLocationsApiResponse {
    @SerializedName("stores")
    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }
}
