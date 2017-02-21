package pingo.mobile.com.ui.products;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.PictureFile;

import static pingo.mobile.com.api.routes.common.getMediaUrl;

/**
 *
 */
public class ProductViewHolder extends RecyclerView.ViewHolder
        implements View
        .OnClickListener {
    public TextView title;
    public TextView count;

    @BindView(R.id.home_product_card_view_item_menu_id)
    ImageView menuItem;

    @BindView(R.id.brand_thumbnail)
    public ImageView brandThumbnail;

    @BindView(R.id.thumbnail)
    public ImageView thumbnail;


    @BindView(R.id.thumbnails)
    public LinearLayout listViewImagesCollection;

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
     */
    public void addImages(Context context, List<Picture> pictureFilesList) {
        // By using setAdpater method in listview we an add string array in list.
        SimpleDraweeView image;
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(2f);
        roundingParams.setCornersRadius(45);
        int i = 0;
        LinearLayout.LayoutParams lp;
        for (Picture picture : pictureFilesList) {
            image = new SimpleDraweeView(context);
            image.setImageURI(getMediaUrl(picture.getFile().getSmallThumbnail().getUrl()));
            image.setMaxHeight(90);
            image.setMaxWidth(90);
            image.setMinimumHeight(90);
            image.setMinimumWidth(90);
            lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i++ > 0) {
                lp.setMargins(0xfffffff6, 0, 0, 0);
            } else {
                lp.setMargins(0, 0, 0, 0);
            }
            image.setLayoutParams(lp);
            image.getHierarchy().setRoundingParams(roundingParams);
            listViewImagesCollection.addView(image);
        }
        if (pictureFilesList.size() > 0) {
            TextView textView = new TextView(context);
            textView.setText("+" + String.valueOf(pictureFilesList.size()));
            textView.setAllCaps(true);
            listViewImagesCollection.addView(textView);
        }

    }

    /**
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