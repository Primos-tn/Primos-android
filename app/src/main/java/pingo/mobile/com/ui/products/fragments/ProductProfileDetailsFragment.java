package pingo.mobile.com.ui.products.fragments;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.ProductDetails;
import pingo.mobile.com.api.responses.ProductsApiResponse;
import pingo.mobile.com.stores.ProductsStore;
import pingo.mobile.com.ui.products.ProductDetailsPicturesPagerAdapter;
import pingo.mobile.com.ui.products.SimilarProductsAdapter;
import pingo.mobile.com.utils.constants.Bundles;
import pingo.mobile.com.utils.helpers.Utils;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class ProductProfileDetailsFragment extends Fragment implements View.OnClickListener {
    /**
     * Setter of base fragment toolbar.
     */

    private ViewPager picturesPager;
    private Object productId;
    private ProductDetailsPicturesPagerAdapter productDetailsPicturesPagerAdapter;

    TextView votesCount;
    TextView locationsCount;
    TextView name;
    private RecyclerView recyclerView;
    private SimilarProductsAdapter similarProductsAdapter;


    /**
     * Default oncerateView function.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_profile_base_fragment, container, false);
        productId = getActivity().getIntent().getExtras().getInt(Bundles.OPENED_PRODUCT_ID);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.product));
        /**
         * Assigning ViewPager View and setting the adapter
         */
        configurePicturesLayout(view);
        configureSimilarLayout(view);

        name = (TextView) view.findViewById(R.id.name);
        locationsCount = (TextView) view.findViewById(R.id.locations_count);
        votesCount = (TextView) view.findViewById(R.id.votes_count);
        loadInformation();
        getSimilarProducts();
        return view;
    }

    /**
     * @param view
     */
    private void configureSimilarLayout(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.similar_products_recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dpToPx(getActivity(), 10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        similarProductsAdapter = new SimilarProductsAdapter(getActivity());
        recyclerView.setAdapter(similarProductsAdapter);

    }

    /**
     * @param view
     */
    private void configurePicturesLayout(View view) {
        picturesPager = (ViewPager) view.findViewById(R.id.pictures_collection_pager);
        productDetailsPicturesPagerAdapter = new ProductDetailsPicturesPagerAdapter(this.getActivity());
        picturesPager.setAdapter(productDetailsPicturesPagerAdapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(picturesPager, true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    /**
     *
     */
    void loadInformation() {
        ProductsStore
                .getProduct((Integer) productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductDetails>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("EREE", e.toString());
                    }

                    @Override
                    public void onNext(ProductDetails product) {
                        attachProductInfo(product);
                    }
                });
    }

    /**
     *
     */
    void getSimilarProducts() {
        ProductsStore
                .getProducts(0)
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
                    public void onNext(ProductsApiResponse product) {
                        similarProductsAdapter.addItems(product.getProductsList());
                    }
                });
    }

    /**
     * @param
     */
    void attachProductInfo(ProductDetails product) {
        name.setText(product.getName());
        votesCount.setText(String.valueOf(product.getVotesCount()));
        locationsCount.setText(String.valueOf(product.getStoresCount()));
        productDetailsPicturesPagerAdapter.setPictureFiles((ArrayList<Picture>) product.getPictures());

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
