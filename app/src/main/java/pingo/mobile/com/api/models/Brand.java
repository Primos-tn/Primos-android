
package pingo.mobile.com.api.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Brand implements Parcelable {
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

    protected Brand(Parcel in) {
        name = in.readString();
        address = in.readString();
        category = in.readString();
        id = in.readString();
        storesCount = in.readInt();
        reviewsCount = in.readInt();
        followersCount = in.readInt();
        facebookLink = in.readString();
        twitterLink = in.readString();
        linkedInLink = in.readString();
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public PictureFile getCover() {
        return cover;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(category);
        dest.writeString(id);
        dest.writeInt(storesCount);
        dest.writeInt(reviewsCount);
        dest.writeInt(followersCount);
        dest.writeString(facebookLink);
        dest.writeString(twitterLink);
        dest.writeString(linkedInLink);
    }
}
