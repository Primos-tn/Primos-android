
package pingo.mobile.com.api.responses;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.Category;

public class CategoriesApiResponse {
    @SerializedName("categories")
    private List<Category> categoryList;

    public CategoriesApiResponse(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
