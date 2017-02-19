/**
 * File UsersService
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.api.services;


import java.util.List;

import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.models.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Asynchronous execution requires the last parameter of the method be a Callback
 */
public interface UsersService {
    @GET("/users")
    void getUsers(Callback<List<User>> apiCallBack);
    @GET("/users/{id}/Brands/")
    Observable<List<BrandShortInfo>> getBrands(@Path("id") int id, @Query("page") int page, @Query("limit") int limit);

    @GET("/users/{id}/followers/")
    Observable<List<User>> getFollowers(@Path("id") int id, @Query("page") int page, @Query("limit") int limit);

    @GET("/users/{id}/wishes/")
    Observable<List<Product>> getWishList(@Path("id") int id, @Query("page") int page, @Query("limit") int limit);

    @GET("/users/{id}/following/")
    void getFollowing(@Path("id") int id, Callback<List<User>> apiCallBack);

    @GET("/users/{id}/wishes/")
    void getWishes(@Path("id") int id, Callback<List<Product>> apiCallBack);
}