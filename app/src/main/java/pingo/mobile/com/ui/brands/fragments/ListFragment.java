package pingo.mobile.com.ui.brands.fragments;

import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.responses.BrandsApiResponse;
import pingo.mobile.com.stores.BrandsStore;
import pingo.mobile.com.ui.brands.AutoCompleteBrandsAdapter;
import pingo.mobile.com.ui.brands.BrandsListAdapter;
import pingo.mobile.com.ui.common.EndlessRecyclerViewScrollListener;


import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import pingo.mobile.com.R;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by houssem.fathallah on 19/02/2017.
 */
public class ListFragment extends Fragment implements TextWatcher {

    EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    Handler handler = new Handler();
    private RecyclerView recyclerView;
    private BrandsListAdapter mAdapter;


    final Runnable r = new Runnable() {
        public void run() {
            endlessRecyclerViewScrollListener.setIsLoading(false);
        }
    };
    private EditText myAutoComplete;
    private AutoCompleteBrandsAdapter myAutoCompleteAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.brands_list_fragment, container, false);


        myAutoComplete = (EditText) view.findViewById(R.id.auto_search);
        myAutoComplete.addTextChangedListener(this);

        ListView listView = (ListView) view.findViewById(R.id.auto_search_results);
        myAutoCompleteAdapter = new AutoCompleteBrandsAdapter(this.getActivity(), R.layout.brands_autocomplete, new ArrayList<BrandShortInfo>());
        listView.setAdapter(myAutoCompleteAdapter);
        /**
         * Any changes in adapter content cannot change the size of the RecyclerView itself.
         */
        recyclerView = (RecyclerView) view.findViewById(R.id.brands_list_recycler_view);

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
                .getBrands(page)
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
                        // item reach 100 we need to empty the listenr
                        mAdapter.addItems(brands.getBrandShortInfoList());
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        myAutoCompleteAdapter.clear();
        myAutoCompleteAdapter.notifyDataSetChanged();
    }

    @Override
    public void afterTextChanged(Editable s) {
        BrandsStore
                .getBrands(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BrandsApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BrandsApiResponse brandsApiResponse) {
                        myAutoCompleteAdapter.clear();
                        myAutoCompleteAdapter.addAll(brandsApiResponse.getBrandShortInfoList());
                        myAutoCompleteAdapter.notifyDataSetChanged();
                    }
                });
    }
}
