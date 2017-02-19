package pingo.mobile.com.stores;

import java.util.ArrayList;

import pingo.mobile.com.api.models.Category;
import pingo.mobile.com.api.responses.CategoriesApiResponse;
import pingo.mobile.com.api.services.CommonRestApiService;
import pingo.mobile.com.api.services.CategoriesService;
import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;

/**
 * Created by houssem.fathallah on 03/10/2016.
 */
public class CategoriesStore {
    public static ArrayList<Category> categories = new ArrayList<>();

    /**
     * @return
     */
    public static Observable<CategoriesApiResponse> fetchCategories() {
        RestAdapter restAdapter = CommonRestApiService.getRestAdapter();
        CategoriesService service = restAdapter.create(CategoriesService.class);
        Observable<CategoriesApiResponse> listObservableTags = service.getList();
        listObservableTags.subscribe(new Observer<CategoriesApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                System.out.print(e.toString());
            }

            @Override
            public void onNext(final CategoriesApiResponse categoriesApiResponse) {
                categories.addAll(categoriesApiResponse.getCategoryList());
            }
        });
        return listObservableTags;
    }

    /**
     * @return
     */
    public static ArrayList<Category> getCategories() {
        return categories;
    }
}
