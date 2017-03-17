package pingo.mobile.com.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.brands.BrandShortInfo;

/**
 * Created by houssem.fathallah on 19/02/2017.
 */
public class BrandsApiResponse {

    @SerializedName("brands")
    private List<BrandShortInfo> brandShortInfoList;

    /**
     * @param brandShortInfoList
     */
    public BrandsApiResponse(List<BrandShortInfo> brandShortInfoList) {
        this.brandShortInfoList = brandShortInfoList;
    }

    /**
     * @return
     */
    public List<BrandShortInfo> getBrandShortInfoList() {
        return brandShortInfoList;
    }
}
