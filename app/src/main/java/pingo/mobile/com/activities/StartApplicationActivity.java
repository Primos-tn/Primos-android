package pingo.mobile.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pingo.mobile.com.R;
import pingo.mobile.com.api.responses.CategoriesApiResponse;
import pingo.mobile.com.stores.CategoriesStore;
import pingo.mobile.com.utils.storage.Preferences;
import rx.Observer;

/**
 * Created by houssem.fathallah on 05/10/2016.
 */
public class StartApplicationActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_application_activity);
        CategoriesStore.fetchCategories().subscribe(new Observer<CategoriesApiResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override

            public void onNext(CategoriesApiResponse tags) {
                if (Preferences.getInstance(StartApplicationActivity.this).isLoggedIn()) {
                    startActivity(new Intent(StartApplicationActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(StartApplicationActivity.this, HomeActivity.class));
                }
                finish();
            }
        });
    }
}
