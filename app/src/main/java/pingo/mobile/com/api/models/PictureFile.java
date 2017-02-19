package pingo.mobile.com.api.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class PictureFile {
    private String url;

    public String getUrl() {
        return url;
    }

    @SerializedName("thumb")
    private ThumbnailInfo thumbnail;
    @SerializedName("small_thumb")
    private ThumbnailInfo smallThumbnail;

    public ThumbnailInfo getThumbnail() {
        return thumbnail;
    }

    public ThumbnailInfo getSmallThumbnail() {
        return smallThumbnail;
    }


    /**
     *
     */
    public class ThumbnailInfo {
        private String url;

        public String getUrl() {
            return url;
        }
    }
}