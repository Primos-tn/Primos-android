package pingo.mobile.com.api.services;
import pingo.mobile.com.api.responses.CategoriesApiResponse;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by houssem.fathallah on 03/10/2016.
 */
public interface CategoriesService {
    @GET("/categories")
    Observable<CategoriesApiResponse> getList();
}