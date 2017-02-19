/**
 * File MainFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 */
package pingo.mobile.com.ui.brands.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Product;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.stores.ProductsStore;
import pingo.mobile.com.ui.common.EndlessRecyclerViewScrollListener;
import pingo.mobile.com.ui.products.ProductsListAdapter;
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    public static ProductsFragment productsFragment;
    private ProductsListAdapter mAdapter;
    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    Handler handler = new Handler();
    private float scrollSpeed = 0.5f;

    final Runnable r = new Runnable() {
        public void run() {
            endlessRecyclerViewScrollListener.setIsLoading(false);
        }
    };
    // newInstance constructor for creating fragment with arguments

    LinearLayout linearLayoutProgressBar;

    public static ProductsFragment newInstance(int page, String title) {
        if (productsFragment == null) {
            productsFragment = new ProductsFragment();

        }
        return productsFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Fresco.initialize(getContext());
        View view = inflater.inflate(R.layout.brand_products_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.home_products_tab_recycler_view);
        //recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ProductsListAdapter(getContext(), new ArrayList<Product>(), Preferences.getInstance(view.getContext()).getCurrentBrandId());
        recyclerView.setAdapter(mAdapter);

        //linearLayoutProgressBar = (LinearLayout) view.findViewById(R.id.progress_bar_container);
        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadNextPage(mAdapter.getItemCount());
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
        return view;

    }

    /**
     * This method is responsible to import items fom WS
     */
    private void loadNextPage(int page) {
        endlessRecyclerViewScrollListener.setIsLoading(true);
        //linearLayoutProgressBar.setVisibility(View.VISIBLE);
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

                    }

                    @Override
                    public void onNext(ProductsApiResponse products) {
                        Log.e("Observer", "ObserverObserverObserverObserver");
                        //Remove loading item
                        handler.postDelayed(r, 500);
                        mAdapter.addItems(products.getProductsList());
                    }
                });

    }
}
