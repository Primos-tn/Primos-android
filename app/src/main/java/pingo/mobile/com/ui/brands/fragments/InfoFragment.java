/**
 * File ProfileFragment
 * Project Pingo
 * Created by Pingo Team
 * (c) Pingo tn
 * Fragment code of profile tab
 */
package pingo.mobile.com.ui.brands.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.login.widget.ProfilePictureView;

import pingo.mobile.com.R;


public class InfoFragment extends Fragment {
    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    AccessToken accessToken;
    ProfilePictureView profilePictureView;
    ImageView settingIcon;
    /**
     * Get facebook photo into imageview
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_profile_fragment,container,false);
        return view;
        /**
         *Initialise facebook sdk
         *
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        /**
         * get current access token
         *
        accessToken = AccessToken.getCurrentAccessToken();
        /**
         * to inflate facebook profile photo
         *
        profilePictureView = (ProfilePictureView) view.findViewById(R.id.profil);
       profilePictureView.setProfileId(accessToken.getUserId());

         settingIcon = (ImageView) view.findViewById(R.id.ic_setting);
         settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SettingsActivity.class);
                startActivity(intent);
            }
        });
*/



    }

}