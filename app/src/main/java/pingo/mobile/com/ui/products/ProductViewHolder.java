package pingo.mobile.com.ui.products;

import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;

/**
 *
 */
public class ProductViewHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
    public TextView title ;
    public TextView count ;

    @BindView(R.id.home_product_card_view_item_menu_id)
    ImageView menuItem;

    @BindView(R.id.brand_thumbnail)
    public ImageView brandThumbnail;

    @BindView(R.id.thumbnail)
    public ImageView thumbnail ;

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    public ProductViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        count = (TextView) itemView.findViewById(R.id.count);
        ButterKnife.bind(this, itemView);
    }

    /**
     *
     * @param view
     */
    @OnClick(R.id.home_product_card_view_item_menu_id)
    public void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_popup_product, popup.getMenu());
        popup.setOnMenuItemClickListener(new ProductViewHolderMenuItem());
        popup.show();
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
}