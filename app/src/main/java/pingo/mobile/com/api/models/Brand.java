
package pingo.mobile.com.api.models;


import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Brand {
    private String name;
    public String address;
    public String category;
    // public List<Store> Stores;
    private String id;
    private PictureFile cover;
    private int storesCount;
    private int reviewsCount;
    private int followersCount;
    @SerializedName("fb_link")
    private String facebookLink;
    @SerializedName("tw_link")
    private String twitterLink;
    @SerializedName("ln_link")
    private String linkedInLink;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public PictureFile getCover() {
        return cover;
    }
}
