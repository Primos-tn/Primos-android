package pingo.mobile.com.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by houssem.fathallah on 24/09/2016.
 */
public class ProductVoteResponse {
    boolean ok ;
    @SerializedName("is_voted")
    boolean isVoted ;

    public boolean getIsWished() {
        return isVoted;
    }
}
