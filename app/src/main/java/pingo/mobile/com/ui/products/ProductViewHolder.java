package pingo.mobile.com.ui.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.ui.common.Helpers;
import pingo.mobile.com.ui.common.Toasts;
import pingo.mobile.com.ui.products.activities.ProductDetailsActivity;
import pingo.mobile.com.utils.constants.Bundles;

/**
 *
 */
public class ProductViewHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
    public TextView productName, votesCount, picturesCount, storesCount, brandName, highlight;


    @BindView(R.id.brand_thumbnail)
    ImageView brandThumbnail;

    @BindView(R.id.thumbnail)
    ImageView thumbnail;

    @BindView(R.id.remind_btn)
    ImageView remindButton;

    private int productId;

    @OnClick(R.id.thumbnail)
    public void onThumbnailClick(View v) {
        Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt(Bundles.OPENED_PRODUCT_ID, this.productId);
        intent.putExtras(mBundle);
        v.getContext().startActivity(intent);
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    public ProductViewHolder(View itemView) {
        super(itemView);
        productName = (TextView) itemView.findViewById(R.id.product_name);
        votesCount = (TextView) itemView.findViewById(R.id.votes_count);
        storesCount = (TextView) itemView.findViewById(R.id.stores_count);
        picturesCount = (TextView) itemView.findViewById(R.id.pictures_count);
        highlight = (TextView) itemView.findViewById(R.id.highlight);
        brandName = (TextView) itemView.findViewById(R.id.brand_name);
        ButterKnife.bind(this, itemView);
    }

    /**
     * @param view
     */
    @OnClick(R.id.menu_btn)
    public void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup_product, popup.getMenu());
        ProductViewHolderMenuItem productViewHolderMenuItem = new ProductViewHolderMenuItem(view.getContext(), this.productId);
        popup.setOnMenuItemClickListener(productViewHolderMenuItem);
        popup.show();
    }

    /**
     * @param view
     */
    @OnClick(R.id.remind_btn)
    public void notifyMe(View view) {
        Context context = view.getContext();
        String productName = this.productName.getText().toString();
        Helpers.createReminder(context, productName);
        Toasts.createShort(context, productName + " Created !");

    }

    /**
     * @param view
     */
    @OnClick(R.id.share_btn)
    public void shareMe(View view) {
        Context context = view.getContext();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Check before the end of day";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Primos");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /**
     * Method for onclick item on the RecyclerView
     */
    @Override
    public void onClick(View v) {
        Intent intent;
        // myClickListener.onItemClick(getAdapterPosition(), v);
        switch (v.getId()) {
            case R.id.thumbnail:
                /*intent = new Intent(v.getContext(), BrandProfileActivity.class);
                v.getContext().startActivity(intent);*/
                break;
            default:
                break;
        }

    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}