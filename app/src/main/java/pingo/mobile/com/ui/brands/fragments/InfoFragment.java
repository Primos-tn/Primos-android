/**
 * File ProfileFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Fragment code of profile tab
 */
package pingo.mobile.com.ui.brands.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.widget.ProfilePictureView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pingo.mobile.com.R;
import pingo.mobile.com.api.models.Brand;
import pingo.mobile.com.utils.constants.Bundles;


public class InfoFragment extends Fragment {

    TextView facebookLinkTextView;
    // Our handler for received Intents. This will be called whenever an Intent
// with an action named "custom-event-name" is broadcasted.
    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            Bundle data = intent.getExtras();
            Brand brand =  data.getParcelable(Bundles.BRAND_PARCELABLE_KEY);
            facebookLinkTextView.setText(brand.getName());
        }
    };
    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,
                new IntentFilter(Bundles.BRAND_INFO_MESSAGE_RECEIVER));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_profile_fragment, container, false);
        facebookLinkTextView = (TextView) view.findViewById(R.id.facebook_link);

        return view;
    }


    @Override
    public void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

}