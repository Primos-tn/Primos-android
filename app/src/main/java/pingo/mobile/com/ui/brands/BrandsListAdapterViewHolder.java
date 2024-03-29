package pingo.mobile.com.ui.brands;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.brands.BrandFollowResponse;
import pingo.mobile.com.stores.BrandsStore;
import pingo.mobile.com.ui.brands.activities.BrandProfileActivity;
import pingo.mobile.com.utils.constants.Bundles;
import rx.Observer;

/**
 *
 */
public class BrandsListAdapterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.brand_name)
    public TextView brandName;

    @BindView(R.id.followers_count)
    public TextView followerCount;

    @BindView(R.id.thumbnail)
    public ImageView thumbnail;
    /**
     *
     */
    private int brandId;

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    public BrandsListAdapterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.thumbnail)
    public void onThumbnailClick(View v) {
        Intent intent = new Intent(v.getContext(), BrandProfileActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt(Bundles.OPENED_BRAND_ID, this.brandId);
        intent.putExtras(mBundle);
        v.getContext().startActivity(intent);
    }


    @OnClick(R.id.follow_btn)
    public void onFollowBrandClick(View v) {
        // attach userId with push token
        BrandsStore.followBrand(this.brandId)
                .subscribe(new Observer<BrandFollowResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BrandFollowResponse brandFollowResponse) {


                    }
                });
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}