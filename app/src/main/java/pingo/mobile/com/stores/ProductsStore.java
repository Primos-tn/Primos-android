package pingo.mobile.com.stores;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.api.models.ProductDetails;
import pingo.mobile.com.api.models.SearchParamsOptions;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.User;
import pingo.mobile.com.api.responses.LocationsApiResponse;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.api.routes.Products;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.api.services.ProductsService;
import pingo.mobile.com.utils.constants.Api;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import rx.Observable;
import rx.Observer;

import static pingo.mobile.com.utils.constants.Api.API_LIST_PAGE_LIMIT;
import static pingo.mobile.com.utils.constants.Api.getApiMaxPageLimit;

/**
 * Created by houssem.fathallah on 20/09/2016.
 */
public class ProductsStore {
    static List<Product> productList = new ArrayList<>();
    static ProductsService productsServiceInstance = null;

    /**
     * @return
     */
    public static ProductsService getBrandsServiceInstance() {
        if (productsServiceInstance == null) {
            productsServiceInstance = CommonRestApiService.getRestAdapter().create(ProductsService.class);
        }
        return productsServiceInstance;
    }


    /**
     * @return
     */
    public static Observable<ProductsApiResponse> getProducts(LatLng center, double distance) {
        SearchParamsOptions.getInstance().setLocationQueryString(center, distance);
        return ProductsStore.getProducts(0, Api.API_MAX_PAGE_LIMIT);
    }

    /**
     * @return
     */
    public static Observable<ProductsApiResponse> getProducts(int page) {
        return ProductsStore.getProducts(0, API_LIST_PAGE_LIMIT);
    }

    /**
     * @param productId
     * @return
     */
    public static Observable<LocationsApiResponse> getProductLocations(int productId) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        ProductsService service = restAdapter.create(ProductsService.class);
        return service.getAvailableLocations(productId);
    }

    /**
     * @param productId
     * @return
     */
    public static Observable<List<Picture>> getProductPictures(int productId) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        ProductsService service = restAdapter.create(ProductsService.class);
        return service.getFullPictures(productId);
    }

    /**
     * @param page
     * @return
     */
    public static Observable<ProductsApiResponse> getProducts(int page, int limit) {

        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        ProductsService service = restAdapter.create(ProductsService.class);
        SearchParamsOptions filterOptions = SearchParamsOptions.getInstance();
        Observable<ProductsApiResponse> list = service.getList(
                page,
                limit,
                filterOptions.getSearchQueryString(),
                filterOptions.getCategoriesQueryString(),
                filterOptions.getMapCenterQueryString(),
                filterOptions.getMapDistanceQueryString(),
                filterOptions.getAge());

        list.subscribe(new Observer<ProductsApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ProductsApiResponse newItems) {
                productList.addAll(newItems.getProductsList());
            }
        });

        return list;
    }

//
//    /**
//     * @param productId
//     * @return
//     */
//    public static Observable<ProductVoteResponse> addToWishList(int productId) {
//        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
//        ProductsService service = restAdapter.create(ProductsService.class);
//        return service.addToWishList(productId);
//    }


    /**
     * @return
     */
    public static List<Product> getLoadedProducts() {
        return productList;
    }


    /**
     * @return
     */
    public static Observable<ProductDetails> getProduct(int id) {
        return getBrandsServiceInstance().getProduct(id);
    }

    /**
     * @param index
     * @return
     */
    public static Product getProductFromStore(int index) {
        if (productList.size() > index) {
            return productList.get(index);
        }
        return null;
    }
}
