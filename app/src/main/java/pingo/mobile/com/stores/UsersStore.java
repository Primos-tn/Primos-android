package pingo.mobile.com.stores;

import java.util.List;

import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.models.User;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.api.services.UsersService;
import pingo.mobile.com.utils.constants.Api;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by houssem.fathallah on 20/09/2016.
 */
public class UsersStore {
    /**
     * @param id
     * @param page
     * @return
     */
    public static Observable<List<User>> getFollowers(int id, int page) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        UsersService service = restAdapter.create(UsersService.class);
        return service.getFollowers(id, page, Api.API_LIST_PAGE_LIMIT);
    }

    /**
     * @param userId
     * @param page
     * @return
     */
    public static Observable<List<Product>> getWishList(int userId, int page) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        UsersService service = restAdapter.create(UsersService.class);
        return service.getWishList(userId, page, Api.API_LIST_PAGE_LIMIT);
    }



    /**
     * @param userId
     * @param page
     * @return
     */
    public static Observable<List<BrandShortInfo>> getBrandsList(int userId, int page) {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        UsersService service = restAdapter.create(UsersService.class);
        return service.getBrands(userId, page, Api.API_LIST_PAGE_LIMIT);
    }
}
