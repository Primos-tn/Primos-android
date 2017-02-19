/**
 * File MainFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 *
 */
package pingo.mobile.com.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.greenfrvr.hashtagview.HashtagView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Category;
import pingo.mobile.com.api.models.SearchParamsOptions;
import pingo.mobile.com.api.responses.CategoriesApiResponse;
import pingo.mobile.com.stores.CategoriesStore;
import pingo.mobile.com.ui.common.FilterOptionsViewListener;
import rx.Observer;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainFragmentFilter extends Fragment {
    private FilterOptionsViewListener filterOptionsViewListener;
    SearchParamsOptions filterOptions;
    View view;

    protected
    @BindView(R.id.tags_list)
    HashtagView hashtagView;
    protected
    @BindView(R.id.home_products_fragment_filter_options_apply_id)
    Button applyFilterButton;
    protected
    @BindView(R.id.home_products_fragment_filter_options_reset_id)
    Button resetFilterButton;

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_products_fragment_filter, container, false);
        ButterKnife.bind(this, view);
        filterOptions = SearchParamsOptions.getInstance();
        CategoriesStore.fetchCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoriesApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final CategoriesApiResponse categoriesApiResponse) {
                        hashtagView.setData(categoriesApiResponse.getCategoryList(), getTagsTransformer(), new HashtagView.DataSelector<Category>() {
                            @Override
                            public boolean preselect(Category item) {
                                return categoriesApiResponse.getCategoryList().indexOf(item) % 2 == 1;
                            }
                        });
                    }
                });

        return view;
    }

    /**
     * @param filterOptionsViewListener
     */
    public void setFilterOptionsViewListener(FilterOptionsViewListener filterOptionsViewListener) {
        this.filterOptionsViewListener = filterOptionsViewListener;
    }

    /**
     *
     */
    private HashtagView.DataStateTransform<Category> getTagsTransformer() {
        return new HashtagView.DataStateTransform<Category>() {
            @Override
            public CharSequence prepare(Category item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.ColorPrimary, getActivity().getTheme())), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.ColorPrimary)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                return spannableString;
            }

            @Override
            public CharSequence prepareSelected(Category item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.WhiteColor, getActivity().getTheme())), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                } else {
                    spannableString.setSpan(new ForegroundColorSpan(getActivity().getResources().getColor(R.color.WhiteColor)), 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
                return spannableString;
            }
        };
    }

    /**
     *
     */
    @OnClick(R.id.home_products_fragment_filter_options_apply_id)
    void applyFilter() {
        this.filterOptionsViewListener.onFilterChanged(filterOptions);
    }

    /**
     *
     */
    @OnClick(R.id.home_products_fragment_filter_options_reset_id)
    void resetFilter() {
        this.filterOptionsViewListener.onFilterReset();
    }
}
