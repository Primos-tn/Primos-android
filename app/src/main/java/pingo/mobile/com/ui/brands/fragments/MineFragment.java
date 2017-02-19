/**
 * File BrandsFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Fragment code of the second Tab
 */
package pingo.mobile.com.ui.brands.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.stores.UsersStore;
import pingo.mobile.com.ui.brands.BrandsListAdapter;
import pingo.mobile.com.ui.common.EndlessRecyclerViewScrollListener;
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;

public class MineFragment extends Fragment {
    private RecyclerView recyclerView;
    private static String LOG_TAG = "BrandsFragment";
    private BrandsListAdapter mAdapter;

    /**
     *
     */
    public MineFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_mine_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.brands_list);
        /**
         * Any changes in adapter content cannot change the size of the RecyclerView itself.
         */
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new BrandsListAdapter(getContext(), new ArrayList<BrandShortInfo>());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    // ((ProductsListAdapterViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i))).animateImage();
                }
            }
        });
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                loadNextPage(page);
            }
        });
        loadNextPage(0);
        return view;
    }

    /**
     * This method is responsible to import items fom WS
     */
    private void loadNextPage(int page) {
        UsersStore.getBrandsList(Preferences.getInstance(getContext()).getCurrentUserId(), page)
                .subscribe(new Observer<List<BrandShortInfo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<BrandShortInfo> products) {
                        Log.e("Observer", "ObserverObserverObserverObserver");
                        mAdapter.addItems(products);
                    }
                });
    }
}
