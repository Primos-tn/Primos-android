/**
 * File UsersService
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.api.services;

import pingo.mobile.com.api.responses.LocationsApiResponse;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;


/**
 * Asynchronous execution requires the last parameter of the method be a Callback
 */
public interface LocationsService {
    @GET("/locations")
    Observable<LocationsApiResponse> getList(@Query("page") int page,
                                            @Query("limit") int limit,
                                            @Query("query") String searchQuery,
                                            @Query("categoriesList") String categoriesQueryString,
                                            @Query("map") String countriesQueryString,
                                            @Query("age") String age);

    @GET("/Products/{id}/stores")
    Observable<LocationsApiResponse> getAvailableStores(@Path("id") int id);
}