package pingo.mobile.com.api.models;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class Picture {
    @SerializedName("file")
    private PictureFile file;
    @SerializedName("brand_id")
    private int brandId;

    /**
     * @return
     */
    public int getBrandID() {
        return brandId;
    }

    /**
     * @return
     */
    public PictureFile getFile() {
        return file;
    }


}
