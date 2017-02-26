package pingo.mobile.com.ui.products;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Picture;

import static pingo.mobile.com.api.routes.common.getMediaUrl;

/**
 * Created by houssem.fathallah on 23/02/2017.
 */

public class ProductDetailsPicturesPagerAdapter extends PagerAdapter {

    private ArrayList<Picture> pictures;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public ProductDetailsPicturesPagerAdapter(Context context) {
        mContext = context;
        this.pictures = new ArrayList<Picture>() {
        };
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.product_details_picture_file_item, container, false);
        SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.picture);
        imageView.setImageURI(getMediaUrl(pictures.get(position).getFile().getUrl()));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);

    }

    public void setPictureFiles(ArrayList<Picture> pictures) {
        this.pictures = pictures;
        this.notifyDataSetChanged();

    }
}
