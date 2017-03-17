/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.brands.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.brands.Brand;
import pingo.mobile.com.api.models.PictureFile;

import static pingo.mobile.com.api.routes.common.getMediaUrl;

/**
 *
 */
public class HeaderFragment extends Fragment {
    /**
     * Default oncerateView function.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @BindView(R.id.brand_name)
    TextView brandNameTextView;

    @BindView(R.id.relation_container_id)
    LinearLayout followBtn;


    @BindView(R.id.header_cover)
    ImageView headerCover;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_profile_header_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * @param brand
     */
    public void setBrand(Brand brand) {
        // do something in fragment
        brandNameTextView.setText(brand.getName());
        PictureFile file = brand.getCover();
        if (
                file != null && file.getThumbnail() != null
                ) {
            Uri uri = Uri.parse(getMediaUrl(file.getThumbnail().getUrl()));
            headerCover.setImageURI(uri);

        }
    }

    @OnClick(R.id.relation_container_id)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relation_container_id:
                Toast.makeText(getActivity(), "following",
                        Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
