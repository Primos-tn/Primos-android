package pingo.mobile.com.ui.products;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.Product;

import static pingo.mobile.com.api.routes.common.getMediaUrl;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class SimilarProductsAdapter extends RecyclerView.Adapter<SimilarProductsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Product> products;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView overflow;
        public SimpleDraweeView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (SimpleDraweeView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public SimilarProductsAdapter(Context mContext) {
        this.mContext = mContext;
        products = new ArrayList<>();
    }

    /**
     * @param products
     */
    public void addItems(List<Product> products) {
        this.products = products;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_details_similar_product_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.title.setText(product.getProductInfo().getName());
        holder.count.setText(String.valueOf(product.getProductInfo().getVotesCount()));
        List<Picture> pictures = product.getPictures();
        if (
                !pictures.isEmpty() &&
                        (pictures.get(0).getFile() != null)
                ) {
            Uri uri = Uri.parse(getMediaUrl(pictures.get(0).getFile().getUrl()));
            holder.thumbnail.setImageURI(uri);
        }
        // loading album cover using Glide library
        //Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

//    /**
//     * Showing popup menu when tapping on 3 dots
//     */
//    private void showPopupMenu(View view) {
//        // inflate menu
//        PopupMenu popup = new PopupMenu(mContext, view);
//        MenuInflater inflater = popup.getMenuInflater();
//        inflater.inflate(R.menu.menu_album, popup.getMenu());
//        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
//        popup.show();
//    }
//
//    /**
//     * Click listener for popup menu items
//     */
//    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
//
//        public MyMenuItemClickListener() {
//        }
//
//        @Override
//        public boolean onMenuItemClick(MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.action_add_favourite:
//                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.action_play_next:
//                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
//                    return true;
//                default:
//            }
//            return false;
//        }
//    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}