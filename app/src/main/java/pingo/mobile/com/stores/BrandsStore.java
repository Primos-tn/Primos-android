package pingo.mobile.com.stores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pingo.mobile.com.api.models.brands.Brand;
import pingo.mobile.com.api.models.brands.BrandFollowResponse;
import pingo.mobile.com.api.models.brands.BrandShortInfo;
import pingo.mobile.com.api.models.accounts.SignInRequest;
import pingo.mobile.com.api.models.accounts.SignInResponse;
import pingo.mobile.com.api.responses.BrandsApiResponse;
import pingo.mobile.com.api.services.AccountsService;
import pingo.mobile.com.api.services.BrandsService;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.utils.constants.Api;
import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;

import static pingo.mobile.com.utils.constants.Api.API_LIST_PAGE_LIMIT;

/**
 * Class for handling accounts data
 * Created by houssem.fathallah on 20/09/2016.
 */
public class BrandsStore {
    static List<BrandShortInfo> brandShortInfoList = new ArrayList<>();
    static BrandsService brandsServiceInstance = null;

    /**
     * @return
     */
    public static BrandsService getBrandsServiceInstance() {
        if (brandsServiceInstance == null) {
            brandsServiceInstance = CommonRestApiService.getRestAdapter().create(BrandsService.class);
        }
        return brandsServiceInstance;
    }

    /**
     * @return
     */
    public static Observable<BrandsApiResponse> getBrands(int page) {
        return BrandsStore.getBrands(0, API_LIST_PAGE_LIMIT);
    }

    public static Observable<BrandsApiResponse> getUserBrands(int page, int userId) {
        return BrandsStore.getBrands(0, API_LIST_PAGE_LIMIT);
    }

    /**
     * @return
     */
    public static Observable<Brand> getBrandInfo(int id) {
        return getBrandsServiceInstance().getBrandInfo(id);
    }

    /**
     * @param page
     * @return
     */
    public static Observable<BrandsApiResponse> getBrands(int page, int limit) {
        Observable<BrandsApiResponse> list = getBrandsServiceInstance()
                .getBrands(page, limit, null, null, null, 0);
        list.subscribe(new Observer<BrandsApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BrandsApiResponse newItems) {
                // cache it
                brandShortInfoList.addAll(newItems.getBrandShortInfoList());
            }
        });

        return list;
    }

    /**
     * Login to app server
     *
     * @return
     */
    public static Observable<BrandFollowResponse> followBrand(int brandId) {
        return BrandsStore.getBrandsServiceInstance().followBrand(brandId);
    }

}
