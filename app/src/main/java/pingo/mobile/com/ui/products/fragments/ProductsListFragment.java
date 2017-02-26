package pingo.mobile.com.ui.products.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.stores.ProductsStore;
import pingo.mobile.com.ui.common.EndlessRecyclerViewScrollListener;
import pingo.mobile.com.ui.products.ProductsListAdapter;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by houssem.fathallah on 18/02/2017.
 */

public class ProductsListFragment extends Fragment {
    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    Handler handler = new Handler();
    private RecyclerView recyclerView;
    private ProductsListAdapter mAdapter;

    final Runnable r = new Runnable() {
        public void run() {
            endlessRecyclerViewScrollListener.setIsLoading(false);
        }
    };
    private static ProductsListFragment instance;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance() {
        if (instance == null) {
            instance = new ProductsListFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_producs_list_fragment, container, false);

        /**
         * Any changes in adapter content cannot change the size of the RecyclerView itself.
         */
        recyclerView = (RecyclerView) view.findViewById(R.id.home_products_tab_recycler_view);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ProductsListAdapter(getContext(), new ArrayList<Product>());
        recyclerView.setAdapter(mAdapter);
        //
        // set infinite  vertical scroll
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadNextPage(page);
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
        loadNextPage(0);
        return view ;
    }

    /**
     * This method is responsible to import items fom WS
     */
    private void loadNextPage(int page) {
        endlessRecyclerViewScrollListener.setIsLoading(true);
        ProductsStore
                .getProducts(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductsApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("EREE", e.toString());
                    }

                    @Override
                    public void onNext(ProductsApiResponse products) {
                        Log.e("Observer", "ObserverObserverObserverObserver");
                        handler.postDelayed(r, 500);
                        int count = mAdapter.getItemCount();
                        // item reach 100 we need to empty the listenr
                        mAdapter.addItems(products.getProductsList());
                        /*
                        // Prepare next fetch
                        LoadingViewHolder view = ((LoadingViewHolder) recyclerView.getLayoutManager().findViewByPosition(votesCount - 1).getTag()) ;
                        if (votesCount > 80){
                            mAdapter.setMustBeReloadedNextFetch(true);
                            view.switchView(false);
                        }
                        else {
                            view.switchView(true);
                        }*/


                    }
                });
    }

}
