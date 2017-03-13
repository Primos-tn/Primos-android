package pingo.mobile.com.api.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houssem.fathallah on 24/09/2016.
 */
public class SearchParamsOptions {
    private final String LOCATION_CENTER_KEY = "center";
    private final String LOCATION_DISTANCE_KEY = "distance";
    private String searchQuery;
    private int brandId;
    private List<Country> countries;
    private List<Category> categories;
    private LatLng mapCenter;
    private double distance;
    private static SearchParamsOptions instance;
    /**
     *
     */
    private int age;

    public SearchParamsOptions() {
        categories = new ArrayList<>();
        countries = new ArrayList<>();
    }

    /**
     * @return
     */
    public static SearchParamsOptions getInstance() {
        if (instance == null) {
            instance = new SearchParamsOptions();
        }
        return instance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the list
     *
     * @return
     */
    public int[] getCategoriesQueryString() {
        int[] categoriesQueryString = new int[categories.size()];
        int i = 0;
        for (Category category : categories) {
            categoriesQueryString[i] = category.getId();
        }
        return categoriesQueryString;
    }

    /**
     * @return
     */
    public void setLocationQueryString(LatLng center, double distance) {
        this.mapCenter = center;
        this.distance = distance;
    }

    /**
     * @return
     */
    public double[] getMapCenterQueryString() {
        double[] location ;
        if (mapCenter != null) {
            location = new double[]{mapCenter.latitude, mapCenter.longitude};
        }
        else {
            // get last loction
            location = new double[]{47.931066347509784, 2.0214843750000004} ;
        }
        return location ;
    }

    public double getMapDistanceQueryString (){
        double distanceInKm;
        if (mapCenter != null) {
            distanceInKm = distance;
        }
        else {
            distanceInKm = 284727.5676720289 ;
        }
        return  distanceInKm ;
    }

    /**
     * Returns the list
     *
     * @return
     */
    public String getCountriesQueryString() {
        String categoriesQueryString = "";
        for (Country country : countries) {
            categoriesQueryString += ";" + String.valueOf(country.getId());
        }
        return categoriesQueryString;
    }


    /**
     * Returns the list
     *
     * @return
     */
    public String getSearchQueryString() {
        return searchQuery;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
