
package pingo.mobile.com.api.responses;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.Store;

public class LocationsApiResponse {
    @SerializedName("Stores")
    private List<Store> StoresList;

    /**
     *
     * @param StoresList
     */
    public LocationsApiResponse(List<Store> StoresList) {
        this.StoresList = StoresList;
    }
    /**
     *
     * @return
     */
    public List<Store> getCategoryList() {
        return StoresList;
    }
}
