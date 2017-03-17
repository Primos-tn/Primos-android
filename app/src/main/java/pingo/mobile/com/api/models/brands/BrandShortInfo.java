
package pingo.mobile.com.api.models.brands;


import com.google.gson.annotations.SerializedName;

import pingo.mobile.com.api.models.PictureFile;

public class BrandShortInfo {
    public int id;
    private String name;
    private PictureFile cover;
    @SerializedName("followers_count")
    private int followersCount ;
    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public PictureFile getCover() {
        return cover;
    }

    public int getFollowersCount() {
        return followersCount;
    }
}
