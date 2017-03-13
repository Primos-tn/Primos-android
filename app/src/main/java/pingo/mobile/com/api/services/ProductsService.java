/**
 * File UsersService
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.api.services;

import java.util.List;
import java.util.Map;

import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.models.ProductDetails;
import pingo.mobile.com.api.routes.Products;
import pingo.mobile.com.api.models.ProductVoteResponse;
import pingo.mobile.com.api.models.User;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.api.responses.LocationsApiResponse;


import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Asynchronous execution requires the last parameter of the method be a Callback
 */
public interface ProductsService {
    @GET(Products.PRODUCTS_LIST)
    Observable<ProductsApiResponse> getList(@Query("page") int page,
                                            @Query("limit") int limit,
                                            @Query("q") String searchQuery,
                                            @Query("categoriesList[]") int[] categoriesQueryString,
                                            @Query(value="map[center][]") double[] mapCenter,
                                            @Query(value="map[distance]") Double mapDistance,
                                            @Query("age") int age);

    @GET(Products.PRODUCT_LOCATIONS)
    Observable<LocationsApiResponse> getAvailableLocations(@Path("id") int id);


    @GET(Products.PRODUCT_DETAILS)
    Observable<ProductDetails> getProduct(@Path("id") int id);

    @GET(Products.PRODUCT_VOTERS)
    Observable<List<User>> getVoters(@Query("page") int page);


    @GET(Products.PRODUCT_PICTURES)
    Observable<List<Picture>> getFullPictures(@Path("id") int id);

    @POST(Products.PRODUCT_VOTE)
    Observable<ProductVoteResponse> voteProduct(@Path("id") int id);

    @POST(Products.PRODUCT_UNVOTE)
    Observable<ProductVoteResponse> unVoteProduct(@Path("id") int id);

}