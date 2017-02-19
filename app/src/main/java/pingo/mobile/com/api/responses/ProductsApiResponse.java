
package pingo.mobile.com.api.responses;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import pingo.mobile.com.api.models.Category;
import pingo.mobile.com.api.models.Product;

public class ProductsApiResponse {
    @SerializedName("products")
    private List<Product> productsList;

    /**
     *
     * @param productsList
     */
    public ProductsApiResponse(List<Product> productsList) {
        this.productsList = productsList;
    }
    /**
     *
     * @return
     */
    public List<Product> getProductsList() {
        return productsList;
    }
}
