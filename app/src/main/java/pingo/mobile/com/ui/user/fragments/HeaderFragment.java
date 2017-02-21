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
import android.widget.TextView;
import pingo.mobile.com.R;

/**
 *
 */
public class HeaderFragment extends Fragment{
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
        return inflater.inflate(R.layout.user_profile_header, container, false);
    }
}
