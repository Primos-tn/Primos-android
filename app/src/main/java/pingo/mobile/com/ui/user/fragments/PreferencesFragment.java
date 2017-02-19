/**
 * File BrandProfileActivity
 * Project Pingo
 * Created by Tarek .. at 10:09
 * (c) Pingo tn
 * *
 * The base fragment wrapper of application.
 */
package pingo.mobile.com.ui.user.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import pingo.mobile.com.R;
import pingo.mobile.com.api.models.BrandShortInfo;

/**
 *
 */
public class PreferencesFragment extends Fragment implements View.OnClickListener {
    /**
     * Default oncerateView function.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    TextView  brandNameTextView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_profile_header_fragment, container, false);
        brandNameTextView = (TextView)view.findViewById(R.id.brand_name);
        LinearLayout followBtn = (LinearLayout)view.findViewById(R.id.relation_container_id);

        followBtn.setOnClickListener(this);
        return view;
    }

    /**
     *
     * @param shortBrand
     */
    public void setBrand(BrandShortInfo shortBrand) {
        // do something in fragment
        brandNameTextView.setText(shortBrand.getName());
    }
    @Override
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
