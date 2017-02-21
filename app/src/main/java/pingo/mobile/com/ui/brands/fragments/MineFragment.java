package pingo.mobile.com.ui.brands.fragments;

import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.responses.BrandsApiResponse;
import pingo.mobile.com.stores.BrandsStore;
import pingo.mobile.com.ui.brands.BrandsListAdapter;
import pingo.mobile.com.ui.common.EndlessRecyclerViewScrollListener;


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
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by houssem.fathallah on 19/02/2017.
 */
public class MineFragment extends Fragment {

    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    Handler handler = new Handler();
    private RecyclerView recyclerView;
    private BrandsListAdapter mAdapter;
    private int userId = Preferences.getInstance().getCurrentUserId();

    final Runnable r = new Runnable() {
        public void run() {
            endlessRecyclerViewScrollListener.setIsLoading(false);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.brand_mine_fragment, container, false);

        /**
         * Any changes in adapter content cannot change the size of the RecyclerView itself.
         */
        recyclerView = (RecyclerView) view.findViewById(R.id.brands_list);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new BrandsListAdapter(getActivity(), new ArrayList<BrandShortInfo>());
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
        return view;
    }

    /**
     * This method is responsible to import items fom WS
     */
    private void loadNextPage(int page) {
        endlessRecyclerViewScrollListener.setIsLoading(true);
        BrandsStore
                .getUserBrands(page, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrandsApiResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("EREE", e.toString());
                    }

                    @Override
                    public void onNext(BrandsApiResponse brands) {
                        Log.e("Observer", "ObserverObserverObserverObserver");
                        handler.postDelayed(r, 500);
                        int count = mAdapter.getItemCount();
                        // item reach 100 we need to empty the listener
                        mAdapter.addItems(brands.getBrandShortInfoList());
                    }
                });
    }
}
