/**
 * File UsersService
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.api.services;

import java.util.List;
import java.util.Map;

import pingo.mobile.com.api.models.Brand;
import pingo.mobile.com.api.models.BrandFollowResponse;
import pingo.mobile.com.api.models.Store;
import pingo.mobile.com.api.models.User;
import pingo.mobile.com.api.responses.BrandLocationsApiResponse;
import pingo.mobile.com.api.responses.BrandsApiResponse;
import pingo.mobile.com.api.routes.Brands;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Asynchronous execution requires the last parameter of the method be a Callback
 */
public interface BrandsService {
    @GET(Brands.BRANDS_LIST)
    Observable<BrandsApiResponse> getBrands(@Query("page") int page,
                                            @Query("limit") int limit,
                                            @Query("q") String searchQuery,
                                            @Query("categoriesList[]") int[] categoriesQueryString,
                                            @Query(value="map", encodeValue = true) Map<String, Object> countriesQueryString,
                                            @Query("userId") int userId);

    @GET(Brands.BRAND_FOLLOWERS)
    Observable<List<User>> getFollowers(@Path("id") int id);

    @GET(Brands.BRAND_INFO)
    Observable<Brand> getBrandInfo(@Path("id") int id);

    @GET(Brands.BRAND_STORES)
    Observable<BrandLocationsApiResponse> getLocations(@Path("id") int id);

    @GET(Brands.BRAND_PRODUCTS)
    void getProducts(@Path("id") int id);

    @POST(Brands.BRAND_FOLLOW)
    Observable<BrandFollowResponse> followBrand(@Path("id") int id);

    @POST(Brands.BRAND_UNFOLLOW)
    Observable<BrandFollowResponse> unFollowBrand(@Path("id") int id);

}