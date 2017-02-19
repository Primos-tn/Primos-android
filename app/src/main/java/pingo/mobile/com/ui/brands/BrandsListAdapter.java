/**
 * File UsersAdapter
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * RecyclerView widget is a more advanced and flexible version of ListView .
 * This widget is a container for displaying large data sets
 */
package pingo.mobile.com.ui.brands;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.BrandShortInfo;
import pingo.mobile.com.api.models.Picture;
import pingo.mobile.com.api.models.PictureFile;

import static pingo.mobile.com.api.routes.common.getMediaUrl;

/**
 * UsersAdapter is a subclass of RecyclerView responsible for providing views that represent items in a data set.
 */
public class BrandsListAdapter extends RecyclerView
        .Adapter<BrandsListAdapterViewHolder> {
    private Context mContext;
    private static ArrayList<BrandShortInfo> listOfBrandShortInfos;


    /**
     * Adapters provide a binding from List<Item> to views that are displayed within a RecyclerView.
     */
    public BrandsListAdapter(Context mContext, List<BrandShortInfo> productsList) {
        this.mContext = mContext;
        listOfBrandShortInfos = (ArrayList<BrandShortInfo>) productsList;
    }

    /**
     * to create a new RecyclerView.ViewHolder and initializes some private fields to be used by RecyclerView.
     */
    @Override
    public BrandsListAdapterViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brand_card_view_item, parent, false);

        return new BrandsListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BrandsListAdapterViewHolder holder, int position) {
        BrandShortInfo brandShortInfo = listOfBrandShortInfos.get(position);
        /**
         * {"Brands":[{"name":"bran","id":2,
         * "owner":"hassenfathallah",
         * "cover":{"url":"/2/Brands/2/269920_3289165846729_17448789_n.jpg","thumb":{"url":"/2/Brands/2/thumb_269920_3289165846729_17448789_n.jpg"},"small_thumb":{"url":"/2/Brands/2/small_thumb_269920_3289165846729_17448789_n.jpg"}},"followers_count":0,"is_following=":false},{"name":"Koala","id":1,"owner":"Hbusiness","cover":{"url":"/3/Brands/1/pic04.jpg","thumb":{"url":"/3/Brands/1/thumb_pic04.jpg"},"small_thumb":{"url":"/3/Brands/1/small_thumb_pic04.jpg"}},
         * "followers_count":1,"is_following=":false,"followers":[{"username":"HfMEddd","id":1}]}]}
         */
        PictureFile file = brandShortInfo.getCover();
        if (
                file != null && file.getThumbnail() != null
                ) {
            Uri uri = Uri.parse(getMediaUrl(file.getThumbnail().getUrl()));
            (holder.thumbnail).setImageURI(uri);

        }
        holder.brandName.setText(brandShortInfo.getName());
        holder.followerCount.setText(String.valueOf(brandShortInfo.getFollowersCount()));
        holder.setBrandId(brandShortInfo.getId());
    }

    /**
     * @param brands
     */
    public void addItems(List<BrandShortInfo> brands) {
        listOfBrandShortInfos.addAll(brands);
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return number of items
     */
    @Override
    public int getItemCount() {
        return listOfBrandShortInfos.size();
    }

}