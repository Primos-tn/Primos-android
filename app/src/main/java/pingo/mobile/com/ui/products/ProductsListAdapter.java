/**
 * File UsersAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * RecyclerView widget is a more advanced and flexible version of ListView .
 * This widget is a container for displaying large data sets
 */
package pingo.mobile.com.ui.products;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.Product;

import static pingo.mobile.com.api.routes.common.getMediaUrl;

/**
 * UsersAdapter is a subclass of RecyclerView responsible for providing views that represent items in a data set.
 */
public class ProductsListAdapter extends RecyclerView
        .Adapter<ProductViewHolder> {
    private Context mContext;
    private static ArrayList<Product> arrayOfProducts;
    private int brandId = -1;


    /**
     * Adapters provide a binding from List<Item> to views that are displayed within a RecyclerView.
     */
    public ProductsListAdapter(Context mContext, List<Product> productsList) {
        this.mContext = mContext;
        arrayOfProducts = (ArrayList<Product>) productsList;
    }

    /**
     * Adapters provide a binding from List<Item> to views that are displayed within a RecyclerView.
     */
    public ProductsListAdapter(Context mContext, List<Product> productsList, int productId) {
        this.mContext = mContext;
        this.brandId = productId;
        arrayOfProducts = (ArrayList<Product>) productsList;
    }

    /**
     * to create a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_product_card_view_item, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        Product product = arrayOfProducts.get(position);
        List<Picture> pictures = product.getPictures();
        if (
                !pictures.isEmpty() &&
                        (pictures.get(0).getFile() != null) &&
                        (pictures.get(0).getFile().getThumbnail() != null) &&
                        (pictures.get(0).getFile().getThumbnail().getUrl() != null)
                ) {
            Uri uri = Uri.parse(getMediaUrl(pictures.get(0).getFile().getThumbnail().getUrl()));
            ((SimpleDraweeView) holder.thumbnail).setImageURI(uri);

            holder.brandThumbnail.setImageURI(
                    Uri.parse(
                            getMediaUrl(product
                                    .getBrandShortInfo()
                                    .getCover()
                                    .getSmallThumbnail()
                                    .getUrl()))
            );
        }
    }


    /**
     * Delete  item by index
     *
     * @param index: the position of the item
     */
    public void deleteItem(int index) {
        arrayOfProducts.remove(index);
        notifyItemRemoved(index);
    }

    /**
     * @param products
     */
    public void addItems(List<Product> products) {
        arrayOfProducts.addAll(products);
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return arrayOfProducts.size();
    }

}