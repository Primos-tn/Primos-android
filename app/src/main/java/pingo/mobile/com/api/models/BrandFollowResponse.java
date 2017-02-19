package pingo.mobile.com.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by houssem.fathallah on 24/09/2016.
 */
public class BrandFollowResponse {
    boolean ok ;
    @SerializedName("is_following")
    boolean isFollowing ;

    public boolean getIsWished() {
        return isFollowing;
    }
}
