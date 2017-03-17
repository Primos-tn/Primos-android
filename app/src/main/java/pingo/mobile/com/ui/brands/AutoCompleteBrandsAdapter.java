package pingo.mobile.com.ui.brands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.brands.BrandShortInfo;

public class AutoCompleteBrandsAdapter extends ArrayAdapter<BrandShortInfo> {
    List<BrandShortInfo> listItems;

    /**
     * @param context
     * @param resourceLayoutId
     * @param brandShortInfoList
     */
    public AutoCompleteBrandsAdapter(Context context, int resourceLayoutId, ArrayList<BrandShortInfo> brandShortInfoList) {
        super(context, resourceLayoutId, brandShortInfoList);
        this.listItems = brandShortInfoList;
    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        if (listItems == null) {
            return 0;
        } else {
            return listItems.size();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item from filtered list.
        BrandShortInfo brandShortInfo = listItems.get(position);
        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.brands_autocomplete, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.brands_autocomplete_item);
        tvName.setText(brandShortInfo.getName());
        //ivIcon.setImageResource(dog.drawable);

        return convertView;
    }
}
