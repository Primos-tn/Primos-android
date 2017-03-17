package pingo.mobile.com.api.models.brands;

import com.google.gson.annotations.SerializedName;

/**
 * Created by houssem.fathallah on 24/09/2016.
 */
public class BrandFollowResponse {
    @SerializedName("is_following")
    boolean isFollowing ;

    public boolean getIsWished() {
        return isFollowing;
    }
}
