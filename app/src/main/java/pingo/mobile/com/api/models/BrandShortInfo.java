
package pingo.mobile.com.api.models;


import com.google.gson.annotations.SerializedName;

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
