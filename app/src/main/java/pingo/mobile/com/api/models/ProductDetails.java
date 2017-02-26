
package pingo.mobile.com.api.models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetails {
    @SerializedName("is_voted")
    private boolean isVoted;
    @SerializedName("votes_count")
    private int votesCount;
    private List<Picture> pictures;
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public int getStoresCount() {
        return 2;
    }
}

